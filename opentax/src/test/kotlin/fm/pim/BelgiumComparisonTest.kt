package fm.pim

import fm.pim.tax.AangifteInput
import fm.pim.tax.be.ComparisonReport
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BelgiumComparisonTest {

    @Test
    fun `compare NL vs BE for same income`() = testApplication {
        application { module() }
        val client = createClient {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }

        val response = client.post("/be/aangifte") {
            contentType(ContentType.Application.Json)
            setBody(AangifteInput(
                fiscalYear = 2026,
                grossIncome = 60000L
            ))
        }

        assertEquals(HttpStatusCode.OK, response.status)
        val report = response.body<ComparisonReport>()
        assertEquals("FY2026", report.fiscalYear)
        assertEquals(60000L, report.grossIncome)
        assertTrue(report.nl.totalTax > 0)
        assertTrue(report.be.totalTax > 0)
        // NL progressive vs BE progressive — both should be reasonable
        assertTrue(report.nlEffectiveRate in 0.0..0.6)
        assertTrue(report.beEffectiveRate in 0.0..0.6)
    }

    @Test
    fun `Laura comparison NL vs BE at 88000`() = testApplication {
        application { module() }
        val client = createClient {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }

        val response = client.post("/be/aangifte") {
            contentType(ContentType.Application.Json)
            setBody(AangifteInput(
                fiscalYear = 2026,
                grossIncome = 88000L,
                hoursInBusiness = 1400,
                investment = 6500L
            ))
        }

        val report = response.body<ComparisonReport>()
        // Both NL and BE should calculate tax for high earner
        assertTrue(report.be.totalTax > 0)
        assertTrue(report.nl.totalTax > 0)
        // BE has no entrepreneur deductions so usually higher effective rate for same gross
        assertTrue(report.beEffectiveRate > report.nlEffectiveRate,
            "BE effective rate (${report.beEffectiveRate}) should be higher than NL (${report.nlEffectiveRate}) for same gross with NL deductions")
    }

    @Test
    fun `BE tax brackets applied correctly`() = testApplication {
        application { module() }
        val client = createClient {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }

        val response = client.post("/be/aangifte") {
            contentType(ContentType.Application.Json)
            setBody(AangifteInput(
                fiscalYear = 2026,
                grossIncome = 15820L
            ))
        }

        val report = response.body<ComparisonReport>()
        // All income in first bracket at 25%
        val beTax = report.be.totalTax
        val expected = (15820 * 0.25).toLong()
        assertEquals(expected, beTax)
    }
}
