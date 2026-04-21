package fm.pim.tax.be

import arrow.core.raise.Raise
import arrow.core.raise.ensure
import fm.pim.tax.FiscalYear

sealed interface BeroepsStatus
data object Hoofdberoep : BeroepsStatus
data object Bijberoep : BeroepsStatus

// Below this threshold, bijberoep self-employed owe no social contributions.
const val BIJBEROEP_DREMPEL: Long = 1_922L

data class BijdragenNietVerschuldigd(
    val inkomen: Long,
    val drempel: Long = BIJBEROEP_DREMPEL
) : BETaxError

// Belgian mandatory social contributions for self-employed.
// Rate: 20.5% of net professional income.
// For bijberoep: raises BijdragenNietVerschuldigd if income is below the threshold.
context(year: FiscalYear)
fun Raise<BETaxError>.berekenSocialeBijdragen(
    nettoInkomen: Long,
    status: BeroepsStatus
): Long {
    if (status is Bijberoep) {
        ensure(nettoInkomen >= BIJBEROEP_DREMPEL) {
            BijdragenNietVerschuldigd(nettoInkomen, BIJBEROEP_DREMPEL)
        }
    }
    return (nettoInkomen * 0.205).toLong()
}

// Minimum annual social contribution for hoofdberoep (indexed, 2025 figure).
const val MINIMALE_BIJDRAGE_HOOFDBEROEP: Long = 3_717L
