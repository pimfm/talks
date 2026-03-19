<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="slide-inner">
      <div class="eyebrow">ARROW RAISE — USING IT</div>
      <h2 class="title">Collecting errors with <span class="accent">either { }</span></h2>
      <div class="code-grid">
        <div class="code-card">
          <div class="code-label">Define domain errors</div>
          <pre><code><span class="kw">sealed interface</span> <span class="type">TaxError</span> {
  <span class="kw">data class</span> <span class="type">UrencriteriumNotMet</span>(
    <span class="kw">val</span> hours: <span class="type">Int</span>, <span class="kw">val</span> required: <span class="type">Int</span> = 1225
  ) : <span class="type">TaxError</span>
  <span class="kw">data class</span> <span class="type">InvalidIncome</span>(<span class="kw">val</span> amount: <span class="type">Long</span>) : <span class="type">TaxError</span>
}</code></pre>
        </div>
        <div class="code-card">
          <div class="code-label">Raise errors in domain functions</div>
          <pre><code><span class="kw">fun</span> <span class="type">Raise</span>&lt;<span class="type">TaxError</span>&gt;.<span class="fn">checkUrencriterium</span>(hours: <span class="type">Int</span>) =
  ensure(hours &gt;= 1225) {
    <span class="type">TaxError</span>.UrencriteriumNotMet(hours)
  }</code></pre>
        </div>
        <div class="code-card">
          <div class="code-label">Recover at the boundary</div>
          <pre><code><span class="kw">val</span> result: <span class="type">Either</span>&lt;<span class="type">TaxError</span>, <span class="type">Long</span>&gt; = either {
  checkUrencriterium(hours)
  berekenBox1(income)
}
result.fold({ err -&gt; ... }, { tax -&gt; ... })</code></pre>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 860px; padding: 36px 64px; display: flex; flex-direction: column; gap: 24px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 38px; font-weight: 700; color: #e2e8f0; margin: 0; letter-spacing: -0.02em; }
.accent { color: #67e8f9; }
.code-grid { display: flex; flex-direction: column; gap: 14px; }
.code-card { background: rgba(124,58,237,0.07); border: 1px solid rgba(124,58,237,0.25); border-radius: 8px; overflow: hidden; }
.code-label { font-size: 11px; color: #a855f7; text-transform: uppercase; letter-spacing: 0.1em; padding: 7px 16px; background: rgba(124,58,237,0.1); border-bottom: 1px solid rgba(124,58,237,0.2); }
pre { margin: 0; padding: 12px 16px; font-size: 13px; line-height: 1.65; }
.kw { color: #c084fc; }
.type { color: #67e8f9; }
.fn { color: #a3e635; }
</style>
