package fm.pim.tax.nl.box3

import fm.pim.tax.*

// Box 3 wealth tax — simplified flat rate model
context(year: FiscalYear)
fun berekenBox3(assets: Long): Box3Result {
    val heffingvrij = 57000L
    val taxableAssets = (assets - heffingvrij).coerceAtLeast(0L)
    val fictitiousRate = fictitiousReturnRate()
    val taxableReturn = (taxableAssets * fictitiousRate).toLong()
    val tax = (taxableReturn * 0.36).toLong()
    return Box3Result(assets, fictitiousRate, taxableReturn, tax)
}

context(year: FiscalYear)
private fun fictitiousReturnRate(): Double = when (year) {
    is FY2022 -> 0.0565
    is FY2023 -> 0.0636
    is FY2024 -> 0.0636
    is FY2025 -> 0.0588
    is FY2026 -> 0.0588
}

data class Box3Result(
    val assets: Long,
    val fictitiousRate: Double,
    val taxableReturn: Long,
    val tax: Long
)
