package fm.pim.tax.nl

import fm.pim.tax.*
import fm.pim.tax.nl.box1.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private fun FiscalYear.buildTarievenReport(year: Int): TarievenReport {
    val schijven = box1Schijven()
    return TarievenReport(
        fiscalYear = year,
        box1Schijven = schijven.map {
            val label = it.upTo?.let { u -> "≤€$u @ ${(it.rate * 100).toInt()}%" } ?: "Toptarief"
            SchijfTarief(it.upTo, it.rate, label)
        },
        zelfstandigenaftrek = zelfstandigenaftrek(),
        startersaftrek = STARTERSAFTREK,
        mkbWinstvrijstelling = mkbWinstvrijstellingRate(),
        box3FictitiousRate = 0.0588
    )
}

fun Route.nlRoutes() {
    route("/nl") {
        post("/aangifte") {
            val input = call.receive<AangifteInput>()
            call.respond(NLTaxCalculator.calculate(input))
        }

        get("/tarieven/{year}") {
            val year = call.parameters["year"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid year")
            call.respond(with(fiscalYearOf(year)) { buildTarievenReport(year) })
        }

        get("/schijven/{year}") {
            val year = call.parameters["year"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid year")
            val fy = fiscalYearOf(year)
            call.respond(with(fy) { box1Schijven() }.map {
                SchijfTarief(it.upTo, it.rate, it.upTo?.let { u -> "≤€$u" } ?: "Boven")
            })
        }
    }
}
