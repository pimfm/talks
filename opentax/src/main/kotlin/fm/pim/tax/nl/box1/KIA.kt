@file:Suppress("MatchingDeclarationName")

package fm.pim.tax.nl.box1

import fm.pim.tax.*

data class KiaBrackets(
    val threshold: Long,
    val flatRate: Double,
    val maxFixed: Long,
    val phaseOutStart: Long,
    val phaseOutRate: Double,
    val phaseOutEnd: Long,
)

context(year: FiscalYear)
fun kiaBrackets(): KiaBrackets = when (year) {
    is FY2022 -> KiaBrackets(2401L, 0.28, 17841L, 116069L, 0.0756, 348727L)
    is FY2023 -> KiaBrackets(2601L, 0.28, 18746L, 122506L, 0.0756, 366749L)
    is FY2024 -> KiaBrackets(2801L, 0.28, 19535L, 128785L, 0.0756, 387730L)
    is FY2025 -> KiaBrackets(2900L, 0.28, 19854L, 130744L, 0.0756, 392046L)
    is FY2026 -> KiaBrackets(2900L, 0.28, 20072L, 132746L, 0.0756, 398236L)
}

context(year: FiscalYear)
fun kia(investment: Long): Long {
    val b = kiaBrackets()
    return when {
        investment <= b.threshold     -> 0L
        investment <= b.phaseOutStart ->
            minOf((investment * b.flatRate).toLong(), b.maxFixed)
        investment <= b.phaseOutEnd   ->
            maxOf(0L, (b.maxFixed - (investment - b.phaseOutStart) * b.phaseOutRate).toLong())
        else -> 0L
    }
}
