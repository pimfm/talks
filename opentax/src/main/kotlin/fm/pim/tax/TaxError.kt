package fm.pim.tax

sealed interface TaxError {
    data class UrencriteriumNotMet(val hours: Int, val required: Int = 1225) : TaxError
    data class InvalidIncome(val amount: Long) : TaxError
    data class InvestmentOutOfRange(val amount: Long) : TaxError
    data object PartnerNotProvided : TaxError
    data class UnknownFiscalYear(val year: Int) : TaxError
}
