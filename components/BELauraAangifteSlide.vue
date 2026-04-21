<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />
    <div class="slide-inner">
      <div class="char-header">
        <img class="avatar" src="https://api.dicebear.com/9.x/adventurer/svg?seed=Laura&backgroundColor=0f0b1e" alt="Laura" />
        <div>
          <div class="eyebrow">AANGIFTE WALKTHROUGH — BELGIË</div>
          <h2 class="title">Laura's Personenbelasting (Hoofdberoep, FJ2026)</h2>
        </div>
      </div>
      <div class="calc-table">
        <div class="row gross">
          <span>Bruto beroepsinkomen</span>
          <span class="amount">€ 88.000</span>
        </div>
        <div class="section-sep">Sociale bijdragen (zelfstandige hoofdberoep)</div>
        <div class="row deduct">
          <span>Sociale bijdragen (20,5% × €88.000)</span>
          <span class="deduct-amount">− €18.040</span>
        </div>
        <div class="row sub">
          <span>Netto beroepsinkomen</span>
          <span class="amount neutral">€ 69.960</span>
        </div>
        <div class="section-sep">Forfaitaire beroepskosten (KBO ✓)</div>
        <div class="row deduct">
          <span>Forfaitaire beroepskosten (max €5.870, 2026)</span>
          <span class="deduct-amount">− €5.870</span>
        </div>
        <div class="section-sep">Belastingvrije som</div>
        <div class="row deduct">
          <span>Belastingvrije som (2026)</span>
          <span class="deduct-amount">− €11.200</span>
        </div>
        <div class="row taxable">
          <span>Belastbaar inkomen</span>
          <span class="amount">€ 52.890</span>
        </div>
        <div class="section-sep">Schijven 2026</div>
        <div class="row schijf">
          <span>≤€15.820 × 25%</span>
          <span class="tax-amount">€ 3.955</span>
        </div>
        <div class="row schijf">
          <span>€12.100 × 40%</span>
          <span class="tax-amount">€ 4.840</span>
        </div>
        <div class="row schijf">
          <span>€20.400 × 45%</span>
          <span class="tax-amount">€ 9.180</span>
        </div>
        <div class="row schijf">
          <span>€4.570 × 50%</span>
          <span class="tax-amount">€ 2.285</span>
        </div>
        <div class="row total">
          <span>Totale belasting (fed.)</span>
          <span class="total-amount">€ 20.260</span>
        </div>
        <div class="row note">
          <span>+ Gemeentelijke opcentiemen (~7%) + sociale bijdragen niet meegerekend in effectief belasttarief</span>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.orb { position: absolute; width: 400px; height: 400px; border-radius: 50%; filter: blur(100px); background: radial-gradient(circle, #a855f7 0%, #4c1d95 60%, transparent 100%); top: -150px; right: -100px; opacity: 0.2; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 800px; padding: 22px 64px; display: flex; flex-direction: column; gap: 12px; }
.char-header { display: flex; align-items: center; gap: 16px; }
.avatar { width: 64px; height: 64px; border-radius: 50%; border: 2px solid rgba(168,85,247,0.4); background: #0f0b1e; }
.eyebrow { font-size: 10px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 26px; font-weight: 700; color: #e2e8f0; margin: 0; }
.calc-table { display: flex; flex-direction: column; gap: 3px; }
.row { display: flex; justify-content: space-between; align-items: center; font-size: 13px; padding: 6px 12px; border-radius: 4px; }
.row.gross { background: rgba(255,255,255,0.04); color: #e2e8f0; font-weight: 600; }
.row.deduct { color: #94a3b8; }
.row.sub { color: #94a3b8; background: rgba(255,255,255,0.02); }
.row.taxable { background: rgba(168,85,247,0.08); border: 1px solid rgba(168,85,247,0.2); color: #e2e8f0; font-weight: 600; }
.row.schijf { color: #94a3b8; padding-left: 24px; }
.row.total { background: rgba(124,58,237,0.1); border: 1px solid rgba(124,58,237,0.3); font-weight: 700; color: #e2e8f0; }
.row.note { font-size: 10px; color: #334155; padding: 4px 12px; }
.section-sep { font-size: 10px; color: #475569; text-transform: uppercase; letter-spacing: 0.1em; padding: 2px 12px; margin-top: 2px; }
.amount { color: #e2e8f0; font-weight: 700; }
.amount.neutral { color: #94a3b8; font-weight: 600; }
.deduct-amount { color: #10b981; font-weight: 600; }
.tax-amount { color: #f59e0b; font-weight: 600; }
.total-amount { color: #a855f7; font-weight: 700; font-size: 16px; }
</style>
