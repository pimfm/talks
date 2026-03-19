package fm.pim.tax.nl.box1

import arrow.core.raise.Raise
import arrow.core.raise.ensure
import fm.pim.tax.FY2022
import fm.pim.tax.TaxError

// Fiscale Oudedagsreserve — ABOLISHED from 2023.
// This function ONLY exists for FY2022.
// Calling it with any other fiscal year context is a compile error.
context(year: FY2022)
fun Raise<TaxError>.fiscaleOudedagsreserve(winst: Long): Long {
    ensure(winst > 0) { TaxError.InvalidIncome(winst) }
    return minOf((winst * 0.0944).toLong(), 9632L)
}
