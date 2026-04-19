<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />
    <div class="slide-inner">

      <div class="eyebrow">BONUS</div>
      <h2 class="title">Because Kotlin lets you <span class="accent">do this</span></h2>

      <div class="code-window">
        <div class="code-dots"><span /><span /><span /></div>
        <pre class="code-pre"><code><span class="kw">object</span> <span class="tp">€</span> {
    <span class="kw">operator fun</span> <span class="fn">invoke</span>(amount: <span class="tp">Int</span>) = <span class="tp">Money</span>(amount)
    <span class="kw">operator fun</span> <span class="fn">invoke</span>(amount: <span class="tp">BigDecimal</span>) = <span class="tp">Money</span>(amount)
}</code></pre>
      </div>

      <div class="arrow-row">
        <div class="arrow-line" />
        <span class="arrow-label">which means</span>
        <div class="arrow-line" />
      </div>

      <div class="result-row">
        <div class="result-before"><span class="kw">val</span> income = <span class="tp">Money</span>(<span class="nm">800</span>)</div>
        <div class="equiv">≡</div>
        <div class="result-after"><span class="kw">val</span> income = <span class="tp">€</span>(<span class="nm">800</span>)</div>
      </div>

    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); pointer-events: none; }
.orb { position: absolute; width: 500px; height: 500px; border-radius: 50%; filter: blur(130px); background: radial-gradient(circle, #06b6d4 0%, #0e4f60 60%, transparent 100%); bottom: -220px; left: -160px; opacity: 0.2; pointer-events: none; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 780px; padding: 36px 64px; display: flex; flex-direction: column; gap: 28px; }

.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(6,182,212,0.8); text-transform: uppercase; }
.title { font-size: 44px; font-weight: 700; color: #e2e8f0; margin: 0; letter-spacing: -0.02em; }
.accent { background: linear-gradient(135deg, #06b6d4, #a855f7); -webkit-background-clip: text; -webkit-text-fill-color: transparent; background-clip: text; }

.code-window { border-radius: 12px; border: 1px solid rgba(30,58,95,0.6); background: rgba(6,14,28,0.9); overflow: hidden; }
.code-dots { display: flex; gap: 6px; padding: 10px 14px 8px; border-bottom: 1px solid rgba(30,58,95,0.4); }
.code-dots span { width: 10px; height: 10px; border-radius: 50%; }
.code-dots span:nth-child(1) { background: rgba(239,68,68,0.4); }
.code-dots span:nth-child(2) { background: rgba(234,179,8,0.4); }
.code-dots span:nth-child(3) { background: rgba(34,197,94,0.4); }
.code-pre { margin: 0; padding: 22px 24px; font-size: 15px; line-height: 1.8; font-family: 'IBM Plex Mono', monospace; color: #e2e8f0; }

.kw  { color: #c084fc; }
.tp  { color: #67e8f9; }
.fn  { color: #a3e635; }
.nm  { color: #fb923c; }

.arrow-row { display: flex; align-items: center; gap: 14px; }
.arrow-line { flex: 1; height: 1px; background: rgba(30,58,95,0.6); }
.arrow-label { font-size: 11px; font-weight: 600; letter-spacing: 0.12em; text-transform: uppercase; color: #475569; white-space: nowrap; }

.result-row {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 28px;
  padding: 20px 28px;
  border-radius: 12px;
  border: 1px solid rgba(6,182,212,0.25);
  background: rgba(6,182,212,0.04);
}
.result-before, .result-after {
  font-size: 17px;
  font-family: 'IBM Plex Mono', monospace;
  color: #e2e8f0;
  line-height: 1.5;
}
.equiv {
  font-size: 26px;
  color: rgba(6,182,212,0.5);
  font-weight: 300;
  line-height: 1;
}
</style>
