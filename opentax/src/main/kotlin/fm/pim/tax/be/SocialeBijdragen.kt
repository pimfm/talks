package fm.pim.tax.be

import fm.pim.tax.FiscalYear

// Belgian mandatory social contributions for self-employed (hoofdberoep).
// Rate: 20.5% of net professional income.
// These are deducted BEFORE calculating professional expenses and income tax.
context(year: FiscalYear)
fun berekenSocialeBijdragen(brutoInkomen: Long): Long =
    (brutoInkomen * 0.205).toLong()

// Minimum annual social contribution for hoofdberoep (indexed, 2025 figure).
// Applies when income is below the minimum income floor (~€15,188).
const val MINIMALE_BIJDRAGE_HOOFDBEROEP: Long = 3_717L
