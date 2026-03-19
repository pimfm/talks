package fm.pim.tax.nl.box1

import fm.pim.tax.FY2026

// Kleinschaligheidsinvesteringsaftrek — 2026 rules
context(year: FY2026)
fun kia(investment: Long): Long = when {
    investment <= 2900L    -> 0L
    investment <= 71683L   -> (investment * 0.28).toLong()
    investment <= 132746L  -> 20072L
    investment <= 398236L  -> (20072 - (investment - 132746) * 0.0756).toLong()
    else                   -> 0L
}

// Generic KIA for other years (simplified — same 2026 thresholds for demo)
fun kiaGeneric(investment: Long): Long = when {
    investment <= 2900L    -> 0L
    investment <= 71683L   -> (investment * 0.28).toLong()
    investment <= 132746L  -> 20072L
    investment <= 398236L  -> (20072 - (investment - 132746) * 0.0756).toLong()
    else                   -> 0L
}
