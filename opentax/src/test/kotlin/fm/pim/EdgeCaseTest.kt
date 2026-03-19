package fm.pim

import fm.pim.tax.AangifteInput
import fm.pim.tax.TaxReport
import fm.pim.tax.nl.box1.kiaGeneric
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
import kotlin.test.assertNull
import kotlin.test.assertTrue

class EdgeCaseTest {

    private suspend fun aangifte(
        client: io.ktor.client.HttpClient,
        input: AangifteInput
    ): TaxReport = client.post("/nl/aangifte") {
        contentType(ContentType.Application.Json)
        setBody(input)
    }.body()

    @Test
    fun `zero gross income produces zero tax`() = testApplication {
        application { module() }
        val client = createClient { install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) } }
        val report = aangifte(client, AangifteInput(2026, grossIncome = 0L, hoursInBusiness = 1400))
        assertEquals(0L, report.totalTax)
        assertEquals(0.0, report.effectiveRate, 0.001)
    }

    @Test
    fun `investment at KIA lower boundary 2900 gives no KIA`() {
        assertEquals(0L, kiaGeneric(2900L))
    }

    @Test
    fun `investment just above KIA lower boundary gives KIA`() {
        val kia = kiaGeneric(2901L)
        assertTrue(kia > 0L, "Investment of 2901 should give KIA")
    }

    @Test
    fun `investment at second KIA bracket boundary 71683 gives fixed KIA`() {
        // At 71683, still in sliding scale — 71683 * 0.28 < 20072
        val kia = kiaGeneric(71683L)
        assertEquals((71683 * 0.28).toLong(), kia)
    }

    @Test
    fun `investment at third KIA bracket boundary 132746 gives 20072`() {
        assertEquals(20072L, kiaGeneric(132746L))
    }

    @Test
    fun `investment at fourth KIA bracket boundary 398236 is approximately zero`() {
        val kia = kiaGeneric(398236L)
        assertTrue(kia >= 0L, "KIA at 398236 should be >= 0")
        assertTrue(kia <= 100L, "KIA at 398236 should be near zero, was $kia")
    }

    @Test
    fun `investment just over upper bound 398237 gives zero KIA`() {
        assertEquals(0L, kiaGeneric(398237L))
    }

    @Test
    fun `hoursInBusiness 1224 does not meet urencriterium`() = testApplication {
        application { module() }
        val client = createClient { install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) } }
        val report = aangifte(
            client,
            AangifteInput(2026, grossIncome = 50000L, hoursInBusiness = 1224)
        )
        assertTrue(report.errors.any { it.contains("UrencriteriumNotMet") })
        assertNull(report.box1.entrepreneurDeductions)
    }

    @Test
    fun `hoursInBusiness 1225 meets urencriterium exactly`() = testApplication {
        application { module() }
        val client = createClient { install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) } }
        val report = aangifte(
            client,
            AangifteInput(2026, grossIncome = 50000L, hoursInBusiness = 1225)
        )
        assertTrue(report.errors.isEmpty(), "1225 hours should meet urencriterium, errors: ${report.errors}")
        assertNotNull(report.box1.entrepreneurDeductions)
    }

    @Test
    fun `isStarter gets extra 2123 aftrek`() = testApplication {
        application { module() }
        val client = createClient { install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) } }
        val starterReport = aangifte(
            client, AangifteInput(2026, 88000L, hoursInBusiness = 1400, isStarter = true)
        )
        val nonStarterReport = aangifte(
            client, AangifteInput(2026, 88000L, hoursInBusiness = 1400, isStarter = false)
        )
        val d1 = assertNotNull(starterReport.box1.entrepreneurDeductions)
        val d2 = assertNotNull(nonStarterReport.box1.entrepreneurDeductions)
        assertEquals(2123L, d1.startersaftrek)
        assertEquals(0L, d2.startersaftrek)
        // Starter gets higher total deductions (exact diff may vary due to MKB recalculation)
        assertTrue(d1.total > d2.total, "Starter should have higher total deductions")
    }
}
