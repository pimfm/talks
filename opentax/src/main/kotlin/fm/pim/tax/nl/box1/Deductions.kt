package fm.pim.tax.nl.box1

import fm.pim.tax.*

context(year: FiscalYear)
fun zelfstandigenaftrek(): Long = when (year) {
    is FY2022 -> 6310L
    is FY2023 -> 5030L
    is FY2024 -> 3750L
    is FY2025 -> 2470L
    is FY2026 -> 1200L
}

val startersaftrek: Long = 2123L

context(year: FiscalYear)
fun mkbWinstvrijstellingRate(): Double = when (year) {
    is FY2022 -> 0.14
    is FY2023 -> 0.1331
    is FY2024 -> 0.1331
    is FY2025 -> 0.127
    is FY2026 -> 0.127
}
