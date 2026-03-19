package fm.pim

import fm.pim.tax.AangifteInput
import fm.pim.tax.TaxReport
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

// Laura: full-time freelancer, 1400h, €88000, €6500 investment
class LauraFullFlowTest {

    private fun lauraInput(fiscalYear: Int, isStarter: Boolean = false) = AangifteInput(
        fiscalYear = fiscalYear,
        grossIncome = 88000L,
        hoursInBusiness = 1400,
        investment = 6500L,
        isStarter = isStarter,
        hasPartner = true,
    )

    private suspend fun callAangifte(client: io.ktor.client.HttpClient, year: Int, isStarter: Boolean = false): TaxReport {
        val response = client.post("/nl/aangifte") {
            contentType(ContentType.Application.Json)
            setBody(lauraInput(year, isStarter))
        }
        return response.body()
    }

    @Test
    fun `Laura 2022 gets full deductions including FOR`() = testApplication {
        application { module() }
        val client = createClient { install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) } }
        val report = callAangifte(client, 2022)
        assertTrue(report.errors.isEmpty())
        val d = assertNotNull(report.box1.entrepreneurDeductions)
        assertEquals(6310L, d.zelfstandigenaftrek)
        assertEquals(1820L, d.kia) // 6500 * 0.28
        assertTrue(d.fiscaleOudedagsreserve > 0L, "FOR should exist in 2022")
        assertTrue(report.totalTax > 0L)
    }

    @Test
    fun `Laura 2023 has zero FOR`() = testApplication {
        application { module() }
        val client = createClient { install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) } }
        val report = callAangifte(client, 2023)
        val d = assertNotNull(report.box1.entrepreneurDeductions)
        assertEquals(5030L, d.zelfstandigenaftrek)
        assertEquals(0L, d.fiscaleOudedagsreserve)
        assertEquals(1820L, d.kia)
    }

    @Test
    fun `Laura 2024 has correct zelfstandigenaftrek`() = testApplication {
        application { module() }
        val client = createClient { install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) } }
        val report = callAangifte(client, 2024)
        val d = assertNotNull(report.box1.entrepreneurDeductions)
        assertEquals(3750L, d.zelfstandigenaftrek)
        assertEquals(0L, d.fiscaleOudedagsreserve)
    }

    @Test
    fun `Laura 2025 has correct zelfstandigenaftrek`() = testApplication {
        application { module() }
        val client = createClient { install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) } }
        val report = callAangifte(client, 2025)
        val d = assertNotNull(report.box1.entrepreneurDeductions)
        assertEquals(2470L, d.zelfstandigenaftrek)
    }

    @Test
    fun `Laura 2026 has correct deductions`() = testApplication {
        application { module() }
        val client = createClient { install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) } }
        val report = callAangifte(client, 2026)
        assertTrue(report.errors.isEmpty())
        val d = assertNotNull(report.box1.entrepreneurDeductions)
        assertEquals(1200L, d.zelfstandigenaftrek)
        assertEquals(0L, d.startersaftrek)
        assertEquals(1820L, d.kia)
        assertEquals(0L, d.fiscaleOudedagsreserve)
        assertTrue(d.mkbWinstvrijstelling > 0L)
    }

    @Test
    fun `Laura as starter 2026 gets startersaftrek`() = testApplication {
        application { module() }
        val client = createClient { install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) } }
        val report = callAangifte(client, 2026, isStarter = true)
        val d = assertNotNull(report.box1.entrepreneurDeductions)
        assertEquals(2123L, d.startersaftrek)
    }

    @Test
    fun `Laura effective rate stays below 50 percent`() = testApplication {
        application { module() }
        val client = createClient { install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) } }
        for (year in 2022..2026) {
            val report = callAangifte(client, year)
            assertTrue(report.effectiveRate < 0.50, "Effective rate in $year should be < 50%, was ${report.effectiveRate}")
        }
    }
}
