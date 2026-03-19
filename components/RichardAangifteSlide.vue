<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />
    <div class="slide-inner">
      <div class="char-header">
        <img class="avatar" src="https://api.dicebear.com/9.x/adventurer/svg?seed=Daan&backgroundColor=0f0b1e" alt="Richard" />
        <div>
          <div class="eyebrow">AANGIFTE WALKTHROUGH</div>
          <h2 class="title">Richard's Box 1</h2>
        </div>
      </div>
      <div class="calc-steps">
        <div class="step">
          <div class="step-label">Gross income</div>
          <div class="step-value">€ 800</div>
        </div>
        <div class="step hours-check">
          <div class="step-label">Urencriterium check</div>
          <div class="step-code">
            <pre><code><span class="kw">context</span>(<span class="id">year</span>: <span class="type">FiscalYear</span>)
<span class="kw">fun</span> <span class="type">Raise</span>&lt;<span class="type">TaxError</span>&gt;.<span class="fn">checkUrencriterium</span>(hours: <span class="type">Int</span>) =
  ensure(hours &gt;= 1225) { UrencriteriumNotMet(hours) }</code></pre>
          </div>
          <div class="step-result error">50h &lt; 1225h → UrencriteriumNotMet(50)</div>
        </div>
        <div class="step">
          <div class="step-label">No entrepreneur deductions apply</div>
          <div class="step-result neutral">Taxable income = €800</div>
        </div>
        <div class="step">
          <div class="step-label">Box 1 tax at 36.97% (2026)</div>
          <div class="step-result warning">€800 × 36.97% = €295</div>
        </div>
      </div>
      <div class="verdict">
        <div class="verdict-label">Total tax</div>
        <div class="verdict-amount">€295</div>
        <div class="verdict-rate">(36.97% effective)</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.orb { position: absolute; width: 400px; height: 400px; border-radius: 50%; filter: blur(100px); background: radial-gradient(circle, #f59e0b 0%, #92400e 60%, transparent 100%); bottom: -150px; right: -100px; opacity: 0.12; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 840px; padding: 32px 64px; display: flex; flex-direction: column; gap: 18px; }
.char-header { display: flex; align-items: center; gap: 16px; }
.avatar { width: 64px; height: 64px; border-radius: 50%; border: 2px solid rgba(245,158,11,0.4); background: #0f0b1e; }
.eyebrow { font-size: 10px; font-weight: 700; letter-spacing: 0.2em; color: rgba(245,158,11,0.7); text-transform: uppercase; }
.title { font-size: 36px; font-weight: 700; color: #e2e8f0; margin: 0; }
.calc-steps { display: flex; flex-direction: column; gap: 12px; }
.step { background: rgba(255,255,255,0.02); border: 1px solid rgba(30,58,95,0.5); border-radius: 8px; padding: 12px 16px; display: flex; flex-direction: column; gap: 8px; }
.step-label { font-size: 11px; color: #8896a8; text-transform: uppercase; letter-spacing: 0.08em; }
.step-value { font-size: 20px; font-weight: 700; color: #e2e8f0; }
.step-code pre { margin: 0; font-size: 12px; line-height: 1.6; }
.step-result { font-size: 14px; font-weight: 600; padding: 6px 12px; border-radius: 4px; }
.step-result.error { background: rgba(239,68,68,0.1); color: #ef4444; border: 1px solid rgba(239,68,68,0.2); }
.step-result.neutral { background: rgba(30,58,95,0.3); color: #94a3b8; }
.step-result.warning { background: rgba(245,158,11,0.1); color: #f59e0b; }
.verdict { display: flex; align-items: center; gap: 16px; padding: 16px 24px; background: rgba(239,68,68,0.08); border: 1px solid rgba(239,68,68,0.2); border-radius: 8px; }
.verdict-label { font-size: 12px; color: #8896a8; flex: 1; }
.verdict-amount { font-size: 32px; font-weight: 700; color: #ef4444; }
.verdict-rate { font-size: 12px; color: #8896a8; }
.kw { color: #c084fc; }
.type { color: #67e8f9; }
.fn { color: #a3e635; }
.id { color: #e2e8f0; }
</style>
