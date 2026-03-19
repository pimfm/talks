<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="slide-inner">
      <div class="eyebrow">SIDE BY SIDE</div>
      <h2 class="title">Raise vs Exceptions</h2>
      <div class="comparison">
        <div class="col">
          <div class="col-header bad">Exceptions</div>
          <pre><code><span class="comment">// Invisible — not in signature</span>
<span class="kw">fun</span> <span class="fn">checkHours</span>(hours: <span class="type">Int</span>) {
  <span class="kw">if</span> (hours &lt; 1225)
    <span class="kw">throw</span> RuntimeException(<span class="str">"..."</span>)
}</code></pre>
          <div class="issue-list">
            <div class="issue">Caller has no idea what to catch</div>
            <div class="issue">Escapes type system</div>
            <div class="issue">Can't compose safely</div>
          </div>
        </div>
        <div class="col">
          <div class="col-header good">Raise DSL</div>
          <pre><code><span class="comment">// Visible — error type in signature</span>
<span class="kw">fun</span> <span class="type">Raise</span>&lt;<span class="type">TaxError</span>&gt;.<span class="fn">checkHours</span>(hours: <span class="type">Int</span>) {
  ensure(hours &gt;= 1225) {
    UrencriteriumNotMet(hours)
  }
}</code></pre>
          <div class="good-list">
            <div class="benefit">Error type is part of the API</div>
            <div class="benefit">Compiler tracks it everywhere</div>
            <div class="benefit">Composable with <code>either { }</code></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 860px; padding: 36px 64px; display: flex; flex-direction: column; gap: 20px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 44px; font-weight: 700; color: #e2e8f0; margin: 0; }
.comparison { display: grid; grid-template-columns: 1fr 1fr; gap: 24px; }
.col { display: flex; flex-direction: column; gap: 12px; }
.col-header { font-size: 13px; font-weight: 700; padding: 8px 16px; border-radius: 4px; }
.col-header.bad { background: rgba(239,68,68,0.15); color: #ef4444; }
.col-header.good { background: rgba(16,185,129,0.15); color: #10b981; }
pre { margin: 0; padding: 14px 16px; font-size: 13px; line-height: 1.65; background: rgba(255,255,255,0.03); border: 1px solid rgba(30,58,95,0.5); border-radius: 6px; }
.issue-list, .good-list { display: flex; flex-direction: column; gap: 6px; }
.issue { font-size: 13px; color: #ef4444; padding-left: 16px; position: relative; }
.issue::before { content: '✗'; position: absolute; left: 0; }
.benefit { font-size: 13px; color: #10b981; padding-left: 16px; position: relative; }
.benefit::before { content: '✓'; position: absolute; left: 0; }
.benefit code { color: #67e8f9; font-size: 12px; }
.kw { color: #c084fc; }
.type { color: #67e8f9; }
.fn { color: #a3e635; }
.str { color: #fbbf24; }
.comment { color: #475569; }
</style>
