<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />
    <div class="slide-inner">
      <div class="eyebrow">FISCAL OUDEDAGSRESERVE</div>
      <h2 class="title">The law changed — <span class="accent-red">and so did the types</span></h2>
      <div class="main-code">
        <div class="code-header">
          <span class="file">FOR.kt</span>
          <span class="tag">Only compiles for FY2022</span>
        </div>
        <pre><code><span class="comment">// FOR abolished from 2023. This function ONLY exists for FY2022.</span>
<span class="kw">context</span>(<span class="id">year</span>: <span class="type">FY2022</span>)
<span class="kw">fun</span> <span class="type">Raise</span>&lt;<span class="type">TaxError</span>&gt;.<span class="fn">fiscaleOudedagsreserve</span>(winst: <span class="type">Long</span>): <span class="type">Long</span> {
  ensure(winst &gt; 0) { <span class="type">TaxError</span>.InvalidIncome(winst) }
  <span class="kw">return</span> minOf((winst * 0.0944).toLong(), 9632L)
}</code></pre>
      </div>
      <div class="comparison">
        <div class="case good">
          <div class="case-label ok">✓ Compiles</div>
          <pre><code><span class="kw">val</span> year = <span class="type">FY2022</span>
<span class="kw">with</span>(year) {
  fiscaleOudedagsreserve(<span class="num">50_000L</span>)
  <span class="comment">// returns 4720L</span>
}</code></pre>
        </div>
        <div class="case bad">
          <div class="case-label err">✗ Compile error!</div>
          <pre><code><span class="kw">val</span> year = <span class="type">FY2023</span>
<span class="kw">with</span>(year) {
  fiscaleOudedagsreserve(<span class="num">50_000L</span>)
  <span class="comment">// ERROR: no context(FY2023)</span>
}</code></pre>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.orb { position: absolute; width: 500px; height: 500px; border-radius: 50%; filter: blur(120px); background: radial-gradient(circle, #ef4444 0%, #991b1b 60%, transparent 100%); top: -200px; right: -150px; opacity: 0.15; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 860px; padding: 32px 64px; display: flex; flex-direction: column; gap: 18px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 38px; font-weight: 700; color: #e2e8f0; margin: 0; letter-spacing: -0.02em; }
.accent-red { color: #ef4444; }
.main-code { background: rgba(124,58,237,0.07); border: 1px solid rgba(124,58,237,0.3); border-radius: 8px; overflow: hidden; }
.code-header { display: flex; justify-content: space-between; align-items: center; padding: 7px 16px; background: rgba(124,58,237,0.1); border-bottom: 1px solid rgba(124,58,237,0.25); }
.file { font-size: 12px; color: #a855f7; }
.tag { font-size: 11px; color: #67e8f9; letter-spacing: 0.05em; }
pre { margin: 0; padding: 14px 16px; font-size: 13px; line-height: 1.65; }
.comparison { display: grid; grid-template-columns: 1fr 1fr; gap: 16px; }
.case { border-radius: 8px; overflow: hidden; }
.case-label { font-size: 11px; font-weight: 700; padding: 7px 16px; letter-spacing: 0.08em; }
.case-label.ok { background: rgba(16,185,129,0.15); color: #10b981; }
.case-label.err { background: rgba(239,68,68,0.15); color: #ef4444; }
.good pre { background: rgba(16,185,129,0.05); border: 1px solid rgba(16,185,129,0.2); }
.bad pre { background: rgba(239,68,68,0.05); border: 1px solid rgba(239,68,68,0.2); }
.kw { color: #c084fc; }
.type { color: #67e8f9; }
.fn { color: #a3e635; }
.num { color: #fb923c; }
.comment { color: #475569; }
.id { color: #e2e8f0; }
</style>
