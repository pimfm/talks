package fm.pim.tax.nl.box1

import fm.pim.tax.FiscalYear

context(year: FiscalYear)
fun mkbWinstvrijstelling(winstNaAftrek: Long): Long =
    (winstNaAftrek * mkbWinstvrijstellingRate()).toLong()
