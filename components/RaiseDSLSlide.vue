<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />
    <div class="slide-inner">
      <div class="eyebrow">ARROW KT</div>
      <h2 class="title">The <span class="accent">Raise</span> DSL</h2>
      <div class="columns">
        <div class="col">
          <div class="col-header">Traditional approach</div>
          <div class="code-block bad">
            <pre><code><span class="kw">fun</span> <span class="fn">calculate</span>(income: <span class="type">Long</span>): <span class="type">Long</span> {
  <span class="kw">if</span> (income &lt; 0)
    <span class="kw">throw</span> IllegalArgumentException(<span class="str">"!"</span>)
  <span class="kw">return</span> income * <span class="num">0.36</span>
}</code></pre>
          </div>
          <div class="problem-list">
            <div class="problem-item">Exceptions are invisible in signatures</div>
            <div class="problem-item">Caller doesn't know what can go wrong</div>
            <div class="problem-item">Easy to forget to handle</div>
          </div>
        </div>
        <div class="divider" />
        <div class="col">
          <div class="col-header">Arrow Raise DSL</div>
          <div class="code-block good">
            <pre><code><span class="kw">fun</span> <span class="type">Raise</span>&lt;<span class="type">TaxError</span>&gt;.<span class="fn">calculate</span>(
  income: <span class="type">Long</span>
): <span class="type">Long</span> {
  ensure(income &gt;= 0) { InvalidIncome(income) }
  <span class="kw">return</span> income * <span class="num">0.36</span>
}</code></pre>
          </div>
          <div class="good-list">
            <div class="good-item">Error type is in the function signature</div>
            <div class="good-item">Compiler forces you to handle errors</div>
            <div class="good-item">No exceptions, no null</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.orb { position: absolute; width: 400px; height: 400px; border-radius: 50%; filter: blur(100px); background: radial-gradient(circle, #06b6d4 0%, #0e7490 60%, transparent 100%); bottom: -150px; left: -100px; opacity: 0.2; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 860px; padding: 36px 64px; display: flex; flex-direction: column; gap: 20px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 44px; font-weight: 700; color: #e2e8f0; margin: 0; letter-spacing: -0.02em; }
.accent { color: #67e8f9; }
.columns { display: grid; grid-template-columns: 1fr 1px 1fr; gap: 24px; align-items: start; }
.divider { background: rgba(30,58,95,0.6); align-self: stretch; }
.col { display: flex; flex-direction: column; gap: 12px; }
.col-header { font-size: 12px; font-weight: 600; color: #8896a8; text-transform: uppercase; letter-spacing: 0.1em; }
.code-block { border-radius: 6px; overflow: hidden; }
.code-block pre { margin: 0; padding: 14px 16px; font-size: 13px; line-height: 1.65; }
.bad pre { background: rgba(30,58,95,0.2); border: 1px solid rgba(30,58,95,0.5); }
.good pre { background: rgba(124,58,237,0.08); border: 1px solid rgba(124,58,237,0.3); }
.kw { color: #c084fc; }
.type { color: #67e8f9; }
.fn { color: #a3e635; }
.str { color: #fbbf24; }
.num { color: #fb923c; }
.problem-list, .good-list { display: flex; flex-direction: column; gap: 6px; }
.problem-item { font-size: 13px; color: #ef4444; padding-left: 16px; position: relative; }
.problem-item::before { content: '✗'; position: absolute; left: 0; }
.good-item { font-size: 13px; color: #10b981; padding-left: 16px; position: relative; }
.good-item::before { content: '✓'; position: absolute; left: 0; }
</style>
