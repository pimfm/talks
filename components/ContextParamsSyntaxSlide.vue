<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />
    <div class="slide-inner">
      <div class="eyebrow">KOTLIN 2.2+ FEATURE</div>
      <h2 class="title">Context parameter <span class="accent">syntax</span></h2>
      <div class="code-steps">
        <div class="step">
          <div class="step-num">1</div>
          <div class="step-body">
            <div class="step-label">Enable in build.gradle.kts</div>
            <pre><code>freeCompilerArgs.add(<span class="str">"-Xcontext-parameters"</span>)</code></pre>
          </div>
        </div>
        <div class="step">
          <div class="step-num">2</div>
          <div class="step-body">
            <div class="step-label">Declare on a function</div>
            <pre><code><span class="kw">context</span>(<span class="id">year</span>: <span class="type">FiscalYear</span>)
<span class="kw">fun</span> <span class="fn">berekenBox1</span>(inkomen: <span class="type">Long</span>): <span class="type">Long</span> {
    <span class="comment">// year is available here implicitly</span>
    <span class="kw">val</span> schijven = box1Schijven() <span class="comment">// also context(year)</span>
}</code></pre>
          </div>
        </div>
        <div class="step">
          <div class="step-num">3</div>
          <div class="step-body">
            <div class="step-label">Provide context at call site</div>
            <pre><code><span class="kw">val</span> fy: <span class="type">FiscalYear</span> = <span class="type">FY2026</span>
<span class="kw">with</span>(fy) {
    berekenBox1(<span class="num">88_000L</span>)
}</code></pre>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.orb { position: absolute; width: 400px; height: 400px; border-radius: 50%; filter: blur(100px); background: radial-gradient(circle, #a855f7 0%, #7c3aed 60%, transparent 100%); top: -150px; right: -100px; opacity: 0.2; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 820px; padding: 36px 64px; display: flex; flex-direction: column; gap: 24px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 44px; font-weight: 700; color: #e2e8f0; margin: 0; }
.accent { color: #67e8f9; }
.code-steps { display: flex; flex-direction: column; gap: 16px; }
.step { display: flex; gap: 16px; align-items: flex-start; }
.step-num { width: 28px; height: 28px; border-radius: 50%; background: rgba(124,58,237,0.3); border: 1px solid rgba(124,58,237,0.5); display: flex; align-items: center; justify-content: center; font-size: 12px; font-weight: 700; color: #a855f7; flex-shrink: 0; margin-top: 2px; }
.step-body { flex: 1; display: flex; flex-direction: column; gap: 8px; }
.step-label { font-size: 12px; color: #8896a8; text-transform: uppercase; letter-spacing: 0.08em; }
pre { margin: 0; padding: 12px 16px; font-size: 13px; line-height: 1.65; background: rgba(124,58,237,0.06); border: 1px solid rgba(124,58,237,0.25); border-radius: 6px; }
.kw { color: #c084fc; }
.type { color: #67e8f9; }
.fn { color: #a3e635; }
.id { color: #e2e8f0; }
.str { color: #fbbf24; }
.num { color: #fb923c; }
.comment { color: #475569; }
</style>
