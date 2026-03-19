<script setup lang="ts">
import type { TaxReport, ComparisonReport } from '@/types/tax'
import SchijfBreakdown from '@/components/SchijfBreakdown.vue'
import StatBar from '@/components/StatBar.vue'

defineProps<{
  report: TaxReport
  comparison?: ComparisonReport | null
}>()

function formatEur(n: number): string {
  return new Intl.NumberFormat('nl-NL', { style: 'currency', currency: 'EUR', maximumFractionDigits: 0 }).format(n)
}

function formatPct(n: number): string {
  return `${(n * 100).toFixed(2)}%`
}
</script>

<template>
  <div class="report">
    <div class="report-header">
      <div class="report-title">
        <span class="fy-badge">{{ report.fiscalYear }}</span>
        <h2>Belastingrapport</h2>
      </div>
      <div v-if="report.errors.length > 0" class="errors-banner">
        <div v-for="err in report.errors" :key="err" class="error-item">
          ⚠ {{ err }}
        </div>
      </div>
    </div>

    <div class="stats-grid">
      <StatBar label="Bruto Inkomen" :value="formatEur(report.grossIncome)" color="cyan" />
      <StatBar label="Totale Belasting" :value="formatEur(report.totalTax)" color="purple" />
      <StatBar label="Effectief Tarief" :value="formatPct(report.effectiveRate)" color="yellow" />
      <StatBar v-if="report.box3" label="Box 3 Belasting" :value="formatEur(report.box3.tax)" color="red" />
    </div>

    <div class="section">
      <h3 class="section-title">Box 1 — Inkomen uit werk en woning</h3>
      <div class="box1-summary">
        <div class="summary-row">
          <span>Bruto inkomen</span>
          <span>{{ formatEur(report.box1.grossIncome) }}</span>
        </div>
        <div v-if="report.box1.entrepreneurDeductions" class="deductions-block">
          <div class="deductions-title">Ondernemersaftrek</div>
          <div class="summary-row indent">
            <span>Zelfstandigenaftrek</span>
            <span class="deduct">− {{ formatEur(report.box1.entrepreneurDeductions.zelfstandigenaftrek) }}</span>
          </div>
          <div v-if="report.box1.entrepreneurDeductions.startersaftrek > 0" class="summary-row indent">
            <span>Startersaftrek</span>
            <span class="deduct">− {{ formatEur(report.box1.entrepreneurDeductions.startersaftrek) }}</span>
          </div>
          <div class="summary-row indent">
            <span>MKB-winstvrijstelling</span>
            <span class="deduct">− {{ formatEur(report.box1.entrepreneurDeductions.mkbWinstvrijstelling) }}</span>
          </div>
          <div v-if="report.box1.entrepreneurDeductions.kia > 0" class="summary-row indent">
            <span>KIA (Kleinschaligheidsinvesteringsaftrek)</span>
            <span class="deduct">− {{ formatEur(report.box1.entrepreneurDeductions.kia) }}</span>
          </div>
          <div v-if="report.box1.entrepreneurDeductions.fiscaleOudedagsreserve > 0" class="summary-row indent">
            <span>Fiscale Oudedagsreserve (FOR)</span>
            <span class="deduct">− {{ formatEur(report.box1.entrepreneurDeductions.fiscaleOudedagsreserve) }}</span>
          </div>
          <div class="summary-row indent total-deduct">
            <span>Totale aftrek</span>
            <span class="deduct">− {{ formatEur(report.box1.entrepreneurDeductions.total) }}</span>
          </div>
        </div>
        <div class="summary-row taxable">
          <span>Belastbaar inkomen</span>
          <span>{{ formatEur(report.box1.taxableIncome) }}</span>
        </div>
      </div>

      <SchijfBreakdown :schijven="report.box1.schijven" :total-tax="report.box1.totalTax" />
    </div>

    <div v-if="report.box3" class="section">
      <h3 class="section-title">Box 3 — Vermogensrendementsheffing</h3>
      <div class="box1-summary">
        <div class="summary-row">
          <span>Vermogen</span>
          <span>{{ formatEur(report.box3.assets) }}</span>
        </div>
        <div class="summary-row">
          <span>Fictief rendement ({{ formatPct(report.box3.fictitiousReturn) }})</span>
          <span>{{ formatEur(report.box3.taxableReturn) }}</span>
        </div>
        <div class="summary-row taxable">
          <span>Belasting (36%)</span>
          <span>{{ formatEur(report.box3.tax) }}</span>
        </div>
      </div>
    </div>

    <div v-if="comparison" class="section">
      <h3 class="section-title">Vergelijking NL vs BE</h3>
      <div class="comparison-grid">
        <div class="comparison-card nl">
          <div class="cc-country">🇳🇱 Nederland</div>
          <div class="cc-tax">{{ formatEur(comparison.nl.totalTax) }}</div>
          <div class="cc-rate">{{ formatPct(comparison.nlEffectiveRate) }} effectief</div>
        </div>
        <div class="comparison-sep">vs</div>
        <div class="comparison-card be">
          <div class="cc-country">🇧🇪 België</div>
          <div class="cc-tax">{{ formatEur(comparison.be.totalTax) }}</div>
          <div class="cc-rate">{{ formatPct(comparison.beEffectiveRate) }} effectief</div>
        </div>
      </div>
      <div class="difference" :class="comparison.difference < 0 ? 'diff-nl-lower' : 'diff-be-lower'">
        Verschil: {{ formatEur(Math.abs(comparison.difference)) }}
        {{ comparison.difference < 0 ? '(NL betaalt minder)' : '(BE betaalt minder)' }}
      </div>
    </div>
  </div>
