package fm.pim.tax.be

sealed interface BETaxError {
    data class GeenKBORegistratie(val inkomen: Long) : BETaxError
    data class InvalideInvestering(val bedrag: Long) : BETaxError
    data class InvalideInkomen(val bedrag: Long) : BETaxError
}
