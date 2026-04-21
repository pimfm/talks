<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="slide-inner">
      <div class="eyebrow">INVESTERINGSAFTREK — CROSS-BORDER</div>
      <h2 class="title">Laura invests <span class="accent">€ 6,500</span></h2>

      <div class="code-block">
        <div class="code-header">
          <span class="file">Investeringsaftrek.kt</span>
        </div>
        <pre><code><span class="comment">// NL — KIA (all years)</span>
<span class="kw">context</span>(<span class="id">year</span>: <span class="type">FiscalYear</span>)
<span class="kw">fun</span> <span class="fn">kia</span>(investment: <span class="type">Long</span>): <span class="type">Long</span>  <span class="comment">// 28% at €6,500</span>

<span class="comment">// BE — Gewone aftrek (abolished from FY2024)</span>
<span class="kw">context</span>(<span class="id">year</span>: <span class="type">FY2023</span>)
<span class="kw">fun</span> <span class="fn">gewoneInvesteringsaftrek</span>(investering: <span class="type">Long</span>): <span class="type">Long</span>  <span class="comment">// 8%</span>

<span class="comment">// BE — Verhoogde aftrek (new from FY2024)</span>
<span class="kw">context</span>(<span class="id">year</span>: <span class="type">VerhoogdeInvesteringsaftrekJaar</span>)
<span class="kw">fun</span> <span class="fn">verhoogdeInvesteringsaftrek</span>(investering: <span class="type">Long</span>, type: <span class="type">InvesteringsType</span>): <span class="type">Long</span></code></pre>
      </div>

      <div class="comparison">
        <div class="comp-card nl">
          <div class="comp-flag">🇳🇱</div>
          <div class="comp-name">NL — KIA (FY2026)</div>
          <div class="comp-math">€6,500 × 28%</div>
          <div class="comp-result">€ 1,820</div>
        </div>

        <div class="comp-card be-old">
          <div class="comp-flag">🇧🇪</div>
          <div class="comp-name">BE — Gewone aftrek (FY2023)</div>
          <div class="comp-math">€6,500 × 8%</div>
          <div class="comp-result old-val">€ 520</div>
          <div class="abolished-tag">Abolished FY2024</div>
        </div>

        <div class="comp-card be-new">
          <div class="comp-flag">🇧🇪</div>
          <div class="comp-name">BE — Verhoogde aftrek (FY2024+)</div>
          <div class="comp-math">€6,500 × 40% (digitalisation)</div>
          <div class="comp-result new-val">€ 2,600</div>
          <div class="better-tag">Better than NL KIA!</div>
        </div>
      </div>

      <div class="compile-box">
        <pre><code><span class="kw">with</span>(FY2024) {
    <span class="fn">gewoneInvesteringsaftrek</span>(6_500L)
    <span class="error-line">// Compile error: FY2023 context not found</span>
    <span class="comment">// Use verhoogdeInvesteringsaftrek(6_500L, DIGITALISERING)</span>
}</code></pre>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); pointer-events: none; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 860px; padding: 28px 64px; display: flex; flex-direction: column; gap: 16px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 36px; font-weight: 700; color: #e2e8f0; margin: 0; letter-spacing: -0.02em; }
.accent { color: #67e8f9; }
.code-block { background: rgba(124,58,237,0.06); border: 1px solid rgba(124,58,237,0.3); border-radius: 8px; overflow: hidden; }
.code-header { display: flex; justify-content: space-between; align-items: center; padding: 6px 16px; background: rgba(124,58,237,0.1); border-bottom: 1px solid rgba(124,58,237,0.2); }
.file { font-size: 12px; color: #a855f7; }
pre { margin: 0; padding: 12px 16px; font-size: 12px; line-height: 1.65; }
.comparison { display: flex; gap: 12px; }
.comp-card { flex: 1; border-radius: 10px; padding: 14px 16px; display: flex; flex-direction: column; gap: 6px; }
.nl { background: rgba(6,182,212,0.06); border: 1px solid rgba(6,182,212,0.25); }
.be-old { background: rgba(239,68,68,0.05); border: 1px solid rgba(239,68,68,0.2); }
.be-new { background: rgba(16,185,129,0.06); border: 1px solid rgba(16,185,129,0.25); }
.comp-flag { font-size: 22px; }
.comp-name { font-size: 12px; font-weight: 700; color: #94a3b8; }
.comp-math { font-size: 12px; color: #64748b; }
.comp-result { font-size: 28px; font-weight: 700; letter-spacing: -0.02em; }
.nl .comp-result { color: #67e8f9; }
.old-val { color: #fca5a5; }
.new-val { color: #6ee7b7; }
.abolished-tag { font-size: 10px; font-weight: 700; color: #ef4444; text-transform: uppercase; letter-spacing: 0.1em; background: rgba(239,68,68,0.12); border: 1px solid rgba(239,68,68,0.3); border-radius: 4px; padding: 2px 8px; align-self: flex-start; }
.better-tag { font-size: 10px; font-weight: 700; color: #10b981; text-transform: uppercase; letter-spacing: 0.1em; background: rgba(16,185,129,0.12); border: 1px solid rgba(16,185,129,0.3); border-radius: 4px; padding: 2px 8px; align-self: flex-start; }
.compile-box { background: rgba(239,68,68,0.05); border: 1px solid rgba(239,68,68,0.2); border-radius: 8px; overflow: hidden; }
.compile-box pre { padding: 12px 16px; font-size: 12px; line-height: 1.7; }
.kw { color: #c084fc; }
.type { color: #67e8f9; }
.fn { color: #a3e635; }
.id { color: #e2e8f0; }
.comment { color: #475569; }
.error-line { color: #ef4444; text-decoration: underline wavy #ef4444; }
</style>
