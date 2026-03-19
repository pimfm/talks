<script setup lang="ts">
import { ref, onMounted } from 'vue'
import type { TarievenReport, FiscalYear } from '@/types/tax'
import { opentax } from '@/api/opentax'

const years: FiscalYear[] = [2022, 2023, 2024, 2025, 2026]
const selectedYear = ref<FiscalYear>(2026)
const report = ref<TarievenReport | null>(null)
const loading = ref(false)
const error = ref<string | null>(null)

async function loadTarieven(year: FiscalYear) {
  loading.value = true
  error.value = null
  selectedYear.value = year
  try {
    report.value = await opentax.nlTarieven(year)
  } catch (e) {
    error.value = 'Kan tarieven niet laden — is de server actief?'
    report.value = null
  } finally {
    loading.value = false
  }
}

onMounted(() => loadTarieven(2026))

function formatEur(n: number): string {
  return new Intl.NumberFormat('nl-NL', { style: 'currency', currency: 'EUR', maximumFractionDigits: 0 }).format(n)
}
function formatPct(n: number): string { return `${(n * 100).toFixed(2)}%` }

// Evolution data: [2022, 2023, 2024, 2025, 2026]
const zaSeries = [6310, 5030, 3750, 2470, 1200]
const zaMax = 6310
</script>

<template>
  <div class="tarieven">
    <div class="page-header">
      <h1>Belastingtarieven</h1>
      <p class="page-desc">Box 1, ondernemersaftrek en MKB-vrijstelling per jaar</p>
    </div>

    <div class="year-tabs">
      <button
        v-for="y in years"
        :key="y"
        :class="['year-tab', { active: selectedYear === y }]"
        @click="loadTarieven(y)"
      >{{ y }}</button>
    </div>

    <div v-if="error" class="error-msg">{{ error }}</div>
    <div v-if="loading" class="loading">Laden...</div>

    <div v-if="report && !loading" class="content">
      <div class="grid-2col">
        <div class="card">
          <div class="card-title">Box 1 — Schijven</div>
          <div class="schijf-list">
            <div v-for="(s, i) in report.box1Schijven" :key="i" class="schijf-item">
              <div class="schijf-info">
                <span class="schijf-desc">{{ s.description }}</span>
                <span class="schijf-rate-badge">{{ formatPct(s.rate) }}</span>
              </div>
              <div class="schijf-viz-track">
                <div
                  class="schijf-viz-bar"
                  :style="{
                    width: `${(s.rate / 0.5) * 100}%`,
                    background: i === 0 ? 'var(--cyan)' : 'var(--purple-light)'
                  }"
                />
              </div>
            </div>
          </div>
        </div>

        <div class="card">
          <div class="card-title">Ondernemersaftrek</div>
          <div class="aftrek-list">
            <div class="aftrek-row">
              <span class="aftrek-label">Zelfstandigenaftrek</span>
              <span class="aftrek-amount cyan">{{ formatEur(report.zelfstandigenaftrek) }}</span>
            </div>
            <div class="aftrek-row">
              <span class="aftrek-label">Startersaftrek</span>
              <span class="aftrek-amount purple">{{ formatEur(report.startersaftrek) }}</span>
            </div>
            <div class="aftrek-row">
              <span class="aftrek-label">MKB-winstvrijstelling</span>
              <span class="aftrek-amount green">{{ formatPct(report.mkbWinstvrijstelling) }}</span>
            </div>
            <div class="aftrek-row">
              <span class="aftrek-label">Box 3 fictief rendement</span>
              <span class="aftrek-amount yellow">{{ formatPct(report.box3FictitiousRate) }}</span>
            </div>
          </div>
        </div>
      </div>

      <div class="card evolution">
        <div class="card-title">Zelfstandigenaftrek 2022–2026 — afbouw</div>
        <div class="evo-chart">
          <div v-for="(za, i) in zaSeries" :key="i" class="evo-col">
            <div class="evo-amount">{{ formatEur(za) }}</div>
            <div class="evo-bar-track">
              <div
                class="evo-bar"
                :class="{ active: years[i] === selectedYear }"
                :style="{ height: `${(za / zaMax) * 100}%` }"
              />
            </div>
            <div class="evo-year">{{ years[i] }}</div>
          </div>
        </div>
        <div class="evo-note">
          Zelfstandigenaftrek daalt van €6.310 (2022) naar €1.200 (2026). Afgebouwd om zzp'ers en werknemers fiscaal gelijker te stellen.
        </div>
      </div>

      <div class="card for-note" v-if="selectedYear <= 2022">
        <div class="card-title">Fiscale Oudedagsreserve (FOR) — alleen 2022</div>
        <div class="for-content">
          <div class="for-code">
            <pre><code><span class="kw">context</span>(<span class="type">year</span>: <span class="type">FY2022</span>)
