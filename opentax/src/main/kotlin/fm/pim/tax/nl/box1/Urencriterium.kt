package fm.pim.tax.nl.box1

import arrow.core.raise.Raise
import arrow.core.raise.ensure
import fm.pim.tax.FiscalYear
import fm.pim.tax.TaxError

context(year: FiscalYear)
fun Raise<TaxError>.checkUrencriterium(hours: Int) =
    ensure(hours >= 1225) { TaxError.UrencriteriumNotMet(hours) }
