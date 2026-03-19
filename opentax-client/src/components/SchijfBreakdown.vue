<script setup lang="ts">
import type { SchijfApplication } from '@/types/tax'

const props = defineProps<{
  schijven: SchijfApplication[]
  totalTax: number
}>()

function formatEur(n: number): string {
  return new Intl.NumberFormat('nl-NL', { style: 'currency', currency: 'EUR', maximumFractionDigits: 0 }).format(n)
}

function pct(rate: number): string {
  return `${(rate * 100).toFixed(2)}%`
}

function barWidth(tax: number): string {
  const max = Math.max(...props.schijven.map(s => s.tax), 1)
  return `${Math.round((tax / max) * 100)}%`
}
</script>

<template>
  <div class="breakdown">
    <div v-for="(schijf, i) in schijven" :key="i" class="schijf-row">
      <div class="schijf-header">
        <span class="schijf-bracket">{{ schijf.bracket }}</span>
        <span class="schijf-rate">{{ pct(schijf.rate) }}</span>
        <span class="schijf-income">{{ formatEur(schijf.income) }}</span>
        <span class="schijf-tax">{{ formatEur(schijf.tax) }}</span>
      </div>
      <div class="schijf-bar-track">
        <div
          :class="['schijf-bar', i === 0 ? 'bar-first' : 'bar-second']"
          :style="{ width: barWidth(schijf.tax) }"
        />
      </div>
    </div>
    <div class="schijf-total">
      <span>Totale belasting box 1</span>
      <span class="total-amount">{{ formatEur(totalTax) }}</span>
    </div>
  </div>
</template>

<style scoped>
.breakdown { display: flex; flex-direction: column; gap: 12px; }

.schijf-row { display: flex; flex-direction: column; gap: 6px; }
.schijf-header {
  display: grid;
  grid-template-columns: 1fr auto auto auto;
  gap: 16px;
  align-items: center;
  font-size: 13px;
}
.schijf-bracket { color: var(--text-muted); }
.schijf-rate { color: var(--yellow); font-weight: 600; }
.schijf-income { color: var(--text); }
.schijf-tax { color: var(--text); font-weight: 600; text-align: right; }

.schijf-bar-track {
  height: 4px;
  background: rgba(255,255,255,0.06);
  border-radius: 2px;
  overflow: hidden;
}
.schijf-bar {
  height: 100%;
  border-radius: 2px;
  transition: width 0.6s ease;
}
.bar-first { background: linear-gradient(90deg, var(--cyan), var(--purple-light)); }
.bar-second { background: linear-gradient(90deg, var(--purple), var(--pink)); }

.schijf-total {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-top: 12px;
  border-top: 1px solid var(--border);
  font-size: 14px;
  color: var(--text-muted);
}
.total-amount { color: var(--text); font-weight: 700; font-size: 18px; }
</style>
