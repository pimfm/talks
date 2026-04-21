<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />
    <div class="slide-inner">
      <div class="eyebrow">SECTION 02</div>
      <h2 class="title">2. Gewone Investeringsaftrek</h2>
      <p class="description">
        A Belgian deduction for self-employed on the value of qualifying business investments —
        reducing taxable profit.
      </p>

      <div class="twist-box">
        <div class="twist-label">Twist</div>
        <div class="twist-text">The <em>gewone investeringsaftrek</em> was <strong>abolished from fiscal year 2024</strong> — replaced by the <em>verhoogde investeringsaftrek</em> with significantly higher rates (20–40%)</div>
      </div>

      <div class="rates-row">
        <div class="rate-card old">
          <div class="rate-era">≤ FY2023 <span class="era-sub">old system</span></div>
          <div class="rate-val">8%</div>
          <div class="rate-desc">Flat rate on all qualifying investments</div>
        </div>
        <div class="arrow-col">→</div>
        <div class="rate-card new">
          <div class="rate-era">FY2024+ <span class="era-sub">new system</span></div>
          <div class="rate-val">20–40%</div>
          <div class="rate-desc">40% for digitalisation &amp; energy, 20% for all other qualifying investments</div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); pointer-events: none; }
.orb { position: absolute; width: 500px; height: 500px; border-radius: 50%; filter: blur(120px); background: radial-gradient(circle, #ef4444 0%, #991b1b 60%, transparent 100%); top: -200px; right: -150px; opacity: 0.15; pointer-events: none; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 900px; padding: 36px 64px; display: flex; flex-direction: column; gap: 22px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 42px; font-weight: 700; color: #e2e8f0; margin: 0; letter-spacing: -0.03em; }
.description { font-size: 16px; color: #8896a8; line-height: 1.7; margin: 0; }

.twist-box {
  display: flex;
  align-items: center;
  gap: 20px;
  border-radius: 12px;
  padding: 18px 24px;
  border: 1px solid rgba(239,68,68,0.4);
  background: rgba(239,68,68,0.07);
}
.twist-label {
  font-size: 10px; font-weight: 700; letter-spacing: 0.15em; text-transform: uppercase; color: #ef4444; white-space: nowrap; padding: 4px 10px; border: 1px solid rgba(239,68,68,0.4); border-radius: 4px; background: rgba(239,68,68,0.15);
}
.twist-text { font-size: 15px; color: #cbd5e1; line-height: 1.5; }
.twist-text strong { color: #fca5a5; font-weight: 700; }
.twist-text em { color: #e2e8f0; font-style: normal; font-weight: 600; }

.rates-row { display: flex; align-items: center; gap: 16px; }
.rate-card { flex: 1; border-radius: 10px; padding: 18px 20px; display: flex; flex-direction: column; gap: 6px; }
.old { background: rgba(239,68,68,0.05); border: 1px solid rgba(239,68,68,0.25); }
.new { background: rgba(16,185,129,0.05); border: 1px solid rgba(16,185,129,0.25); }
.rate-era { font-size: 12px; font-weight: 700; color: #475569; letter-spacing: 0.05em; display: flex; align-items: baseline; gap: 8px; }
.era-sub { font-size: 10px; font-weight: 400; color: #334155; text-transform: uppercase; letter-spacing: 0.1em; }
.rate-val { font-size: 36px; font-weight: 700; letter-spacing: -0.03em; }
.old .rate-val { color: #fca5a5; }
.new .rate-val { color: #6ee7b7; }
.rate-desc { font-size: 12px; color: #475569; line-height: 1.5; }
.arrow-col { font-size: 28px; color: rgba(124,58,237,0.3); flex-shrink: 0; }
</style>
