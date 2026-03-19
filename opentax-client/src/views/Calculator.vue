<script setup lang="ts">
import { ref, reactive } from 'vue'
import type { AangifteInput, TaxReport, ComparisonReport, FiscalYear } from '@/types/tax'
import { opentax } from '@/api/opentax'
import Report from './Report.vue'

const loading = ref(false)
const error = ref<string | null>(null)
const report = ref<TaxReport | null>(null)
const comparison = ref<ComparisonReport | null>(null)

const form = reactive<AangifteInput>({
  fiscalYear: 2026,
  grossIncome: 0,
  hoursInBusiness: undefined,
  investment: undefined,
  isStarter: false,
  hasPartner: false,
  box3Assets: undefined
})

const compareWithBE = ref(false)

async function submit() {
  loading.value = true
  error.value = null
  report.value = null
  comparison.value = null

  try {
    const input: AangifteInput = {
      ...form,
      hoursInBusiness: form.hoursInBusiness || undefined,
      investment: form.investment || undefined,
      box3Assets: form.box3Assets || undefined
    }
    report.value = await opentax.nlAangifte(input)
    if (compareWithBE.value) {
      comparison.value = await opentax.beAangifte(input)
    }
  } catch (e) {
    error.value = e instanceof Error ? e.message : 'Onbekende fout'
  } finally {
    loading.value = false
  }
}

function loadRichard() {
  form.fiscalYear = 2026
  form.grossIncome = 800
  form.hoursInBusiness = 50
  form.investment = 300
  form.isStarter = false
  form.hasPartner = false
  form.box3Assets = undefined
}

function loadLaura() {
  form.fiscalYear = 2026
  form.grossIncome = 88000
  form.hoursInBusiness = 1400
  form.investment = 6500
  form.isStarter = false
  form.hasPartner = true
  form.box3Assets = undefined
}
</script>

<template>
  <div class="calculator">
    <div class="page-header">
      <h1>Belastingaangifte</h1>
      <p class="page-desc">Bereken uw Nederlandse inkomstenbelasting voor {{ form.fiscalYear }}</p>
    </div>

    <div class="layout">
      <div class="form-panel">
        <div class="preset-bar">
          <span class="preset-label">Laad voorbeeld:</span>
          <button class="preset-btn richard" @click="loadRichard">Richard</button>
          <button class="preset-btn laura" @click="loadLaura">Laura</button>
        </div>

        <form @submit.prevent="submit" class="form">
          <div class="form-section">
            <div class="form-group">
              <label>Belastingjaar</label>
              <select v-model.number="form.fiscalYear">
                <option :value="2022">2022</option>
                <option :value="2023">2023</option>
                <option :value="2024">2024</option>
                <option :value="2025">2025</option>
                <option :value="2026">2026</option>
              </select>
            </div>

            <div class="form-group">
              <label>Bruto inkomen / winst (€)</label>
              <input
                type="number"
                v-model.number="form.grossIncome"
                min="0"
                placeholder="0"
              />
            </div>
          </div>

          <div class="form-section">
            <div class="section-label">Ondernemerschap</div>
            <div class="form-group">
              <label>Gewerkte uren (voor urencriterium)</label>
              <input
                type="number"
                v-model.number="form.hoursInBusiness"
                min="0"
                max="8760"
                placeholder="Leeg = geen ondernemer"
              />
              <span class="form-hint">Minimaal 1.225 uur voor ondernemersaftrek</span>
            </div>

            <div class="form-group">
              <label>Investering (€, voor KIA)</label>
              <input
                type="number"
                v-model.number="form.investment"
                min="0"
                placeholder="0"
              />
            </div>

            <div class="form-check">
              <input type="checkbox" id="starter" v-model="form.isStarter" />
              <label for="starter">Starter (eerste 3 jaar — +€2.123 startersaftrek)</label>
            </div>
          </div>

          <div class="form-section">
            <div class="section-label">Vermogen</div>
            <div class="form-group">
              <label>Box 3 vermogen (€)</label>
              <input
                type="number"
                v-model.number="form.box3Assets"
                min="0"
                placeholder="0"
              />
            </div>
          </div>

          <div class="form-section">
            <div class="form-check">
              <input type="checkbox" id="compare" v-model="compareWithBE" />
              <label for="compare">Vergelijk met België (zelfde inkomen)</label>
            </div>
          </div>

          <div v-if="error" class="form-error">{{ error }}</div>

          <button type="submit" class="submit-btn" :disabled="loading">
            {{ loading ? 'Berekenen...' : 'Bereken aangifte →' }}
          </button>
        </form>
      </div>

      <div v-if="report" class="result-panel">
        <Report :report="report" :comparison="comparison" />
      </div>
    </div>
  </div>
