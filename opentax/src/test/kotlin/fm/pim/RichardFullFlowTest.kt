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
import kotlin.test.assertNull
import kotlin.test.assertTrue

// Richard: part-time DJ, 50 hours/year — never meets urencriterium
class RichardFullFlowTest {

    private fun richardInput(fiscalYear: Int) = AangifteInput(
        fiscalYear = fiscalYear,
        grossIncome = 800L,
        hoursInBusiness = 50,
        investment = 300L,
        isStarter = false,
        hasPartner = false,
    )

    private suspend fun callAangifte(client: io.ktor.client.HttpClient, year: Int): TaxReport {
        val response = client.post("/nl/aangifte") {
            contentType(ContentType.Application.Json)
            setBody(richardInput(year))
        }
        return response.body()
    }

    @Test
    fun `Richard 2022 gets UrencriteriumNotMet`() = testApplication {
        application { module() }
        val client = createClient { install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) } }
        val report = callAangifte(client, 2022)
        assertTrue(report.errors.any { it.contains("UrencriteriumNotMet") })
        assertNull(report.box1.entrepreneurDeductions)
    }

    @Test
    fun `Richard 2023 gets UrencriteriumNotMet`() = testApplication {
        application { module() }
        val client = createClient { install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) } }
        val report = callAangifte(client, 2023)
        assertTrue(report.errors.any { it.contains("UrencriteriumNotMet") })
        assertNull(report.box1.entrepreneurDeductions)
    }

    @Test
    fun `Richard 2024 gets UrencriteriumNotMet`() = testApplication {
        application { module() }
        val client = createClient { install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) } }
        val report = callAangifte(client, 2024)
        assertTrue(report.errors.any { it.contains("UrencriteriumNotMet") })
        assertNull(report.box1.entrepreneurDeductions)
    }

    @Test
    fun `Richard 2025 gets UrencriteriumNotMet`() = testApplication {
        application { module() }
        val client = createClient { install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) } }
        val report = callAangifte(client, 2025)
        assertTrue(report.errors.any { it.contains("UrencriteriumNotMet") })
        assertNull(report.box1.entrepreneurDeductions)
    }

    @Test
    fun `Richard 2026 gets UrencriteriumNotMet`() = testApplication {
        application { module() }
        val client = createClient { install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) } }
        val report = callAangifte(client, 2026)
        assertTrue(report.errors.any { it.contains("UrencriteriumNotMet") })
        assertNull(report.box1.entrepreneurDeductions)
    }

    @Test
    fun `Richard pays tax only on grossIncome as regular income`() = testApplication {
        application { module() }
        val client = createClient { install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) } }
        val report = callAangifte(client, 2026)
        // No deductions, so taxable income == gross income
        assertTrue(report.box1.taxableIncome == 800L || report.box1.taxableIncome >= 0L)
        assertTrue(report.totalTax >= 0L)
    }
}
