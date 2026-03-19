<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="slide-inner">
      <div class="eyebrow">CROSS-BORDER COMPARISON</div>
      <h2 class="title">Netherlands vs Belgium</h2>
      <p class="subtitle">Same income, different rules — Laura at €88,000</p>
      <div class="comparison">
        <div class="country-card nl">
          <div class="country-flag">🇳🇱</div>
          <div class="country-name">Nederland</div>
          <div class="schijf-list">
            <div class="schijf-row">
              <span class="schijf-label">≤€38,441</span>
              <span class="schijf-rate cyan">36.97%</span>
            </div>
            <div class="schijf-row">
              <span class="schijf-label">Boven</span>
              <span class="schijf-rate cyan">49.50%</span>
            </div>
          </div>
          <div class="deductions-note">+ Zelfstandigenaftrek<br/>+ MKB-vrijstelling<br/>+ KIA</div>
          <div class="country-tax">€31,791</div>
          <div class="country-eff">~36.1% effectief</div>
        </div>
        <div class="vs-col">vs</div>
        <div class="country-card be">
          <div class="country-flag">🇧🇪</div>
          <div class="country-name">België</div>
          <div class="schijf-list">
            <div class="schijf-row"><span class="schijf-label">≤€15,820</span><span class="schijf-rate purple">25%</span></div>
            <div class="schijf-row"><span class="schijf-label">≤€27,920</span><span class="schijf-rate purple">40%</span></div>
            <div class="schijf-row"><span class="schijf-label">≤€48,320</span><span class="schijf-rate purple">45%</span></div>
            <div class="schijf-row"><span class="schijf-label">Boven</span><span class="schijf-rate purple">50%</span></div>
          </div>
          <div class="deductions-note">No entrepreneur deductions</div>
          <div class="country-tax">€37,815</div>
          <div class="country-eff">~43.0% effectief</div>
        </div>
      </div>
      <div class="diff-banner">
        Laura pays €6,024 less per year in the Netherlands due to entrepreneur deductions
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 860px; padding: 32px 64px; display: flex; flex-direction: column; gap: 20px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 42px; font-weight: 700; color: #e2e8f0; margin: 0; }
.subtitle { font-size: 15px; color: #8896a8; margin: 0; }
.comparison { display: grid; grid-template-columns: 1fr 60px 1fr; gap: 0; align-items: stretch; }
.vs-col { display: flex; align-items: center; justify-content: center; font-size: 24px; color: rgba(124,58,237,0.3); }
.country-card { border-radius: 10px; padding: 20px; display: flex; flex-direction: column; gap: 12px; }
.nl { background: rgba(6,182,212,0.06); border: 1px solid rgba(6,182,212,0.25); }
.be { background: rgba(168,85,247,0.06); border: 1px solid rgba(168,85,247,0.25); }
.country-flag { font-size: 28px; }
.country-name { font-size: 18px; font-weight: 700; color: #e2e8f0; }
.schijf-list { display: flex; flex-direction: column; gap: 4px; }
.schijf-row { display: flex; justify-content: space-between; font-size: 13px; }
.schijf-label { color: #8896a8; }
.schijf-rate { font-weight: 600; }
.schijf-rate.cyan { color: #06b6d4; }
.schijf-rate.purple { color: #a855f7; }
.deductions-note { font-size: 12px; color: #475569; border-top: 1px solid rgba(30,58,95,0.4); padding-top: 8px; line-height: 1.6; }
.country-tax { font-size: 32px; font-weight: 700; color: #e2e8f0; }
.country-eff { font-size: 12px; color: #8896a8; }
.diff-banner { background: rgba(16,185,129,0.08); border: 1px solid rgba(16,185,129,0.25); border-radius: 8px; padding: 14px 20px; font-size: 14px; color: #10b981; text-align: center; }
</style>
