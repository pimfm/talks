<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />
    <div class="slide-inner">
      <div class="char-header">
        <img class="avatar" src="https://api.dicebear.com/9.x/adventurer/svg?seed=Laura&backgroundColor=0f0b1e" alt="Laura" />
        <div>
          <div class="eyebrow">AANGIFTE WALKTHROUGH</div>
          <h2 class="title">Laura's Box 1 — All deductions</h2>
        </div>
      </div>
      <div class="calc-table">
        <div class="row gross">
          <span>Gross income</span>
          <span class="amount">€88,000</span>
        </div>
        <div class="section-sep">Ondernemersaftrek (1400h ≥ 1225h ✓)</div>
        <div class="row deduct">
          <span>Zelfstandigenaftrek (2026)</span>
          <span class="deduct-amount">− €1,200</span>
        </div>
        <div class="row deduct">
          <span>MKB-winstvrijstelling (12.7% × €86,800)</span>
          <span class="deduct-amount">− €11,023</span>
        </div>
        <div class="row deduct">
          <span>KIA (€6,500 × 28%)</span>
          <span class="deduct-amount">− €1,820</span>
        </div>
        <div class="row taxable">
          <span>Belastbaar inkomen</span>
          <span class="amount">€73,957</span>
        </div>
        <div class="section-sep">Schijven 2026</div>
        <div class="row schijf">
          <span>≤€38,441 × 36.97%</span>
          <span class="tax-amount">€14,209</span>
        </div>
        <div class="row schijf">
          <span>€35,516 × 49.50%</span>
          <span class="tax-amount">€17,580</span>
        </div>
        <div class="row total">
          <span>Totale belasting</span>
          <span class="total-amount">≈ €31,789</span>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.orb { position: absolute; width: 400px; height: 400px; border-radius: 50%; filter: blur(100px); background: radial-gradient(circle, #06b6d4 0%, #0e7490 60%, transparent 100%); top: -150px; right: -100px; opacity: 0.2; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 800px; padding: 28px 64px; display: flex; flex-direction: column; gap: 16px; }
.char-header { display: flex; align-items: center; gap: 16px; }
.avatar { width: 64px; height: 64px; border-radius: 50%; border: 2px solid rgba(6,182,212,0.4); background: #0f0b1e; }
.eyebrow { font-size: 10px; font-weight: 700; letter-spacing: 0.2em; color: rgba(6,182,212,0.7); text-transform: uppercase; }
.title { font-size: 30px; font-weight: 700; color: #e2e8f0; margin: 0; }
.calc-table { display: flex; flex-direction: column; gap: 4px; }
.row { display: flex; justify-content: space-between; align-items: center; font-size: 14px; padding: 7px 12px; border-radius: 4px; }
.row.gross { background: rgba(255,255,255,0.04); color: #e2e8f0; font-weight: 600; }
.row.deduct { color: #94a3b8; }
.row.taxable { background: rgba(6,182,212,0.08); border: 1px solid rgba(6,182,212,0.2); color: #e2e8f0; font-weight: 600; }
.row.schijf { color: #94a3b8; padding-left: 24px; }
.row.total { background: rgba(124,58,237,0.1); border: 1px solid rgba(124,58,237,0.3); font-weight: 700; color: #e2e8f0; }
.section-sep { font-size: 10px; color: #475569; text-transform: uppercase; letter-spacing: 0.1em; padding: 4px 12px; margin-top: 4px; }
.amount { color: #e2e8f0; font-weight: 700; }
.deduct-amount { color: #10b981; font-weight: 600; }
.tax-amount { color: #f59e0b; font-weight: 600; }
.total-amount { color: #a855f7; font-weight: 700; font-size: 18px; }
</style>
