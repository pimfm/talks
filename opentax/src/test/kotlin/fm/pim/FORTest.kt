package fm.pim

import arrow.core.raise.either
import fm.pim.tax.AangifteInput
import fm.pim.tax.FY2022
import fm.pim.tax.TaxReport
import fm.pim.tax.TaxError
import fm.pim.tax.nl.box1.fiscaleOudedagsreserve
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

class FORTest {

    @Test
    fun `FOR calculation works for FY2022`() {
        val year = FY2022
        val result = either<TaxError, Long> {
            with(year) { fiscaleOudedagsreserve(50000L) }
        }
        assertTrue(result.isRight())
        result.onRight { forAmount ->
            // 50000 * 0.0944 = 4720, capped at 9632
            assertEquals(4720L, forAmount)
        }
    }

    @Test
    fun `FOR capped at 9632 for high profit`() {
        val year = FY2022
        val result = either<TaxError, Long> {
            with(year) { fiscaleOudedagsreserve(200000L) }
        }
        assertTrue(result.isRight())
        result.onRight { assertEquals(9632L, it) }
    }

    @Test
    fun `FOR returns error for negative profit`() {
        val year = FY2022
        val result = either<TaxError, Long> {
            with(year) { fiscaleOudedagsreserve(-1000L) }
        }
        assertTrue(result.isLeft())
    }

    // NOTE: The following would be a COMPILE ERROR — FOR does not exist in FY2023!
    //
    // fun `FOR does not compile for FY2023`() {
    //     val year = FY2023
    //     either<TaxError, Long> {
    //         with(year) { fiscaleOudedagsreserve(50000L) }  // COMPILE ERROR: no context(FY2023)
    //     }
    // }
    //
    // This is the power of context parameters: making abolished tax rules
    // unrepresentable in code — the type system prevents the mistake entirely.

    @Test
    fun `aangifte for 2022 includes FOR in deductions`() = testApplication {
        application { module() }
        val client = createClient {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }
        val response = client.post("/nl/aangifte") {
            contentType(ContentType.Application.Json)
            setBody(AangifteInput(
                fiscalYear = 2022,
                grossIncome = 50000L,
                hoursInBusiness = 1400
            ))
        }
        val report = response.body<TaxReport>()
        val forAmount = report.box1.entrepreneurDeductions?.fiscaleOudedagsreserve ?: 0L
        assertTrue(forAmount > 0L, "FOR should be calculated for 2022")
        assertEquals(4720L, forAmount)
    }

    @Test
    fun `aangifte for 2023 has zero FOR`() = testApplication {
        application { module() }
        val client = createClient {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }
        val response = client.post("/nl/aangifte") {
            contentType(ContentType.Application.Json)
            setBody(AangifteInput(
                fiscalYear = 2023,
                grossIncome = 50000L,
                hoursInBusiness = 1400
            ))
        }
        val report = response.body<TaxReport>()
        val forAmount = report.box1.entrepreneurDeductions?.fiscaleOudedagsreserve ?: 0L
        assertEquals(0L, forAmount, "FOR should be 0 for 2023+ (abolished)")
    }
}
