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

class UrencriteriumTest {

    @Test
    fun `Richard with 50 hours gets UrencriteriumNotMet error`() = testApplication {
        application { module() }
        val client = createClient {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }

        val response = client.post("/nl/aangifte") {
            contentType(ContentType.Application.Json)
            setBody(AangifteInput(
                fiscalYear = 2026,
                grossIncome = 800L,
                hoursInBusiness = 50
            ))
        }

        val report = response.body<TaxReport>()
        assertTrue(report.errors.any { "UrencriteriumNotMet" in it && "50" in it })
        assertNull(report.box1.entrepreneurDeductions)
    }

    @Test
    fun `Exactly 1225 hours meets urencriterium`() = testApplication {
        application { module() }
        val client = createClient {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }

        val response = client.post("/nl/aangifte") {
            contentType(ContentType.Application.Json)
            setBody(AangifteInput(
                fiscalYear = 2026,
                grossIncome = 50000L,
                hoursInBusiness = 1225
            ))
        }

        val report = response.body<TaxReport>()
        assertTrue(report.errors.none { "UrencriteriumNotMet" in it })
        kotlin.test.assertNotNull(report.box1.entrepreneurDeductions)
    }

    @Test
    fun `1224 hours just misses urencriterium`() = testApplication {
        application { module() }
        val client = createClient {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }

        val response = client.post("/nl/aangifte") {
            contentType(ContentType.Application.Json)
            setBody(AangifteInput(
                fiscalYear = 2026,
                grossIncome = 50000L,
                hoursInBusiness = 1224
            ))
        }

        val report = response.body<TaxReport>()
        assertTrue(report.errors.any { "UrencriteriumNotMet" in it })
    }
}
