package fm.pim.tax.be

sealed interface TaxError {
    data class InvalideInvestering(val bedrag: Long) : TaxError
    data class InvalideInkomen(val bedrag: Long) : TaxError
}
