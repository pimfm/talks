package fm.pim

import arrow.core.Either
import arrow.core.raise.Raise
import arrow.core.raise.either
import arrow.core.raise.recover
import fm.pim.`<no name provided>`.zelfstandigenAftrek
import kotlin.math.roundToLong

data class NotEnoughHours(val actual: Int, val required: Int)


object € {

}

enum class TaxYear {
    TAXYEAR2022,
    TAXYEAR2023,
}

context(year: )
fun zelfstandigenAftrek(
    hours: Int,
    income: Money
): Either<NotEnoughHours, Money> {
    // ...
}


    if (hours < 1225) {
        raise.raise(NotEnoughHours(hours, 1225))
    }

    return income
        .minus(1200.euro)
        .coerceAtLeast(0.euro)
}

fun main() {
    val grossIncome = 800.euro
    val netIncome = recover({
        zelfstandigenAftrek(hours = 50, income = grossIncome)
    }) { _: NotEnoughHours ->
        info("Not enough hours.")
        grossIncome
    }

    netIncome // € 800
}

fun main() {
    val grossIncome = 88_000.euro
    val netIncome = either {
        zelfstandigenAftrek(
            hours = 1_400,
            income = grossIncome
        )
    }

    netIncome // Right(€ 86800.00)
}


val Int.euro: Money get() = Money(this.toLong())
val Long.euro: Money get() = Money(this)

@JvmInline
value class Money private constructor(val euros: Long) : Comparable<Money> {
    private val cents
        get() = euros.times(100)
    operator fun plus(other: Money)    = Money(cents + other.cents)
    operator fun minus(other: Money)   = Money(cents - other.cents)
    operator fun times(factor: Double) = Money((cents * factor).roundToLong())
    operator fun unaryMinus()          = Money(-cents)
    fun coerceAtLeast(min: Money)      = if (this < min) min else this
    override fun compareTo(other: Money) = cents.compareTo(other.cents)
    override fun toString() = "€${"%.2f".format(cents / 100.0)}"

    companion object {
        val ZERO = Money(0)
        fun euros(amount: Number) = Money((amount.toDouble() * 100).roundToLong())

        /**
         * Construct Money from a validated cent value.
         * Internal only — callers outside this module cannot bypass validation.
         */
        internal fun fromCents(cents: Long) = Money(cents)
    }
}
