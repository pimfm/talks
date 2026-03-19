package fm.pim.tax

import kotlinx.serialization.Serializable

@Serializable
data class TaxReport(
    val fiscalYear: String,
    val grossIncome: Long,
    val box1: Box1Report,
    val box3: Box3Report? = null,
    val totalTax: Long,
    val effectiveRate: Double,
    val errors: List<String> = emptyList()
)

@Serializable
data class Box1Report(
    val grossIncome: Long,
    val entrepreneurDeductions: EntrepreneurDeductions? = null,
    val taxableIncome: Long,
    val schijven: List<SchijfApplication>,
    val totalTax: Long
)

@Serializable
data class SchijfApplication(
    val bracket: String,
    val rate: Double,
    val income: Long,
    val tax: Long
)

@Serializable
data class EntrepreneurDeductions(
    val zelfstandigenaftrek: Long,
    val startersaftrek: Long,
    val mkbWinstvrijstelling: Long,
    val kia: Long,
    val fiscaleOudedagsreserve: Long,
    val total: Long
)

@Serializable
data class Box3Report(
    val assets: Long,
    val fictitiousReturn: Double,
    val taxableReturn: Long,
    val tax: Long
)

@Serializable
data class TarievenReport(
    val fiscalYear: Int,
    val box1Schijven: List<SchijfTarief>,
    val zelfstandigenaftrek: Long,
    val startersaftrek: Long,
    val mkbWinstvrijstelling: Double,
    val box3FictitiousRate: Double
)

@Serializable
data class SchijfTarief(
    val upTo: Long?,
    val rate: Double,
    val description: String
)
