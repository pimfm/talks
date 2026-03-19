package fm.pim

import fm.pim.tax.AangifteInput
import fm.pim.tax.FY2026
import fm.pim.tax.TaxReport
import fm.pim.tax.nl.box1.kia
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class KIATest {

    @Test
    fun `investment at or below threshold gives no KIA`() {
        with(FY2026) {
            assertEquals(0L, kia(2900L))
            assertEquals(0L, kia(0L))
        }
    }

    @Test
    fun `investment in flat rate bracket gives 28 percent capped at max`() {
        with(FY2026) {
            assertEquals(1820L, kia(6500L))      // 6500 * 0.28 = 1820
            assertEquals(20072L, kia(100000L))   // 100000 * 0.28 = 28000, capped at 20072
        }
    }

    @Test
    fun `investment in tapering bracket reduces KIA`() {
        with(FY2026) {
            // 20072 - (200000 - 132746) * 0.0756
            val expected = (20072 - (200000 - 132746) * 0.0756).toLong()
            assertEquals(expected, kia(200000L))
        }
    }

    @Test
    fun `very large investment gives no KIA`() {
        with(FY2026) {
            assertEquals(0L, kia(400000L))
            assertEquals(0L, kia(1000000L))
        }
    }

    @Test
    fun `Laura investment of 6500 in aangifte`() = testApplication {
        application { module() }
        val client = createClient {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }
        val response = client.post("/nl/aangifte") {
            contentType(ContentType.Application.Json)
            setBody(AangifteInput(
                fiscalYear = 2026,
                grossIncome = 88000L,
                hoursInBusiness = 1400,
                investment = 6500L
            ))
        }
        val report = response.body<TaxReport>()
        val kia = report.box1.entrepreneurDeductions?.kia ?: 0L
        assertEquals(1820L, kia)  // 6500 * 0.28
    }
}
