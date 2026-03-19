<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />
    <div class="slide-inner">
      <div class="eyebrow">ZELFSTANDIGENAFTREK 2022 → 2026</div>
      <h2 class="title">The deduction that keeps <span class="accent">shrinking</span></h2>

      <div class="bars">
        <div v-for="yr in years" :key="yr.year" class="bar-row">
          <div class="bar-year">{{ yr.year }}</div>
          <div class="bar-track">
            <div
              class="bar-fill"
              :style="{ width: (yr.amount / 6310 * 100) + '%', opacity: yr.year === 2026 ? 1 : 0.55 + (yr.year - 2022) * 0.1 }"
            />
          </div>
          <div class="bar-amount" :class="{ highlight: yr.year === 2026 }">€{{ yr.amount.toLocaleString('nl-NL') }}</div>
        </div>
      </div>

      <div class="note-row">
        <div class="note">
          <span class="note-icon">↓</span>
          Down <strong>81%</strong> from 2022 to 2026
        </div>
        <div class="note highlight-note">
          In 2026: Laura saves <strong>€450</strong> (€1,200 × 37.56%), not €2,369 (2022 rate)
        </div>
      </div>

      <div class="code-aside">
        <pre><code><span class="comment">// Same function, different year, different value</span>
<span class="kw">context</span>(<span class="id">year</span>: <span class="type">FiscalYear</span>)
<span class="kw">fun</span> <span class="fn">zelfstandigenaftrek</span>(): <span class="type">Long</span> = <span class="kw">when</span> (year) {
  <span class="kw">is</span> <span class="type">FY2022</span> -&gt; <span class="num">6_310L</span>   <span class="kw">is</span> <span class="type">FY2023</span> -&gt; <span class="num">5_030L</span>
  <span class="kw">is</span> <span class="type">FY2025</span> -&gt; <span class="num">2_470L</span>   <span class="kw">is</span> <span class="type">FY2026</span> -&gt; <span class="num">1_200L</span>
}</code></pre>
      </div>
    </div>
  </div>
</template>

<script setup>
const years = [
  { year: 2022, amount: 6310 },
  { year: 2023, amount: 5030 },
  { year: 2024, amount: 3750 },
  { year: 2025, amount: 2470 },
  { year: 2026, amount: 1200 },
]
</script>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); pointer-events: none; }
.orb { position: absolute; width: 400px; height: 400px; border-radius: 50%; filter: blur(100px); background: radial-gradient(circle, #f59e0b 0%, #92400e 60%, transparent 100%); top: -150px; right: -100px; opacity: 0.15; pointer-events: none; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 820px; padding: 32px 64px; display: flex; flex-direction: column; gap: 18px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 38px; font-weight: 700; color: #e2e8f0; margin: 0; letter-spacing: -0.02em; }
.accent { color: #f59e0b; }
.bars { display: flex; flex-direction: column; gap: 10px; }
.bar-row { display: flex; align-items: center; gap: 14px; }
.bar-year { font-size: 13px; font-weight: 600; color: #8896a8; width: 38px; flex-shrink: 0; }
.bar-track { flex: 1; height: 12px; background: rgba(30,58,95,0.4); border-radius: 6px; overflow: hidden; }
.bar-fill { height: 100%; background: linear-gradient(90deg, #a855f7, #7c3aed); border-radius: 6px; transition: width 0.6s ease; }
.bar-amount { font-size: 13px; font-weight: 600; color: #94a3b8; width: 70px; text-align: right; flex-shrink: 0; }
.bar-amount.highlight { color: #a855f7; font-weight: 700; }
.note-row { display: flex; gap: 16px; flex-wrap: wrap; }
.note { font-size: 13px; color: #8896a8; padding: 10px 14px; border-radius: 6px; background: rgba(30,58,95,0.2); border: 1px solid rgba(30,58,95,0.4); }
.note strong { color: #e2e8f0; }
.note-icon { color: #a855f7; margin-right: 6px; }
.highlight-note { border-color: rgba(124,58,237,0.35); background: rgba(124,58,237,0.07); }
.highlight-note strong { color: #a855f7; }
.code-aside { background: rgba(30,58,95,0.15); border: 1px solid rgba(30,58,95,0.4); border-radius: 8px; }
pre { margin: 0; padding: 12px 16px; font-size: 12px; line-height: 1.65; }
.kw { color: #c084fc; }
.type { color: #67e8f9; }
.fn { color: #a3e635; }
.id { color: #e2e8f0; }
.num { color: #fb923c; }
.comment { color: #475569; }
</style>
