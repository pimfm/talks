<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb orb-purple" />
    <div class="orb orb-cyan" />
    <div class="slide-inner">
      <div class="eyebrow">TAKEAWAYS</div>
      <h1 class="title">Summary</h1>
      <div class="points">
        <div class="point">
          <div class="point-num">01</div>
          <div class="point-content">
            <div class="point-title">Context parameters make implicit rules explicit</div>
            <div class="point-desc">The fiscal year flows through your codebase without threading — and violations are caught at compile time.</div>
          </div>
        </div>
        <div class="point">
          <div class="point-num">02</div>
          <div class="point-content">
            <div class="point-title">Arrow Raise replaces exceptions with typed errors</div>
            <div class="point-desc">Domain errors are values, not surprises. <code>UrencriteriumNotMet</code> is in the signature, not hidden.</div>
          </div>
        </div>
        <div class="point">
          <div class="point-num">03</div>
          <div class="point-content">
            <div class="point-title">Abolished rules become compile errors</div>
            <div class="point-desc">FOR after 2022 doesn't exist in the codebase — you can't call it. The type system is the law.</div>
          </div>
        </div>
        <div class="point">
          <div class="point-num">04</div>
          <div class="point-content">
            <div class="point-title">Every calculation step is traceable</div>
            <div class="point-desc">The full report shows every deduction, every bracket, every rate — auditable by design.</div>
          </div>
        </div>
      </div>
      <div class="footer-row">
        <div class="footer-item">Pim van Gurp</div>
        <div class="footer-dot">·</div>
        <div class="footer-item">Software Engineer @ Flock</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.orb { position: absolute; border-radius: 50%; filter: blur(100px); }
.orb-purple { width: 500px; height: 500px; background: radial-gradient(circle, #7c3aed 0%, #4c1d95 60%, transparent 100%); top: -200px; right: -150px; opacity: 0.3; }
.orb-cyan { width: 350px; height: 350px; background: radial-gradient(circle, #06b6d4 0%, #0e7490 60%, transparent 100%); bottom: -100px; left: -80px; opacity: 0.2; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 860px; padding: 40px 64px; display: flex; flex-direction: column; gap: 24px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 52px; font-weight: 700; color: #e2e8f0; margin: 0; letter-spacing: -0.03em; }
.points { display: flex; flex-direction: column; gap: 16px; }
.point { display: flex; gap: 20px; align-items: flex-start; }
.point-num { font-size: 14px; font-weight: 700; color: rgba(168,85,247,0.5); width: 28px; flex-shrink: 0; padding-top: 2px; }
.point-content { display: flex; flex-direction: column; gap: 3px; }
.point-title { font-size: 16px; font-weight: 600; color: #e2e8f0; }
.point-desc { font-size: 13px; color: #8896a8; line-height: 1.5; }
.point-desc code { color: #67e8f9; font-size: 12px; }
.footer-row { display: flex; align-items: center; gap: 12px; padding-top: 8px; border-top: 1px solid rgba(30,58,95,0.4); }
.footer-item { font-size: 13px; color: #475569; }
.footer-dot { color: rgba(124,58,237,0.4); }
</style>
