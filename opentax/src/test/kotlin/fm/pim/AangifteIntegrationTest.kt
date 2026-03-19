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

class AangifteIntegrationTest {

    // Richard: part-time DJ, 50h, €800 gross, €300 investment — does NOT meet urencriterium
    @Test
    fun `Richard - 50 hours, low income, no entrepreneur deductions`() = testApplication {
        application { module() }
        val client = createClient {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }

        val response = client.post("/nl/aangifte") {
            contentType(ContentType.Application.Json)
            setBody(AangifteInput(
                fiscalYear = 2026,
                grossIncome = 800L,
                hoursInBusiness = 50,
                investment = 300L,
                isStarter = false
            ))
        }

        assertEquals(HttpStatusCode.OK, response.status)
        val report = response.body<TaxReport>()
        assertEquals("FY2026", report.fiscalYear)
        assertEquals(800L, report.grossIncome)
        assertTrue(report.errors.any { it.contains("UrencriteriumNotMet") })
        // No entrepreneur deductions since urencriterium not met
        assertNull(report.box1.entrepreneurDeductions)
    }

    // Laura: full-time, 1400h, €88000 gross, €6500 investment — meets urencriterium, gets all deductions
    @Test
    fun `Laura - 1400 hours, high income, full entrepreneur deductions`() = testApplication {
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
                investment = 6500L,
                isStarter = false
            ))
        }

        assertEquals(HttpStatusCode.OK, response.status)
        val report = response.body<TaxReport>()
        assertEquals("FY2026", report.fiscalYear)
        assertEquals(88000L, report.grossIncome)
        assertTrue(report.errors.isEmpty())

        val deductions = report.box1.entrepreneurDeductions
        assertNotNull(deductions)
        assertEquals(1200L, deductions.zelfstandigenaftrek)   // 2026 rate
        assertEquals(0L, deductions.startersaftrek)           // not a starter
        assertTrue(deductions.kia > 0L)                       // €6500 investment qualifies
        assertTrue(report.totalTax > 0L)
        assertTrue(report.effectiveRate < 0.50)
    }

    @Test
    fun `Laura - starter gets extra aftrek`() = testApplication {
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
                isStarter = true
            ))
        }

        assertEquals(HttpStatusCode.OK, response.status)
        val report = response.body<TaxReport>()
        val deductions = report.box1.entrepreneurDeductions
        assertNotNull(deductions)
        assertEquals(2123L, deductions.startersaftrek)
    }
}

fun assertNull(value: Any?) = kotlin.test.assertNull(value)
