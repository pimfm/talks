<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb orb-l" />
    <div class="orb orb-r" />
    <div class="slide-inner">
      <div class="eyebrow">PUTTING IT TOGETHER</div>
      <h2 class="title">Raise + Context = <span class="accent">type-safe domain logic</span></h2>

      <div class="code-block">
        <div class="code-header">
          <span class="file">NLTaxCalculator.kt</span>
          <span class="annotations">
            <span class="ann ann-ctx">context params</span>
            <span class="ann ann-raise">raise dsl</span>
          </span>
        </div>
        <pre><code><span class="kw">context</span>(<span class="id">year</span>: <span class="type">FiscalYear</span>)
<span class="kw">fun</span> <span class="type">Raise</span>&lt;<span class="type">TaxError</span>&gt;.<span class="fn">entrepreneurDeductions</span>(input: <span class="type">AangifteInput</span>): <span class="type">EntrepreneurDeductions</span> {
  checkUrencriterium(input.hoursInBusiness)     <span class="comment">// raises UrencriteriumNotMet</span>
  <span class="kw">val</span> za  = zelfstandigenaftrek()               <span class="comment">// year-aware, no param needed</span>
  <span class="kw">val</span> kia = kia(input.investment)               <span class="comment">// year-specific brackets</span>
  <span class="kw">val</span> mkb = mkbWinstvrijstelling(input.grossIncome - za)
  <span class="kw">return</span> EntrepreneurDeductions(za, kia, mkb)
}</code></pre>
      </div>

      <div class="benefits">
        <div class="benefit">
          <div class="benefit-icon ctx">ctx</div>
          <div class="benefit-body">
            <div class="benefit-title">Year flows through automatically</div>
            <div class="benefit-desc">No parameter threading — every function gets the right context</div>
          </div>
        </div>
        <div class="benefit">
          <div class="benefit-icon raise">⚡</div>
          <div class="benefit-body">
            <div class="benefit-title">Errors are in the signature</div>
            <div class="benefit-desc"><code>UrencriteriumNotMet</code> surfaces at compile time, not runtime</div>
          </div>
        </div>
        <div class="benefit">
          <div class="benefit-icon compiler">✓</div>
          <div class="benefit-body">
            <div class="benefit-title">The compiler is your tax lawyer</div>
            <div class="benefit-desc">Wrong year, missing error handling, abolished deductions — all caught before prod</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); pointer-events: none; }
.orb { position: absolute; border-radius: 50%; filter: blur(100px); pointer-events: none; }
.orb-l { width: 400px; height: 400px; background: radial-gradient(circle, #7c3aed 0%, #4c1d95 60%, transparent 100%); bottom: -150px; left: -100px; opacity: 0.25; }
.orb-r { width: 350px; height: 350px; background: radial-gradient(circle, #06b6d4 0%, #0e7490 60%, transparent 100%); top: -120px; right: -80px; opacity: 0.2; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 860px; padding: 32px 64px; display: flex; flex-direction: column; gap: 22px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 38px; font-weight: 700; color: #e2e8f0; margin: 0; letter-spacing: -0.02em; }
.accent { background: linear-gradient(135deg, #a855f7, #06b6d4); -webkit-background-clip: text; -webkit-text-fill-color: transparent; background-clip: text; }
.code-block { background: rgba(124,58,237,0.07); border: 1px solid rgba(124,58,237,0.3); border-radius: 8px; overflow: hidden; }
.code-header { display: flex; justify-content: space-between; align-items: center; padding: 7px 16px; background: rgba(124,58,237,0.1); border-bottom: 1px solid rgba(124,58,237,0.2); }
.file { font-size: 12px; color: #a855f7; }
.annotations { display: flex; gap: 8px; }
.ann { font-size: 10px; font-weight: 700; letter-spacing: 0.1em; padding: 2px 8px; border-radius: 3px; text-transform: uppercase; }
.ann-ctx { background: rgba(124,58,237,0.2); color: #a855f7; border: 1px solid rgba(124,58,237,0.3); }
.ann-raise { background: rgba(6,182,212,0.15); color: #06b6d4; border: 1px solid rgba(6,182,212,0.3); }
pre { margin: 0; padding: 14px 16px; font-size: 13px; line-height: 1.7; }
.kw { color: #c084fc; }
.type { color: #67e8f9; }
.fn { color: #a3e635; }
.id { color: #e2e8f0; }
.comment { color: #475569; }
.benefits { display: flex; flex-direction: column; gap: 10px; }
.benefit { display: flex; gap: 14px; align-items: flex-start; padding: 12px 16px; background: rgba(255,255,255,0.02); border: 1px solid rgba(30,58,95,0.4); border-radius: 8px; }
.benefit-icon { width: 32px; height: 32px; border-radius: 6px; display: flex; align-items: center; justify-content: center; font-size: 11px; font-weight: 700; flex-shrink: 0; }
.benefit-icon.ctx { background: rgba(124,58,237,0.2); color: #a855f7; border: 1px solid rgba(124,58,237,0.3); }
.benefit-icon.raise { background: rgba(6,182,212,0.15); color: #06b6d4; border: 1px solid rgba(6,182,212,0.3); font-size: 16px; }
.benefit-icon.compiler { background: rgba(16,185,129,0.15); color: #10b981; border: 1px solid rgba(16,185,129,0.3); font-size: 16px; }
.benefit-body { display: flex; flex-direction: column; gap: 2px; }
.benefit-title { font-size: 14px; font-weight: 600; color: #e2e8f0; }
.benefit-desc { font-size: 12px; color: #8896a8; line-height: 1.5; }
.benefit-desc code { color: #67e8f9; font-size: 11px; }
</style>
