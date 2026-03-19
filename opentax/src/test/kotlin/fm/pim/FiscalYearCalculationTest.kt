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

    @Test
    fun `KIA threshold differs per fiscal year`() {
        // FY2022 threshold is 2401 — investment of 2402 qualifies
        val kia2022 = with(FY2022) { kia(2402L) }
        assertTrue(kia2022 > 0L, "2402 should give KIA in FY2022 (threshold=2401)")

        // FY2026 threshold is 2900 — investment of 2402 does NOT qualify
        val kia2026 = with(FY2026) { kia(2402L) }
        assertEquals(0L, kia2026, "2402 should give no KIA in FY2026 (threshold=2900)")
    }

    @Test
    fun `KIA max deduction increases each year`() {
        // At a large investment (€150_000) we should be at or near maxFixed for each year
        val maxFixed2022 = with(FY2022) { kiaBrackets().maxFixed }
        val maxFixed2026 = with(FY2026) { kiaBrackets().maxFixed }
        assertTrue(maxFixed2026 > maxFixed2022, "Max KIA deduction should increase from 2022 to 2026")
        assertEquals(17841L, maxFixed2022)
        assertEquals(20072L, maxFixed2026)
    }
}
