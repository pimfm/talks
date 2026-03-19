package fm.pim

import fm.pim.tax.TarievenReport
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

class TarievenTest {

    @Test
    fun `GET tarieven for 2025`() = testApplication {
        application { module() }
        val client = createClient {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }

        val response = client.get("/nl/tarieven/2025")
        assertEquals(HttpStatusCode.OK, response.status)
        val report = response.body<TarievenReport>()
        assertEquals(2025, report.fiscalYear)
        assertEquals(2470L, report.zelfstandigenaftrek)
        assertEquals(0.127, report.mkbWinstvrijstelling)
        assertTrue(report.box1Schijven.isNotEmpty())
    }

    @Test
    fun `GET tarieven for 2026`() = testApplication {
        application { module() }
        val client = createClient {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }

        val response = client.get("/nl/tarieven/2026")
        assertEquals(HttpStatusCode.OK, response.status)
        val report = response.body<TarievenReport>()
        assertEquals(2026, report.fiscalYear)
        assertEquals(1200L, report.zelfstandigenaftrek)
        assertEquals(0.127, report.mkbWinstvrijstelling)

        // First bracket rate for 2026 is 36.97%
        val firstSchijf = report.box1Schijven.first()
        assertEquals(0.3697, firstSchijf.rate)
    }

    @Test
    fun `GET schijven for 2025`() = testApplication {
        application { module() }
        val client = createClient {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }

        val response = client.get("/nl/schijven/2025")
        assertEquals(HttpStatusCode.OK, response.status)
    }
}
