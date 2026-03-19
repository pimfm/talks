<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />
    <div class="slide-inner">
      <div class="eyebrow">REAL WORLD</div>
      <h2 class="title">This scales to <span class="accent">production</span></h2>
      <div class="examples">
        <div class="example-card">
          <div class="example-icon">🏦</div>
          <div class="example-title">Financial rules change yearly</div>
          <div class="example-desc">Tax brackets, deduction limits, rates — add a new FY object and the compiler tells you everywhere it matters.</div>
        </div>
        <div class="example-card">
          <div class="example-icon">⚖️</div>
          <div class="example-title">Regulatory compliance</div>
          <div class="example-desc">Rules that only apply in certain jurisdictions, time windows, or entity types — model them as types, not conditions.</div>
        </div>
        <div class="example-card">
          <div class="example-icon">🔍</div>
          <div class="example-title">Full audit trail</div>
          <div class="example-desc">Traceable reports mean every calculation can be explained and verified. No black boxes.</div>
        </div>
      </div>
      <div class="pattern-box">
        <div class="pattern-label">The pattern</div>
        <div class="pattern-text">
          <span class="p-item">Domain rules as types</span>
          <span class="p-sep">+</span>
          <span class="p-item">Errors as values</span>
          <span class="p-sep">+</span>
          <span class="p-item">Context for year/jurisdiction</span>
          <span class="p-sep">=</span>
          <span class="p-result">Correct-by-construction</span>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.orb { position: absolute; width: 450px; height: 450px; border-radius: 50%; filter: blur(110px); background: radial-gradient(circle, #06b6d4 0%, #0e7490 60%, transparent 100%); bottom: -150px; right: -100px; opacity: 0.15; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 860px; padding: 36px 64px; display: flex; flex-direction: column; gap: 24px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 44px; font-weight: 700; color: #e2e8f0; margin: 0; }
.accent { background: linear-gradient(135deg, #06b6d4, #a855f7); -webkit-background-clip: text; -webkit-text-fill-color: transparent; background-clip: text; }
.examples { display: grid; grid-template-columns: repeat(3, 1fr); gap: 16px; }
.example-card { background: rgba(255,255,255,0.02); border: 1px solid rgba(30,58,95,0.5); border-radius: 8px; padding: 20px; display: flex; flex-direction: column; gap: 10px; }
.example-icon { font-size: 28px; }
.example-title { font-size: 14px; font-weight: 600; color: #e2e8f0; }
.example-desc { font-size: 13px; color: #8896a8; line-height: 1.5; }
.pattern-box { background: rgba(124,58,237,0.08); border: 1px solid rgba(124,58,237,0.3); border-radius: 8px; padding: 16px 24px; }
.pattern-label { font-size: 11px; color: #8896a8; text-transform: uppercase; letter-spacing: 0.1em; margin-bottom: 10px; }
.pattern-text { display: flex; align-items: center; gap: 10px; flex-wrap: wrap; }
.p-item { font-size: 14px; color: #e2e8f0; background: rgba(255,255,255,0.05); padding: 4px 12px; border-radius: 4px; }
.p-sep { color: rgba(168,85,247,0.5); font-size: 18px; }
.p-result { font-size: 15px; font-weight: 700; color: #a855f7; }
</style>
