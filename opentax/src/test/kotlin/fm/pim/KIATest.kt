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

class KIATest {

    @Test
    fun `investment below threshold gives no KIA`() {
        assertEquals(0L, kiaGeneric(2900L))
        assertEquals(0L, kiaGeneric(0L))
    }

    @Test
    fun `investment in first bracket gives 28 percent`() {
        // €6500 * 0.28 = 1820
        assertEquals(1820L, kiaGeneric(6500L))
    }

    @Test
    fun `investment in fixed bracket gives 20072`() {
        assertEquals(20072L, kiaGeneric(100000L))
    }

    @Test
    fun `investment in tapering bracket`() {
        // €200000 investment: 20072 - (200000 - 132746) * 0.0756
        val expected = (20072 - (200000 - 132746) * 0.0756).toLong()
        assertEquals(expected, kiaGeneric(200000L))
    }

    @Test
    fun `very large investment gives no KIA`() {
        assertEquals(0L, kiaGeneric(400000L))
        assertEquals(0L, kiaGeneric(1000000L))
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
