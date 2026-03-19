package fm.pim.tax

import kotlinx.serialization.Serializable

@Serializable
data class AangifteInput(
    val fiscalYear: Int,
    val grossIncome: Long,
    val hoursInBusiness: Int? = null,
    val investment: Long? = null,
    val isStarter: Boolean = false,
    val hasPartner: Boolean = false,
    val partner: PartnerInput? = null,
    val ownHome: OwnHomeInput? = null,
    val box3Assets: Long? = null
)

@Serializable
data class PartnerInput(
    val grossIncome: Long = 0L
)

@Serializable
data class OwnHomeInput(
    val eigenwoningforfait: Long = 0L,
    val hypotheekrenteaftrek: Long = 0L
)
