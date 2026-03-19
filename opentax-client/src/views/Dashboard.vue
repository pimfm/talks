<script setup lang="ts">
const emit = defineEmits<{
  navigate: [view: 'calculator' | 'tarieven']
}>()
</script>

<template>
  <div class="dashboard">
    <div class="hero">
      <div class="hero-badge">type-safe · compile-time errors · arrow raise DSL</div>
      <h1 class="hero-title">
        <span class="line1">OpenTax</span>
        <span class="line2">Type-Safe Tax Calculations</span>
      </h1>
      <p class="hero-desc">
        Dutch income tax calculator using Kotlin context parameters and Arrow's Raise DSL.
        Every fiscal year rule is enforced at compile time.
      </p>
      <div class="hero-actions">
        <button class="btn-primary" @click="emit('navigate', 'calculator')">
          Bereken aangifte →
        </button>
        <button class="btn-secondary" @click="emit('navigate', 'tarieven')">
          Bekijk tarieven
        </button>
      </div>
    </div>

    <div class="features">
      <div class="feature-card">
        <div class="feature-icon purple">context(FY2022)</div>
        <h3>Context Parameters</h3>
        <p>
          Fiscal year rules are injected via context parameters.
          Using a 2022-only rule in 2023 is a <em>compile error</em>.
        </p>
      </div>
      <div class="feature-card">
        <div class="feature-icon cyan">Raise&lt;TaxError&gt;</div>
        <h3>Arrow Raise DSL</h3>
        <p>
          All domain errors surface as typed values — no exceptions,
          no null, no surprises. Every error is traceable.
        </p>
      </div>
      <div class="feature-card">
        <div class="feature-icon pink">@Serializable</div>
        <h3>Traceable Reports</h3>
        <p>
          Every calculation step is recorded in the report.
          See exactly how the tax was computed, schijf by schijf.
        </p>
      </div>
    </div>

    <div class="demo-code">
      <div class="code-header">
        <span class="code-file">FOR.kt</span>
        <span class="code-tag">compile-time enforcement</span>
      </div>
      <pre class="code-block"><code><span class="kw">context</span>(<span class="type">year</span>: <span class="type">FY2022</span>)
<span class="kw">fun</span> <span class="type">Raise</span>&lt;<span class="type">TaxError</span>&gt;.<span class="fn">fiscaleOudedagsreserve</span>(winst: <span class="type">Long</span>): <span class="type">Long</span> {
    ensure(winst &gt; 0) { <span class="type">TaxError</span>.InvalidIncome(winst) }
    <span class="kw">return</span> minOf((winst * 0.0944).toLong(), 9632L)
}
<span class="comment">// No FY2023 version — using FOR in 2023 = compile error</span></code></pre>
    </div>

    <div class="characters">
      <h2 class="section-title">Test cases</h2>
      <div class="char-grid">
        <div class="char-card richard">
          <div class="char-name">Richard</div>
          <div class="char-details">
            <div>Part-time DJ · 50h/year</div>
            <div>€800 gross income</div>
            <div>€300 investment</div>
            <div class="char-result error">Urencriterium niet gehaald</div>
          </div>
        </div>
        <div class="char-card laura">
          <div class="char-name">Laura</div>
          <div class="char-details">
            <div>Full-time freelancer · 1400h/year</div>
            <div>€88,000 gross income</div>
            <div>€6,500 investment</div>
            <div class="char-result success">Alle aftrekposten van toepassing</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.dashboard { display: flex; flex-direction: column; gap: 48px; }

.hero {
  text-align: center;
  padding: 48px 0 32px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
}

.hero-badge {
  font-size: 11px;
  color: var(--cyan);
  border: 1px solid rgba(6, 182, 212, 0.3);
  padding: 4px 14px;
  border-radius: 20px;
  letter-spacing: 0.08em;
}

.hero-title {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 4px;
}
.line1 { font-size: 18px; font-weight: 300; color: var(--text-muted); }
.line2 {
  font-size: 42px;
  font-weight: 700;
  background: linear-gradient(135deg, #a855f7 0%, #ec4899 50%, #06b6d4 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.hero-desc { font-size: 15px; color: var(--text-muted); max-width: 560px; line-height: 1.7; }

.hero-actions { display: flex; gap: 12px; }
.btn-primary {
  background: var(--purple);
  color: white;
  border: none;
  padding: 10px 24px;
  border-radius: 6px;
  font-size: 14px;
  cursor: pointer;
  transition: all 0.15s;
}
.btn-primary:hover { background: var(--purple-light); }
.btn-secondary {
  background: none;
  color: var(--text);
  border: 1px solid var(--border);
  padding: 10px 24px;
  border-radius: 6px;
  font-size: 14px;
  cursor: pointer;
  transition: all 0.15s;
}
.btn-secondary:hover { border-color: var(--purple-light); color: var(--purple-light); }

.features {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
}
.feature-card {
  background: var(--bg2);
  border: 1px solid var(--border);
  border-radius: 8px;
  padding: 24px;
  display: flex;
  flex-direction: column;
  gap: 12px;
}
.feature-icon {
  font-size: 12px;
  padding: 6px 12px;
  border-radius: 4px;
  display: inline-block;
  font-weight: 600;
}
.feature-icon.purple { background: rgba(124,58,237,0.15); color: var(--purple-light); }
.feature-icon.cyan { background: rgba(6,182,212,0.15); color: var(--cyan); }
.feature-icon.pink { background: rgba(236,72,153,0.15); color: var(--pink); }
.feature-card h3 { font-size: 15px; font-weight: 600; }
.feature-card p { font-size: 13px; color: var(--text-muted); line-height: 1.6; }
.feature-card em { color: var(--red); font-style: normal; }

.demo-code {
  background: var(--bg2);
  border: 1px solid var(--border);
  border-radius: 8px;
  overflow: hidden;
}
.code-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  background: rgba(255,255,255,0.03);
  border-bottom: 1px solid var(--border);
  font-size: 12px;
}
.code-file { color: var(--purple-light); }
.code-tag { color: var(--text-muted); font-size: 11px; }
.code-block {
  padding: 20px;
  font-size: 13px;
  line-height: 1.7;
  overflow-x: auto;
}
.kw { color: #c084fc; }
.type { color: #67e8f9; }
.fn { color: #a3e635; }
.comment { color: #475569; }

.section-title { font-size: 16px; font-weight: 600; margin-bottom: 16px; }

.char-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 20px; }
.char-card {
  background: var(--bg2);
  border: 1px solid var(--border);
  border-radius: 8px;
  padding: 24px;
}
.char-card.richard { border-left: 3px solid var(--yellow); }
.char-card.laura { border-left: 3px solid var(--cyan); }
.char-name { font-size: 18px; font-weight: 700; margin-bottom: 12px; }
.char-details { display: flex; flex-direction: column; gap: 4px; font-size: 13px; color: var(--text-muted); }
.char-result { margin-top: 8px; font-weight: 600; font-size: 12px; }
.char-result.error { color: var(--red); }
.char-result.success { color: var(--green); }
</style>
