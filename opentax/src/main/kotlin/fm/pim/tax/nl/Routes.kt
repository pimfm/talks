package fm.pim.tax.nl

import fm.pim.tax.*
import fm.pim.tax.nl.box1.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.nlRoutes() {
    route("/nl") {
        post("/aangifte") {
            val input = call.receive<AangifteInput>()
            val report = NLTaxCalculator.calculate(input)
            call.respond(report)
        }

        get("/tarieven/{year}") {
            val year = call.parameters["year"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid year")
            val fy = fiscalYearOf(year)
            val schijven = with(fy) { box1Schijven() }
            val report = TarievenReport(
                fiscalYear = year,
                box1Schijven = schijven.map {
                    SchijfTarief(it.upTo, it.rate, it.upTo?.let { u -> "≤€$u @ ${(it.rate * 100).toInt()}%" } ?: "Toptarief")
                },
                zelfstandigenaftrek = with(fy) { zelfstandigenaftrek() },
                startersaftrek = startersaftrek,
                mkbWinstvrijstelling = with(fy) { mkbWinstvrijstellingRate() },
                box3FictitiousRate = 0.0588
            )
            call.respond(report)
        }

        get("/schijven/{year}") {
            val year = call.parameters["year"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid year")
            val fy = fiscalYearOf(year)
            val schijven = with(fy) { box1Schijven() }
            call.respond(schijven.map {
                SchijfTarief(it.upTo, it.rate, it.upTo?.let { u -> "≤€$u" } ?: "Boven")
            })
        }
    }
}
