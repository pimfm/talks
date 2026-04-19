<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />
    <div class="slide-inner">
      <div class="eyebrow">SECTION 01</div>
      <h2 class="title">1. Zelfstandigenaftrek</h2>
      <p class="description">
        A fixed deduction from gross income for entrepreneurs in the Netherlands — reducing how much income tax they pay.
      </p>

      <div class="requirement-box">
        <div class="req-label">Requirement</div>
        <div class="req-text">Only applicable when you spend <strong>1225 hours</strong> working on your company</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); pointer-events: none; }
.orb { position: absolute; width: 500px; height: 500px; border-radius: 50%; filter: blur(120px); background: radial-gradient(circle, #7c3aed 0%, #4c1d95 60%, transparent 100%); top: -200px; right: -150px; opacity: 0.2; pointer-events: none; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 900px; padding: 36px 64px; display: flex; flex-direction: column; gap: 28px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 52px; font-weight: 700; color: #e2e8f0; margin: 0; letter-spacing: -0.03em; }
.description { font-size: 17px; color: #8896a8; line-height: 1.7; margin: 0; }

.requirement-box {
  display: flex;
  align-items: center;
  gap: 20px;
  border-radius: 12px;
  padding: 20px 28px;
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
.req-text {
  font-size: 16px;
  color: #cbd5e1;
  line-height: 1.5;
}
.req-text strong {
  color: #e2e8f0;
  font-weight: 700;
}
</style>
