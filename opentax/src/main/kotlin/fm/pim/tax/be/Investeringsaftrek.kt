package fm.pim.tax.be

import arrow.core.raise.Raise
import arrow.core.raise.ensure
import fm.pim.tax.FY2023
import fm.pim.tax.VerhoogdeInvesteringsaftrekJaar

// Gewone investeringsaftrek — ABOLISHED from FY2024.
// Flat 8% deduction on qualifying investments for self-employed (eenmanszaak).
// This function ONLY exists for FY2023 (last applicable year).
context(year: FY2023)
fun gewoneInvesteringsaftrek(investering: Long): Long {
    require(investering >= 0) { "Investering must be non-negative" }
    return (investering * 0.08).toLong()
}

// ─────────────────────────────────────────────────────────────────────────────

// Types of qualifying investments under the new verhoogde investeringsaftrek.
enum class InvesteringsType {
    // 40%: digital transformation, cybersecurity
    DIGITALISERING,
    // 40%: energy efficiency, renewables
    ENERGIE,
    // 20%: all other qualifying investments (base rate)
    BASIS
}

// Verhoogde investeringsaftrek — NEW from FY2024.
// Replaces the old gewone aftrek with a tiered system:
//   40% for digitalization and energy investments
//   20% for all other qualifying investments
context(year: VerhoogdeInvesteringsaftrekJaar)
fun verhoogdeInvesteringsaftrek(
    investering: Long,
    type: InvesteringsType
): Long {
    require(investering >= 0) { "Investering must be non-negative" }
    val rate = when (type) {
        InvesteringsType.DIGITALISERING -> 0.40
        InvesteringsType.ENERGIE        -> 0.40
        InvesteringsType.BASIS          -> 0.20
    }
    return (investering * rate).toLong()
}