</template>

<style scoped>
.calculator { display: flex; flex-direction: column; gap: 24px; }

.page-header { display: flex; flex-direction: column; gap: 6px; }
.page-header h1 { font-size: 26px; font-weight: 700; }
.page-desc { font-size: 14px; color: var(--text-muted); }

.layout { display: grid; grid-template-columns: 380px 1fr; gap: 32px; align-items: start; }

.form-panel {
  background: var(--bg2);
  border: 1px solid var(--border);
  border-radius: 8px;
  overflow: hidden;
  position: sticky;
  top: 80px;
}

.preset-bar {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 12px 20px;
  background: rgba(255,255,255,0.02);
  border-bottom: 1px solid var(--border);
}
.preset-label { font-size: 11px; color: var(--text-dim); flex: 1; }
.preset-btn {
  font-size: 12px;
  padding: 4px 12px;
  border-radius: 4px;
  border: 1px solid var(--border);
  background: none;
  cursor: pointer;
  transition: all 0.15s;
}
.preset-btn.richard { color: var(--yellow); border-color: rgba(245,158,11,0.3); }
.preset-btn.richard:hover { background: rgba(245,158,11,0.1); }
.preset-btn.laura { color: var(--cyan); border-color: rgba(6,182,212,0.3); }
.preset-btn.laura:hover { background: rgba(6,182,212,0.1); }

.form { padding: 20px; display: flex; flex-direction: column; gap: 20px; }

.form-section { display: flex; flex-direction: column; gap: 12px; }
.section-label { font-size: 11px; color: var(--purple-light); text-transform: uppercase; letter-spacing: 0.1em; }

.form-group { display: flex; flex-direction: column; gap: 4px; }
.form-group label { font-size: 12px; color: var(--text-muted); }
.form-group input, .form-group select {
  background: var(--bg3);
  border: 1px solid var(--border);
  color: var(--text);
  padding: 8px 12px;
  border-radius: 4px;
  font-size: 14px;
  outline: none;
  transition: border-color 0.15s;
}
.form-group input:focus, .form-group select:focus { border-color: var(--purple-light); }
.form-hint { font-size: 11px; color: var(--text-dim); }

.form-check { display: flex; align-items: center; gap: 8px; }
.form-check input[type="checkbox"] { accent-color: var(--purple); width: 16px; height: 16px; }
.form-check label { font-size: 13px; color: var(--text-muted); cursor: pointer; }

.form-error {
  background: rgba(239,68,68,0.1);
  border: 1px solid rgba(239,68,68,0.3);
  border-radius: 6px;
  padding: 10px 14px;
  font-size: 13px;
  color: var(--red);
}

.submit-btn {
  background: var(--purple);
  color: white;
  border: none;
  padding: 12px;
  border-radius: 6px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.15s;
  width: 100%;
}
.submit-btn:hover:not(:disabled) { background: var(--purple-light); }
.submit-btn:disabled { opacity: 0.5; cursor: not-allowed; }

.result-panel { min-width: 0; }
</style>
