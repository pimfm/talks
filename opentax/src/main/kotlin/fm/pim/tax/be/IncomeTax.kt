package fm.pim.tax.be

import fm.pim.tax.FiscalYear
import fm.pim.tax.SchijfApplication

data class BESchijf(val upTo: Long?, val rate: Double)

val beSchijven = listOf(
    BESchijf(15820L,  0.25),
    BESchijf(27920L,  0.40),
    BESchijf(48320L,  0.45),
    BESchijf(null,    0.50)
)

context(year: FiscalYear)
fun berekenBETax(inkomen: Long): List<SchijfResult> {
    val results = mutableListOf<SchijfResult>()
    var remaining = inkomen.coerceAtLeast(0L)
    var prev = 0L

    for (schijf in beSchijven) {
        if (remaining <= 0) break
        val max = schijf.upTo?.let { it - prev } ?: remaining
        val inSchijf = minOf(remaining, max)
        val tax = (inSchijf * schijf.rate).toLong()
        val label = schijf.upTo?.let { "≤€$it" } ?: ">€${prev}"
        results.add(SchijfResult(label, schijf.rate, inSchijf, tax))
        remaining -= inSchijf
        prev = schijf.upTo ?: prev
    }
    return results
}

data class SchijfResult(val bracket: String, val rate: Double, val income: Long, val tax: Long)
