<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />
    <div class="slide-inner">
      <div class="eyebrow">SECTION 01</div>
      <h2 class="title">1. Sociale Bijdragen</h2>
      <p class="description">
        Belgian self-employed pay mandatory <strong>social contributions</strong> of 20.5% of their net professional income —
        deducted before income tax is calculated.
      </p>

      <div class="requirement-box">
        <div class="req-label">Threshold</div>
        <div class="req-text">For <strong>bijberoep</strong> (secondary occupation): contributions are only owed when annual income exceeds <strong>€ 1,922</strong></div>
      </div>

      <div class="cap-row">
        <div class="cap-item">
          <div class="cap-label">Rate</div>
          <div class="cap-value">20.5%</div>
          <div class="cap-sub">of net professional income</div>
        </div>
        <div class="cap-divider" />
        <div class="cap-item">
          <div class="cap-label">Bijberoep threshold</div>
          <div class="cap-value">€ 1,922</div>
          <div class="cap-sub">below this → no contributions</div>
        </div>
        <div class="cap-divider" />
        <div class="cap-item">
          <div class="cap-label">Hoofdberoep</div>
          <div class="cap-value">Always due</div>
          <div class="cap-sub">minimum flat if income too low</div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); pointer-events: none; }
.orb { position: absolute; width: 500px; height: 500px; border-radius: 50%; filter: blur(120px); background: radial-gradient(circle, #7c3aed 0%, #4c1d95 60%, transparent 100%); top: -200px; right: -150px; opacity: 0.2; pointer-events: none; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 900px; padding: 36px 64px; display: flex; flex-direction: column; gap: 24px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 44px; font-weight: 700; color: #e2e8f0; margin: 0; letter-spacing: -0.03em; }
.description { font-size: 16px; color: #8896a8; line-height: 1.7; margin: 0; }
.description strong { color: #e2e8f0; }

.requirement-box {
  display: flex;
  align-items: center;
  gap: 20px;
  border-radius: 12px;
  padding: 18px 24px;
  border: 1px solid rgba(168,85,247,0.4);
  background: rgba(124,58,237,0.07);
}
.req-label {
  font-size: 10px;
  font-weight: 700;
  letter-spacing: 0.15em;
  text-transform: uppercase;
  color: #a855f7;
  white-space: nowrap;
  padding: 4px 10px;
  border: 1px solid rgba(124,58,237,0.4);
  border-radius: 4px;
  background: rgba(124,58,237,0.15);
}
.req-text { font-size: 15px; color: #cbd5e1; line-height: 1.5; }
.req-text strong { color: #e2e8f0; font-weight: 700; }

.cap-row {
  display: flex;
  align-items: center;
  gap: 0;
  border: 1px solid rgba(30,58,95,0.5);
  border-radius: 10px;
  overflow: hidden;
}
.cap-item {
  flex: 1;
  padding: 16px 20px;
  display: flex;
  flex-direction: column;
  gap: 4px;
}
.cap-divider {
  width: 1px;
  align-self: stretch;
  background: rgba(30,58,95,0.5);
}
.cap-label { font-size: 10px; font-weight: 600; letter-spacing: 0.1em; text-transform: uppercase; color: #475569; }
.cap-value { font-size: 24px; font-weight: 700; color: #a855f7; letter-spacing: -0.02em; }
.cap-sub { font-size: 11px; color: #475569; }
</style>
