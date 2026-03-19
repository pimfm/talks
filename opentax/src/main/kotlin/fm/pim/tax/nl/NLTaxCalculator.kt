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

    private fun FiscalYear.calculateBox1(input: AangifteInput, errors: MutableList<String>): Box1Report {
        val year = this
        val isEntrepreneur = input.hoursInBusiness != null

        // Check urencriterium
        val meetsUrencriterium = if (isEntrepreneur) {
            val hours = input.hoursInBusiness ?: 0
            val result: Either<TaxError, Unit> = either {
                with(year) { checkUrencriterium(hours) }
            }
            result.fold(
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
        } else false

        var deductions: EntrepreneurDeductions? = null
        var taxableIncome = input.grossIncome

        if (isEntrepreneur && meetsUrencriterium) {
            val za = with(year) { zelfstandigenaftrek() }
            val sa = if (input.isStarter) startersaftrek else 0L
            val afterZa = (input.grossIncome - za - sa).coerceAtLeast(0L)
            val mkb = with(year) { mkbWinstvrijstelling(afterZa) }

            val kiaAmount = input.investment?.let { inv ->
                val result: Either<TaxError, Long> = either {
                    ensure(inv >= 0) { TaxError.InvestmentOutOfRange(inv) }
                    kiaGeneric(inv)
                }
                result.fold({ 0L }, { it })
            } ?: 0L

            val forAmount = if (year is FY2022) {
                val result: Either<TaxError, Long> = either {
                    with(year) { fiscaleOudedagsreserve(input.grossIncome) }
                }
                result.fold({ 0L }, { it })
            } else 0L

            val total = za + sa + mkb + kiaAmount + forAmount
            taxableIncome = (input.grossIncome - total).coerceAtLeast(0L)

            deductions = EntrepreneurDeductions(
                zelfstandigenaftrek = za,
                startersaftrek = sa,
                mkbWinstvrijstelling = mkb,
                kia = kiaAmount,
                fiscaleOudedagsreserve = forAmount,
                total = total
            )
        }

        val schijfResults = with(year) { berekenBox1(taxableIncome) }
        val totalBox1Tax = schijfResults.sumOf { it.tax }

        return Box1Report(
            grossIncome = input.grossIncome,
            entrepreneurDeductions = deductions,
            taxableIncome = taxableIncome,
            schijven = schijfResults.map {
                SchijfApplication(it.bracket, it.rate, it.income, it.tax)
            },
            totalTax = totalBox1Tax
        )
    }
}
