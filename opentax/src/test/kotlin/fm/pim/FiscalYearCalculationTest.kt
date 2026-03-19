package fm.pim

import fm.pim.tax.*
import fm.pim.tax.nl.box1.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FiscalYearCalculationTest {

    @Test
    fun `FY2022 box1 schijven has first bracket at 35472`() {
        val schijven = with(FY2022) { box1Schijven() }
        assertEquals(2, schijven.size)
        assertEquals(35472L, schijven[0].upTo)
        assertEquals(0.3707, schijven[0].rate, 0.0001)
    }

    @Test
    fun `FY2023 box1 schijven has first bracket at 37149`() {
        val schijven = with(FY2023) { box1Schijven() }
        assertEquals(37149L, schijven[0].upTo)
        assertEquals(0.3693, schijven[0].rate, 0.0001)
    }

    @Test
    fun `FY2026 zelfstandigenaftrek is 1200`() {
        val za = with(FY2026) { zelfstandigenaftrek() }
        assertEquals(1200L, za)
    }

    @Test
    fun `FY2022 zelfstandigenaftrek is 6310`() {
        val za = with(FY2022) { zelfstandigenaftrek() }
        assertEquals(6310L, za)
    }

    @Test
    fun `MKB rate is 14 percent in 2022`() {
        val rate = with(FY2022) { mkbWinstvrijstellingRate() }
        assertEquals(0.14, rate, 0.0001)
    }

    @Test
    fun `MKB rate is 12_7 percent in 2025 and 2026`() {
        assertEquals(0.127, with(FY2025) { mkbWinstvrijstellingRate() }, 0.0001)
        assertEquals(0.127, with(FY2026) { mkbWinstvrijstellingRate() }, 0.0001)
    }

    @Test
    fun `MKB rate is 13_31 percent in 2023 and 2024`() {
        assertEquals(0.1331, with(FY2023) { mkbWinstvrijstellingRate() }, 0.0001)
        assertEquals(0.1331, with(FY2024) { mkbWinstvrijstellingRate() }, 0.0001)
    }

    @Test
    fun `zelfstandigenaftrek declines every year`() {
        val values = listOf(
            with(FY2022) { zelfstandigenaftrek() },
            with(FY2023) { zelfstandigenaftrek() },
            with(FY2024) { zelfstandigenaftrek() },
            with(FY2025) { zelfstandigenaftrek() },
            with(FY2026) { zelfstandigenaftrek() },
        )
        for (i in 0 until values.size - 1) {
            assertTrue(values[i] > values[i + 1], "Year ${i + 2022} should be higher than ${i + 2023}")
        }
    }

    @Test
    fun `berekenBox1 for zero income gives no tax`() {
        val results = with(FY2026) { berekenBox1(0L) }
        assertEquals(0L, results.sumOf { it.tax })
    }

    @Test
    fun `berekenBox1 correctly applies two schijven`() {
        // Income of 50000: first 38441 at 36.97%, rest at 49.5%
        val results = with(FY2026) { berekenBox1(50000L) }
        assertEquals(2, results.size)
        assertEquals(38441L, results[0].income)
        assertEquals((38441 * 0.3697).toLong(), results[0].tax)
    }

    @Test
    fun `startersaftrek constant is 2123`() {
        assertEquals(2123L, STARTERSAFTREK)
    }

    @Test
    fun `fiscalYearOf returns correct types`() {
        assertTrue(fiscalYearOf(2022) is FY2022)
        assertTrue(fiscalYearOf(2023) is FY2023)
        assertTrue(fiscalYearOf(2024) is FY2024)
        assertTrue(fiscalYearOf(2025) is FY2025)
        assertTrue(fiscalYearOf(2026) is FY2026)
    }
}
