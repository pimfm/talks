package fm.pim.tax.be

import arrow.core.raise.Raise
import arrow.core.raise.ensure
import fm.pim.tax.FiscalYear
import fm.pim.tax.FY2025
import fm.pim.tax.FY2026

// Forfaitaire beroepskosten — flat-rate professional expense deduction for Belgian self-employed.
// 30% of net professional income (after sociale bijdragen), capped at the indexed annual maximum.
// Requires KBO registration as a self-employed person (zelfstandige).
context(year: FiscalYear)
fun forfaitaireBeroepskostenCap(): Long = when (year) {
    is FY2026 -> 5_870L
    is FY2025 -> 5_750L
    else      -> 5_750L
}

context(year: FiscalYear)
fun Raise<BETaxError>.forfaitaireBeroepskosten(
    nettoInkomen: Long,
    kboGeregistreerd: Boolean
): Long {
    ensure(kboGeregistreerd) { BETaxError.GeenKBORegistratie(nettoInkomen) }
    return minOf((nettoInkomen * 0.30).toLong(), forfaitaireBeroepskostenCap())
}
