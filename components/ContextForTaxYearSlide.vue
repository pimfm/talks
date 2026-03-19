<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb orb-purple" />
    <div class="orb orb-cyan" />
    <div class="slide-inner">
      <div class="eyebrow">THE CENTRAL IDEA</div>
      <h2 class="title">Fiscal year as <span class="accent">context</span></h2>
      <div class="main-code">
        <div class="code-header">
          <span class="file">FiscalYear.kt</span>
        </div>
        <pre><code><span class="kw">sealed interface</span> <span class="type">FiscalYear</span>
<span class="kw">data object</span> <span class="type">FY2022</span> : <span class="type">FiscalYear</span>
<span class="kw">data object</span> <span class="type">FY2023</span> : <span class="type">FiscalYear</span>
<span class="kw">data object</span> <span class="type">FY2026</span> : <span class="type">FiscalYear</span></code></pre>
      </div>
      <div class="arrow-row">
        <div class="arrow-text">Each year is its own type. Functions can demand <em>exactly the right year</em>.</div>
      </div>
      <div class="usage-cards">
        <div class="usage-card">
          <div class="usage-label cyan">Generic — works any year</div>
          <pre><code><span class="kw">context</span>(<span class="id">year</span>: <span class="type">FiscalYear</span>)
<span class="kw">fun</span> <span class="fn">zelfstandigenaftrek</span>(): <span class="type">Long</span></code></pre>
        </div>
        <div class="usage-card">
          <div class="usage-label purple">Specific — only 2022!</div>
          <pre><code><span class="kw">context</span>(<span class="id">year</span>: <span class="type">FY2022</span>)
<span class="kw">fun</span> <span class="fn">fiscaleOudedagsreserve</span>(...): <span class="type">Long</span></code></pre>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.orb { position: absolute; border-radius: 50%; filter: blur(100px); }
.orb-purple { width: 400px; height: 400px; background: radial-gradient(circle, #7c3aed 0%, #4c1d95 60%, transparent 100%); top: -150px; right: -100px; opacity: 0.3; }
.orb-cyan { width: 300px; height: 300px; background: radial-gradient(circle, #06b6d4 0%, #0e7490 60%, transparent 100%); bottom: -100px; left: -80px; opacity: 0.2; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 800px; padding: 36px 64px; display: flex; flex-direction: column; gap: 20px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 44px; font-weight: 700; color: #e2e8f0; margin: 0; letter-spacing: -0.02em; }
.accent { color: #a855f7; }
.main-code { background: rgba(30,58,95,0.2); border: 1px solid rgba(30,58,95,0.5); border-radius: 8px; overflow: hidden; }
.code-header { padding: 7px 16px; background: rgba(255,255,255,0.03); border-bottom: 1px solid rgba(30,58,95,0.4); font-size: 12px; color: #a855f7; }
pre { margin: 0; padding: 14px 16px; font-size: 14px; line-height: 1.65; }
.kw { color: #c084fc; }
.type { color: #67e8f9; }
.fn { color: #a3e635; }
.id { color: #e2e8f0; }
.arrow-text { font-size: 16px; color: #94a3b8; }
.arrow-text em { color: #a855f7; font-style: normal; font-weight: 600; }
.usage-cards { display: grid; grid-template-columns: 1fr 1fr; gap: 16px; }
.usage-card { border-radius: 8px; overflow: hidden; border: 1px solid rgba(30,58,95,0.5); }
.usage-label { font-size: 11px; font-weight: 700; letter-spacing: 0.1em; padding: 7px 16px; text-transform: uppercase; }
.usage-label.cyan { background: rgba(6,182,212,0.1); color: #06b6d4; border-bottom: 1px solid rgba(6,182,212,0.2); }
.usage-label.purple { background: rgba(124,58,237,0.1); color: #a855f7; border-bottom: 1px solid rgba(124,58,237,0.3); }
.usage-card pre { background: rgba(255,255,255,0.02); font-size: 13px; }
</style>
