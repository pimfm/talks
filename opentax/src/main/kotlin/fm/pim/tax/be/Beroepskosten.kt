package fm.pim.tax.be

import fm.pim.tax.FiscalYear
import fm.pim.tax.FY2025
import fm.pim.tax.FY2026

// Forfaitaire beroepskosten — flat-rate professional expense deduction for Belgian self-employed.
// 30% of net professional income (after sociale bijdragen), capped at the indexed annual maximum.
context(year: FiscalYear)
fun forfaitaireBeroepskostenCap(): Long = when (year) {
    is FY2026 -> 5_870L
    is FY2025 -> 5_750L
    else      -> 5_750L
}

context(year: FiscalYear)
fun forfaitaireBeroepskosten(nettoInkomen: Long): Long =
    minOf((nettoInkomen * 0.30).toLong(), forfaitaireBeroepskostenCap())
