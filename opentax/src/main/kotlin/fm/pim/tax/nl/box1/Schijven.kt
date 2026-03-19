package fm.pim.tax.nl.box1

import fm.pim.tax.*

data class Schijf(val upTo: Long?, val rate: Double)

context(year: FiscalYear)
fun box1Schijven(): List<Schijf> = when (year) {
    is FY2025 -> listOf(
        Schijf(38441L, 0.3582),
        Schijf(null,   0.4950)
    )
    is FY2026 -> listOf(
        Schijf(38441L, 0.3697),
        Schijf(null,   0.4950)
    )
    is FY2024 -> listOf(
        Schijf(38441L, 0.3697),
        Schijf(null,   0.4950)
    )
    is FY2023 -> listOf(
        Schijf(37149L, 0.3693),
        Schijf(null,   0.4950)
    )
    is FY2022 -> listOf(
        Schijf(35472L, 0.3707),
        Schijf(null,   0.4950)
    )
}

context(year: FiscalYear)
fun berekenBox1(belastbaarInkomen: Long): List<SchijfResult> {
    val schijven = box1Schijven()
    val results = mutableListOf<SchijfResult>()
    var remaining = belastbaarInkomen.coerceAtLeast(0L)
    var prevLimit = 0L

    for (schijf in schijven) {
        if (remaining <= 0) break
        val schijfMax = schijf.upTo?.let { it - prevLimit } ?: remaining
        val inThisSchijf = minOf(remaining, schijfMax)
        val tax = (inThisSchijf * schijf.rate).toLong()
        val label = schijf.upTo?.let { "≤€${it}" } ?: ">${prevLimit}"
        results.add(SchijfResult(label, schijf.rate, inThisSchijf, tax))
        remaining -= inThisSchijf
        prevLimit = schijf.upTo ?: prevLimit
    }
    return results
}

data class SchijfResult(
    val bracket: String,
    val rate: Double,
    val income: Long,
    val tax: Long
)