<span class="kw">fun</span> <span class="type">Raise</span>&lt;<span class="type">TaxError</span>&gt;.<span class="fn">fiscaleOudedagsreserve</span>(winst: <span class="type">Long</span>): <span class="type">Long</span></code></pre>
          </div>
          <p>Maximale dotatie: min(9,44% × winst, €9.632). De FOR bestaat niet meer — de context-parameter definitie met <code>FY2022</code> maakt het onmogelijk om FOR in 2023 te berekenen: <strong>compile error</strong>.</p>
        </div>
      </div>
      <div class="card for-note abolished" v-else>
        <div class="card-title">Fiscale Oudedagsreserve (FOR) — afgeschaft</div>
        <p>Per 2023 is de FOR afgeschaft. In code: er bestaat geen <code>context(FY{{ selectedYear }})</code> variant van <code>fiscaleOudedagsreserve()</code>. Aanroepen → compile error.</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.tarieven { display: flex; flex-direction: column; gap: 24px; }

.page-header { display: flex; flex-direction: column; gap: 6px; }
.page-header h1 { font-size: 26px; font-weight: 700; }
.page-desc { font-size: 14px; color: var(--text-muted); }

.year-tabs { display: flex; gap: 4px; }
.year-tab {
  background: none;
  border: 1px solid var(--border);
  color: var(--text-muted);
  padding: 8px 20px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.15s;
}
.year-tab:hover { color: var(--text); border-color: rgba(124,58,237,0.4); }
.year-tab.active {
  color: var(--purple-light);
  border-color: var(--purple);
  background: rgba(124,58,237,0.1);
}

.error-msg { color: var(--red); font-size: 14px; padding: 12px; background: rgba(239,68,68,0.1); border-radius: 6px; }
.loading { color: var(--text-muted); font-size: 14px; }

.content { display: flex; flex-direction: column; gap: 20px; }
.grid-2col { display: grid; grid-template-columns: 1fr 1fr; gap: 20px; }

.card {
  background: var(--bg2);
  border: 1px solid var(--border);
  border-radius: 8px;
  padding: 24px;
  display: flex;
  flex-direction: column;
  gap: 16px;
}
.card-title { font-size: 12px; color: var(--text-muted); text-transform: uppercase; letter-spacing: 0.1em; font-weight: 600; }

.schijf-list { display: flex; flex-direction: column; gap: 12px; }
.schijf-item { display: flex; flex-direction: column; gap: 6px; }
.schijf-info { display: flex; justify-content: space-between; align-items: center; }
.schijf-desc { font-size: 14px; color: var(--text); }
.schijf-rate-badge { font-size: 16px; font-weight: 700; color: var(--text); }
.schijf-viz-track { height: 6px; background: rgba(255,255,255,0.06); border-radius: 3px; overflow: hidden; }
.schijf-viz-bar { height: 100%; border-radius: 3px; transition: width 0.5s ease; }

.aftrek-list { display: flex; flex-direction: column; gap: 12px; }
.aftrek-row { display: flex; justify-content: space-between; align-items: center; }
.aftrek-label { font-size: 14px; color: var(--text-muted); }
.aftrek-amount { font-size: 18px; font-weight: 700; }
.aftrek-amount.cyan { color: var(--cyan); }
.aftrek-amount.purple { color: var(--purple-light); }
.aftrek-amount.green { color: var(--green); }
.aftrek-amount.yellow { color: var(--yellow); }

.evolution { }
.evo-chart {
  display: flex;
  gap: 16px;
  align-items: flex-end;
  height: 120px;
}
.evo-col { flex: 1; display: flex; flex-direction: column; align-items: center; gap: 4px; }
.evo-amount { font-size: 11px; color: var(--text-muted); }
.evo-bar-track { flex: 1; width: 100%; display: flex; align-items: flex-end; }
.evo-bar {
  width: 100%;
  background: rgba(124,58,237,0.3);
  border-radius: 3px 3px 0 0;
  transition: background 0.2s;
  border: 1px solid rgba(124,58,237,0.4);
}
.evo-bar.active { background: var(--purple-light); border-color: var(--purple-light); }
.evo-year { font-size: 12px; color: var(--text-muted); }
.evo-note { font-size: 12px; color: var(--text-dim); line-height: 1.5; }

.for-note { }
.for-note.abolished { border-left: 3px solid var(--red); }
.for-content { display: flex; flex-direction: column; gap: 12px; }
.for-code {
  background: var(--bg3);
  border-radius: 6px;
  padding: 12px 16px;
  font-size: 12px;
}
.for-note p { font-size: 13px; color: var(--text-muted); line-height: 1.6; }
.for-note strong { color: var(--red); }
.for-note code { color: var(--cyan); font-size: 12px; }
.kw { color: #c084fc; }
.type { color: #67e8f9; }
.fn { color: #a3e635; }
</style>
