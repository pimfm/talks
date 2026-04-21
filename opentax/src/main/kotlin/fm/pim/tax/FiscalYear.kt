package fm.pim.tax

sealed interface FiscalYear

// Marker for years where the Belgian verhoogde investeringsaftrek applies (FY2024+).
sealed interface VerhoogdeInvesteringsaftrekJaar : FiscalYear

data object FY2022 : FiscalYear
data object FY2023 : FiscalYear
data object FY2024 : FiscalYear, VerhoogdeInvesteringsaftrekJaar
data object FY2025 : FiscalYear, VerhoogdeInvesteringsaftrekJaar
data object FY2026 : FiscalYear, VerhoogdeInvesteringsaftrekJaar

fun fiscalYearOf(year: Int): FiscalYear = when (year) {
    2022 -> FY2022
    2023 -> FY2023
    2024 -> FY2024
    2025 -> FY2025
    2026 -> FY2026
    else -> FY2026
}

fun FiscalYear.toInt(): Int = when (this) {
    is FY2022 -> 2022
    is FY2023 -> 2023
    is FY2024 -> 2024
    is FY2025 -> 2025
    is FY2026 -> 2026
}
