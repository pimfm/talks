<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="slide-inner">
      <div class="eyebrow">TRACEABLE RESULTS</div>
      <h2 class="title">The Tax Report</h2>
      <div class="json-preview">
        <div class="json-header">
          <span class="json-file">POST /nl/aangifte → TaxReport</span>
          <span class="json-badge">@Serializable</span>
        </div>
        <pre class="json-body"><code>{
  <span class="jk">"fiscalYear"</span>: <span class="js">"FY2026"</span>,
  <span class="jk">"grossIncome"</span>: <span class="jn">88000</span>,
  <span class="jk">"box1"</span>: {
    <span class="jk">"entrepreneurDeductions"</span>: {
      <span class="jk">"zelfstandigenaftrek"</span>: <span class="jn">1200</span>,
      <span class="jk">"mkbWinstvrijstelling"</span>: <span class="jn">11023</span>,
      <span class="jk">"kia"</span>: <span class="jn">1820</span>,
      <span class="jk">"total"</span>: <span class="jn">14043</span>
    },
    <span class="jk">"taxableIncome"</span>: <span class="jn">73957</span>,
    <span class="jk">"schijven"</span>: [
      { <span class="jk">"bracket"</span>: <span class="js">"≤€38441"</span>, <span class="jk">"rate"</span>: <span class="jn">0.3697</span>, <span class="jk">"tax"</span>: <span class="jn">14209</span> },
      { <span class="jk">"bracket"</span>: <span class="js">"&gt;38441"</span>,  <span class="jk">"rate"</span>: <span class="jn">0.4950</span>, <span class="jk">"tax"</span>: <span class="jn">17580</span> }
    ]
  },
  <span class="jk">"totalTax"</span>: <span class="jn">31789</span>,
  <span class="jk">"effectiveRate"</span>: <span class="jn">0.361</span>
}</code></pre>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 860px; padding: 32px 64px; display: flex; flex-direction: column; gap: 20px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 44px; font-weight: 700; color: #e2e8f0; margin: 0; }
.json-preview { background: var(--bg3, #0d1a2e); border: 1px solid rgba(30,58,95,0.6); border-radius: 8px; overflow: hidden; }
.json-header { display: flex; justify-content: space-between; align-items: center; padding: 10px 20px; background: rgba(255,255,255,0.03); border-bottom: 1px solid rgba(30,58,95,0.5); }
.json-file { font-size: 13px; color: #a855f7; }
.json-badge { font-size: 11px; color: #06b6d4; border: 1px solid rgba(6,182,212,0.3); padding: 2px 8px; border-radius: 3px; }
.json-body { margin: 0; padding: 16px 20px; font-size: 12px; line-height: 1.7; overflow-x: auto; }
.jk { color: #67e8f9; }
.js { color: #a3e635; }
.jn { color: #fb923c; }
</style>