</template>

<style scoped>
.report { display: flex; flex-direction: column; gap: 32px; }

.report-header { display: flex; flex-direction: column; gap: 12px; }
.report-title { display: flex; align-items: center; gap: 12px; }
.fy-badge {
  font-size: 11px;
  background: rgba(124,58,237,0.2);
  color: var(--purple-light);
  border: 1px solid rgba(124,58,237,0.4);
  padding: 3px 10px;
  border-radius: 3px;
}
.report-title h2 { font-size: 22px; font-weight: 600; }

.errors-banner {
  background: rgba(239,68,68,0.1);
  border: 1px solid rgba(239,68,68,0.3);
  border-radius: 6px;
  padding: 12px 16px;
}
.error-item { font-size: 13px; color: var(--red); }

.stats-grid { display: grid; grid-template-columns: repeat(4, 1fr); gap: 16px; }

.section {
  background: var(--bg2);
  border: 1px solid var(--border);
  border-radius: 8px;
  padding: 24px;
  display: flex;
  flex-direction: column;
  gap: 20px;
}
.section-title { font-size: 14px; font-weight: 600; color: var(--text-muted); text-transform: uppercase; letter-spacing: 0.06em; }

.box1-summary { display: flex; flex-direction: column; gap: 8px; }
.summary-row {
  display: flex;
  justify-content: space-between;
  font-size: 13px;
  color: var(--text-muted);
  padding: 4px 0;
}
.summary-row.indent { padding-left: 16px; }
.summary-row.taxable {
  border-top: 1px solid var(--border);
  padding-top: 8px;
  margin-top: 4px;
  color: var(--text);
  font-weight: 600;
}
.summary-row.total-deduct { color: var(--text); font-weight: 600; }

.deductions-block {
  background: rgba(6,182,212,0.05);
  border: 1px solid rgba(6,182,212,0.15);
  border-radius: 6px;
  padding: 12px 16px;
  display: flex;
  flex-direction: column;
  gap: 4px;
}
.deductions-title { font-size: 11px; color: var(--cyan); text-transform: uppercase; letter-spacing: 0.1em; margin-bottom: 4px; }
.deduct { color: var(--green); }

.comparison-grid { display: flex; align-items: center; gap: 20px; }
.comparison-card {
  flex: 1;
  background: var(--bg3);
  border: 1px solid var(--border);
  border-radius: 6px;
  padding: 20px;
  text-align: center;
}
.comparison-card.nl { border-top: 2px solid var(--cyan); }
.comparison-card.be { border-top: 2px solid var(--purple-light); }
.cc-country { font-size: 12px; color: var(--text-muted); margin-bottom: 8px; }
.cc-tax { font-size: 28px; font-weight: 700; }
.cc-rate { font-size: 12px; color: var(--text-muted); margin-top: 4px; }
.comparison-sep { color: var(--text-dim); font-size: 18px; }

.difference {
  text-align: center;
  font-size: 14px;
  padding: 12px;
  border-radius: 6px;
}
.diff-nl-lower { background: rgba(16,185,129,0.1); color: var(--green); }
.diff-be-lower { background: rgba(239,68,68,0.1); color: var(--red); }
</style>
