package fm.pim.tax.nl

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import fm.pim.tax.*
import fm.pim.tax.nl.box1.*
import fm.pim.tax.nl.box3.*

object NLTaxCalculator {

    fun calculate(input: AangifteInput): TaxReport {
        val year = fiscalYearOf(input.fiscalYear)
        val errors = mutableListOf<String>()

        val box1 = with(year) {
            calculateBox1(input, errors)
        }

        val box3 = input.box3Assets?.let { assets ->
            val result = with(year) { berekenBox3(assets) }
            Box3Report(
                assets = result.assets,
                fictitiousReturn = result.fictitiousRate,
                taxableReturn = result.taxableReturn,
                tax = result.tax
            )
        }

        val totalTax = box1.totalTax + (box3?.tax ?: 0L)
        val effectiveRate = if (input.grossIncome > 0) totalTax.toDouble() / input.grossIncome else 0.0

        return TaxReport(
            fiscalYear = "FY${input.fiscalYear}",
            grossIncome = input.grossIncome,
            box1 = box1,
            box3 = box3,
            totalTax = totalTax,
            effectiveRate = effectiveRate,
            errors = errors
        )
    }

    private fun FiscalYear.checkUren(input: AangifteInput, errors: MutableList<String>): Boolean {
        val hours = input.hoursInBusiness ?: return false
        return either { checkUrencriterium(hours) }.fold(
            { e ->
                when (e) {
                    is TaxError.UrencriteriumNotMet ->
                        errors.add("UrencriteriumNotMet: ${e.hours} hours worked, ${e.required} required")
                    else -> errors.add(e.toString())
                }
                false
            },
            { true }
        )
    }

    private fun FiscalYear.buildDeductions(input: AangifteInput): EntrepreneurDeductions {
        val za = zelfstandigenaftrek()
        val sa = if (input.isStarter) STARTERSAFTREK else 0L
        val afterZa = (input.grossIncome - za - sa).coerceAtLeast(0L)
        val mkb = mkbWinstvrijstelling(afterZa)
        val kiaAmount = input.investment?.let { inv ->
            either {
                ensure(inv >= 0) { TaxError.InvestmentOutOfRange(inv) }
                kiaGeneric(inv)
            }.fold({ 0L }, { it })
        } ?: 0L
        val forAmount = if (this is FY2022) {
            either { fiscaleOudedagsreserve(input.grossIncome) }.fold({ 0L }, { it })
        } else 0L
        val total = za + sa + mkb + kiaAmount + forAmount
        return EntrepreneurDeductions(za, sa, mkb, kiaAmount, forAmount, total)
    }

    private fun FiscalYear.calculateBox1(input: AangifteInput, errors: MutableList<String>): Box1Report {
        val meetsUren = input.hoursInBusiness != null && checkUren(input, errors)
        val deductions = if (meetsUren) buildDeductions(input) else null
        val taxableIncome = if (deductions != null) {
            (input.grossIncome - deductions.total).coerceAtLeast(0L)
        } else input.grossIncome
        val schijfResults = berekenBox1(taxableIncome)
        return Box1Report(
            grossIncome = input.grossIncome,
            entrepreneurDeductions = deductions,
            taxableIncome = taxableIncome,
            schijven = schijfResults.map { SchijfApplication(it.bracket, it.rate, it.income, it.tax) },
            totalTax = schijfResults.sumOf { it.tax }
        )
    }
}
