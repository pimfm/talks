<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />
    <div class="slide-inner">
      <div class="eyebrow">KLEINSCHALIGHEIDSINVESTERINGSAFTREK</div>
      <h2 class="title">KIA — invest, <span class="accent">deduct</span></h2>

      <div class="code-block">
        <div class="code-header">
          <span class="file">KIA.kt</span>
          <span class="tag">context(year: FiscalYear)</span>
        </div>
        <pre><code><span class="kw">context</span>(<span class="id">year</span>: <span class="type">FiscalYear</span>)
<span class="kw">fun</span> <span class="fn">kia</span>(investment: <span class="type">Long</span>): <span class="type">Long</span> {
  <span class="kw">val</span> b = <span class="fn">kiaBrackets</span>()    <span class="comment">// FY2022..FY2026 — different per year</span>
  <span class="kw">return when</span> {
    investment &lt;= b.threshold    -&gt; <span class="num">0L</span>
    investment &lt;= b.phaseOutStart -&gt; minOf((investment * b.flatRate).toLong(), b.maxFixed)
    <span class="kw">else</span>                          -&gt; <span class="num">0L</span>
  }
}</code></pre>
      </div>

      <div class="comparison">
        <div class="char-case richard">
          <div class="case-header">
            <img src="https://api.dicebear.com/9.x/adventurer/svg?seed=Daan&backgroundColor=0f0b1e" class="avatar" alt="Richard" />
            <div>
              <div class="char-name">Richard</div>
              <div class="case-sub">€300 investment</div>
            </div>
          </div>
          <div class="case-result no-kia">
            kia(300L) = <strong>€0</strong>
            <div class="case-note">Below €2,900 threshold</div>
          </div>
        </div>

        <div class="arrow">→</div>

        <div class="char-case laura">
          <div class="case-header">
            <img src="https://api.dicebear.com/9.x/adventurer/svg?seed=Laura&backgroundColor=0f0b1e" class="avatar" alt="Laura" />
            <div>
              <div class="char-name">Laura</div>
              <div class="case-sub">€6,500 investment</div>
            </div>
          </div>
          <div class="case-result yes-kia">
            kia(6500L) = <strong>€1,820</strong>
            <div class="case-note">6500 × 28% = deductible</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); pointer-events: none; }
.orb { position: absolute; width: 400px; height: 400px; border-radius: 50%; filter: blur(100px); background: radial-gradient(circle, #06b6d4 0%, #0e7490 60%, transparent 100%); bottom: -150px; right: -100px; opacity: 0.15; pointer-events: none; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 840px; padding: 32px 64px; display: flex; flex-direction: column; gap: 20px; }
.eyebrow { font-size: 10px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 42px; font-weight: 700; color: #e2e8f0; margin: 0; letter-spacing: -0.02em; }
.accent { color: #67e8f9; }
.code-block { background: rgba(124,58,237,0.06); border: 1px solid rgba(124,58,237,0.3); border-radius: 8px; overflow: hidden; }
.code-header { display: flex; justify-content: space-between; align-items: center; padding: 7px 16px; background: rgba(124,58,237,0.1); border-bottom: 1px solid rgba(124,58,237,0.2); }
.file { font-size: 12px; color: #a855f7; }
.tag { font-size: 11px; color: #67e8f9; }
pre { margin: 0; padding: 14px 16px; font-size: 13px; line-height: 1.65; }
.comparison { display: flex; align-items: center; gap: 20px; }
.char-case { flex: 1; border: 1px solid rgba(30,58,95,0.5); border-radius: 10px; padding: 16px; display: flex; flex-direction: column; gap: 12px; }
.richard { border-color: rgba(245,158,11,0.3); background: rgba(245,158,11,0.04); }
.laura { border-color: rgba(6,182,212,0.3); background: rgba(6,182,212,0.04); }
.case-header { display: flex; align-items: center; gap: 10px; }
.avatar { width: 40px; height: 40px; border-radius: 50%; border: 1px solid rgba(30,58,95,0.6); background: #0f0b1e; }
.char-name { font-size: 15px; font-weight: 700; color: #e2e8f0; }
.case-sub { font-size: 12px; color: #8896a8; }
.case-result { font-size: 14px; color: #94a3b8; padding: 10px 14px; border-radius: 6px; }
.case-result strong { font-size: 18px; font-weight: 700; }
.no-kia { background: rgba(239,68,68,0.06); border: 1px solid rgba(239,68,68,0.2); }
.no-kia strong { color: #ef4444; }
.yes-kia { background: rgba(16,185,129,0.06); border: 1px solid rgba(16,185,129,0.2); }
.yes-kia strong { color: #10b981; }
.case-note { font-size: 11px; color: #475569; margin-top: 4px; }
.arrow { font-size: 24px; color: rgba(124,58,237,0.4); flex-shrink: 0; }
.kw { color: #c084fc; }
.type { color: #67e8f9; }
.fn { color: #a3e635; }
.id { color: #e2e8f0; }
.num { color: #fb923c; }
</style>
