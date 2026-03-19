<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />
    <div class="slide-inner">
      <div class="eyebrow">THE FOUNDATION</div>
      <h2 class="title">What is <span class="accent">Type Safety</span>?</h2>
      <div class="content-row">
        <div class="definition">
          <p class="def-text">Type safety means the compiler prevents you from doing things that don't make sense for your data.</p>
          <div class="examples">
            <div class="example bad">
              <div class="example-label bad-label">✗ wrong fiscal year — runtime surprise</div>
              <pre><code><span class="comment">// What year is this FOR for? Nobody knows.</span>
<span class="kw">fun</span> <span class="fn">berekenFOR</span>(<span class="id">winst</span>: <span class="type">Long</span>, <span class="id">year</span>: <span class="type">Int</span>): <span class="type">Long</span> = ...
<span class="fn">berekenFOR</span>(<span class="num">50_000L</span>, <span class="num">2024</span>)  <span class="comment">// abolished in 2023!</span></code></pre>
            </div>
            <div class="example good">
              <div class="example-label good-label">✓ wrong fiscal year — compile error</div>
              <pre><code><span class="kw">context</span>(<span class="id">year</span>: <span class="type">FY2022</span>)
<span class="kw">fun</span> <span class="fn">fiscaleOudedagsreserve</span>(<span class="id">winst</span>: <span class="type">Long</span>): <span class="type">Long</span>
<span class="comment">// with(FY2024) { fiscaleOudedagsreserve(50_000L) }</span>
<span class="comment">// ^^^^^ COMPILE ERROR — no context(FY2024) exists</span></code></pre>
            </div>
          </div>
        </div>
        <div class="mantra">
          <div class="mantra-text">Make illegal states <span class="accent">unrepresentable</span></div>
          <div class="mantra-sub">— Yaron Minsky</div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.orb { position: absolute; width: 500px; height: 500px; border-radius: 50%; filter: blur(120px); background: radial-gradient(circle, #7c3aed 0%, #4c1d95 60%, transparent 100%); top: -200px; right: -150px; opacity: 0.3; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 860px; padding: 40px 64px; display: flex; flex-direction: column; gap: 24px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 48px; font-weight: 700; color: #e2e8f0; margin: 0; letter-spacing: -0.02em; }
.accent { background: linear-gradient(135deg, #a855f7, #06b6d4); -webkit-background-clip: text; -webkit-text-fill-color: transparent; background-clip: text; }
.content-row { display: flex; flex-direction: column; gap: 24px; }
.def-text { font-size: 18px; color: #94a3b8; line-height: 1.6; margin: 0 0 16px; }
.examples { display: grid; grid-template-columns: 1fr 1fr; gap: 16px; }
.example { border-radius: 8px; overflow: hidden; }
.example-label { font-size: 11px; font-weight: 700; letter-spacing: 0.1em; padding: 6px 14px; }
.bad-label { background: rgba(239,68,68,0.2); color: #ef4444; }
.good-label { background: rgba(16,185,129,0.2); color: #10b981; }
.example pre { margin: 0; padding: 14px 16px; font-size: 13px; line-height: 1.6; }
.example.bad pre { background: rgba(239,68,68,0.06); border: 1px solid rgba(239,68,68,0.2); }
.example.good pre { background: rgba(16,185,129,0.06); border: 1px solid rgba(16,185,129,0.2); }
.kw { color: #c084fc; }
.type { color: #67e8f9; }
.fn { color: #a3e635; }
.id { color: #e2e8f0; }
.str { color: #fbbf24; }
.num { color: #fb923c; }
.comment { color: #475569; }
.mantra { text-align: center; padding: 24px; background: rgba(124,58,237,0.08); border: 1px solid rgba(124,58,237,0.25); border-radius: 10px; }
.mantra-text { font-size: 22px; font-weight: 600; color: #e2e8f0; }
.mantra-sub { font-size: 14px; color: #8896a8; margin-top: 6px; }
</style>
