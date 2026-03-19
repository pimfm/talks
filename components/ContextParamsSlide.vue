<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />
    <div class="slide-inner">
      <div class="eyebrow">KOTLIN 2.2+</div>
      <h2 class="title"><span class="accent">Context Parameters</span></h2>
      <div class="content">
        <p class="intro">Implicit dependencies — injected by the compiler from the call site.</p>
        <div class="code-row">
          <div class="code-card">
            <div class="code-label">Declare</div>
            <pre><code><span class="comment">// "I need a FiscalYear in scope"</span>
<span class="kw">context</span>(<span class="id">year</span>: <span class="type">FiscalYear</span>)
<span class="kw">fun</span> <span class="fn">zelfstandigenaftrek</span>(): <span class="type">Long</span> =
  <span class="kw">when</span> (year) {
    <span class="kw">is</span> <span class="type">FY2025</span> -&gt; <span class="num">2470L</span>
    <span class="kw">is</span> <span class="type">FY2026</span> -&gt; <span class="num">1200L</span>
  }</code></pre>
          </div>
          <div class="code-card">
            <div class="code-label">Call</div>
            <pre><code><span class="comment">// Provide the context with "with()"</span>
<span class="kw">val</span> fy = <span class="type">FY2026</span>
<span class="kw">with</span>(fy) {
  <span class="kw">val</span> aftrek = zelfstandigenaftrek()
  <span class="comment">// aftrek == 1200L</span>
}</code></pre>
          </div>
        </div>
        <div class="benefit-row">
          <div class="benefit">No parameter threading through every function</div>
          <div class="benefit">Available everywhere in scope automatically</div>
          <div class="benefit">Wrong context = compile error, not runtime bug</div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.orb { position: absolute; width: 450px; height: 450px; border-radius: 50%; filter: blur(110px); background: radial-gradient(circle, #7c3aed 0%, #4c1d95 60%, transparent 100%); top: -160px; right: -100px; opacity: 0.25; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 860px; padding: 36px 64px; display: flex; flex-direction: column; gap: 20px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 48px; font-weight: 700; color: #e2e8f0; margin: 0; letter-spacing: -0.02em; }
.accent { background: linear-gradient(135deg, #a855f7, #ec4899); -webkit-background-clip: text; -webkit-text-fill-color: transparent; background-clip: text; }
.intro { font-size: 18px; color: #94a3b8; margin: 0; }
.code-row { display: grid; grid-template-columns: 1fr 1fr; gap: 16px; }
.code-card { background: rgba(124,58,237,0.07); border: 1px solid rgba(124,58,237,0.3); border-radius: 8px; overflow: hidden; }
.code-label { font-size: 11px; color: #a855f7; text-transform: uppercase; letter-spacing: 0.1em; padding: 7px 16px; background: rgba(124,58,237,0.1); border-bottom: 1px solid rgba(124,58,237,0.2); }
pre { margin: 0; padding: 14px 16px; font-size: 13px; line-height: 1.65; }
.kw { color: #c084fc; }
.type { color: #67e8f9; }
.fn { color: #a3e635; }
.id { color: #e2e8f0; }
.num { color: #fb923c; }
.comment { color: #475569; }
.benefit-row { display: grid; grid-template-columns: repeat(3, 1fr); gap: 12px; }
.benefit { background: rgba(6,182,212,0.06); border: 1px solid rgba(6,182,212,0.2); border-radius: 6px; padding: 12px 16px; font-size: 13px; color: #94a3b8; line-height: 1.4; }
</style>
