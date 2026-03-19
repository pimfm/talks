package fm.pim.tax.be

import fm.pim.tax.*
import fm.pim.tax.nl.NLTaxCalculator
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class ComparisonReport(
    val fiscalYear: String,
    val grossIncome: Long,
    val nl: TaxReport,
    val be: BEReport,
    val difference: Long,
    val nlEffectiveRate: Double,
    val beEffectiveRate: Double
)

@Serializable
data class BEReport(
    val grossIncome: Long,
    val taxableIncome: Long,
    val schijven: List<SchijfApplication>,
    val totalTax: Long,
    val effectiveRate: Double
)

fun Route.beRoutes() {
    route("/be") {
        post("/aangifte") {
            val input = call.receive<AangifteInput>()
            val fy = fiscalYearOf(input.fiscalYear)

            val beResults = with(fy) { berekenBETax(input.grossIncome) }
            val beTotalTax = beResults.sumOf { it.tax }
            val beEffRate = if (input.grossIncome > 0) beTotalTax.toDouble() / input.grossIncome else 0.0

            val nlReport = NLTaxCalculator.calculate(input)

            val beReport = BEReport(
                grossIncome = input.grossIncome,
                taxableIncome = input.grossIncome,
                schijven = beResults.map { SchijfApplication(it.bracket, it.rate, it.income, it.tax) },
                totalTax = beTotalTax,
                effectiveRate = beEffRate
            )

            call.respond(ComparisonReport(
                fiscalYear = "FY${input.fiscalYear}",
                grossIncome = input.grossIncome,
                nl = nlReport,
                be = beReport,
                difference = nlReport.totalTax - beTotalTax,
                nlEffectiveRate = nlReport.effectiveRate,
                beEffectiveRate = beEffRate
            ))
        }
    }
}
