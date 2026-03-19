package fm.pim

import kotlin.math.roundToLong


@JvmInline
value class Euro private constructor(val euros: Long) : Comparable<Euro> {
    private val cents
        get() = euros.times(100)
    operator fun plus(other: Euro)    = Euro(cents + other.cents)
    operator fun minus(other: Euro)   = Euro(cents - other.cents)
    operator fun times(factor: Double) = Euro((cents * factor).roundToLong())
    operator fun unaryMinus()          = Euro(-cents)
    fun coerceAtLeast(min: Euro)      = if (this < min) min else this
    override fun compareTo(other: Euro) = cents.compareTo(other.cents)
    override fun toString() = "€${"%.2f".format(cents / 100.0)}"

    companion object {
        val ZERO = Euro(0)
        fun euros(amount: Number) = Euro((amount.toDouble() * 100).roundToLong())

        /**
         * Construct Money from a validated cent value.
         * Internal only — callers outside this module cannot bypass validation.
         */
        internal fun fromCents(cents: Long) = Euro(cents)
    }
}
