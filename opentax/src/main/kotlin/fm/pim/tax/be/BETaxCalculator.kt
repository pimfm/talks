package fm.pim.tax.be

import arrow.core.raise.either
import fm.pim.tax.FiscalYear
import fm.pim.tax.SchijfApplication
import fm.pim.tax.fiscalYearOf
import kotlinx.serialization.Serializable

@Serializable
data class BEZelfstandigeInput(
    val fiscalYear: Int,
    val brutoInkomen: Long,
    val kboGeregistreerd: Boolean = true,
    val investering: Long? = null,
    val investeringsType: String? = null
)

@Serializable
data class BEZelfstandigeReport(
    val fiscalYear: String,
    val brutoInkomen: Long,
    val socialeBijdragen: Long,
    val nettoNaBijdragen: Long,
    val forfaitaireBeroepskosten: Long,
    val belastingvrijeSom: Long,
    val belastbaarInkomen: Long,
    val schijven: List<SchijfApplication>,
    val totalTax: Long,
    val effectiveRate: Double,
    val errors: List<String> = emptyList()
)

object BETaxCalculator {

    // Tax-free basic allowance (belastingvrije som), indexed annually.
    context(year: FiscalYear)
    fun belastingvrijeSom(): Long = when (year) {
        is fm.pim.tax.FY2022 -> 9_270L
        is fm.pim.tax.FY2023 -> 9_690L
        is fm.pim.tax.FY2024 -> 10_570L
        is fm.pim.tax.FY2025 -> 10_910L
        is fm.pim.tax.FY2026 -> 11_200L
    }

    fun calculate(input: BEZelfstandigeInput): BEZelfstandigeReport {
        val year = fiscalYearOf(input.fiscalYear)
        val errors = mutableListOf<String>()

        return with(year) {
            val socialeBijdragen = berekenSocialeBijdragen(input.brutoInkomen)
            val nettoNaBijdragen = (input.brutoInkomen - socialeBijdragen).coerceAtLeast(0L)

            val forfait = either<BETaxError, Long> {
                forfaitaireBeroepskosten(nettoNaBijdragen, input.kboGeregistreerd)
            }.fold(
                ifLeft = {
                    errors.add("GeenKBORegistratie: niet erkend als zelfstandige")
                    0L
                },
                ifRight = { it }
            )

            val vrijeSom = belastingvrijeSom()
            val belastbaar = (nettoNaBijdragen - forfait - vrijeSom).coerceAtLeast(0L)

            val schijfResults = berekenBETax(belastbaar)
            val totalTax = schijfResults.sumOf { it.tax }
            val effectRate = if (input.brutoInkomen > 0) totalTax.toDouble() / input.brutoInkomen else 0.0

            BEZelfstandigeReport(
                fiscalYear = "FY${input.fiscalYear}",
                brutoInkomen = input.brutoInkomen,
                socialeBijdragen = socialeBijdragen,
                nettoNaBijdragen = nettoNaBijdragen,
                forfaitaireBeroepskosten = forfait,
                belastingvrijeSom = vrijeSom,
                belastbaarInkomen = belastbaar,
                schijven = schijfResults.map { SchijfApplication(it.bracket, it.rate, it.income, it.tax) },
                totalTax = totalTax,
                effectiveRate = effectRate,
                errors = errors
            )
        }
    }
}
