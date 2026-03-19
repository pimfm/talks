<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb orb-l" />
    <div class="orb orb-r" />
    <div class="slide-inner">
      <div class="eyebrow">OUR TWO TAXPAYERS</div>

      <div class="cards-row">
        <div class="detail-card richard">
          <div class="card-accent richard-accent" />
          <div class="card-avatar">
            <img src="https://api.dicebear.com/9.x/adventurer/svg?seed=Daan&backgroundColor=0f0b1e" alt="Richard" />
          </div>
          <div class="char-name">Richard</div>
          <div class="char-role">Part-time DJ</div>
          <div class="detail-list">
            <div class="detail-row"><span class="detail-dot warn" />No partner</div>
            <div class="detail-row"><span class="detail-dot warn" />Rents his apartment</div>
            <div class="detail-row"><span class="detail-dot warn" />50 hours/year in business</div>
            <div class="detail-row"><span class="detail-dot warn" />€800 gross income</div>
            <div class="detail-row"><span class="detail-dot warn" />€300 investment</div>
          </div>
          <div class="char-verdict error">Urencriterium: 50/1225 ✗</div>
        </div>

        <div class="vs-sep">vs</div>

        <div class="detail-card laura">
          <div class="card-accent laura-accent" />
          <div class="card-avatar">
            <img src="https://api.dicebear.com/9.x/adventurer/svg?seed=Laura&backgroundColor=0f0b1e" alt="Laura" />
          </div>
          <div class="char-name">Laura</div>
          <div class="char-role">Full-time Marketeer</div>
          <div class="detail-list">
            <div class="detail-row"><span class="detail-dot ok" />Has a partner</div>
            <div class="detail-row"><span class="detail-dot ok" />Owns her home</div>
            <div class="detail-row"><span class="detail-dot ok" />1400 hours/year in business</div>
            <div class="detail-row"><span class="detail-dot ok" />€88,000 gross income</div>
            <div class="detail-row"><span class="detail-dot ok" />€6,500 investment</div>
          </div>
          <div class="char-verdict success">Urencriterium: 1400/1225 ✓</div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.orb { position: absolute; border-radius: 50%; filter: blur(100px); }
.orb-l { width: 400px; height: 400px; background: radial-gradient(circle, #f59e0b 0%, #92400e 60%, transparent 100%); bottom: -150px; left: -100px; opacity: 0.12; }
.orb-r { width: 380px; height: 380px; background: radial-gradient(circle, #06b6d4 0%, #0e7490 60%, transparent 100%); top: -120px; right: -80px; opacity: 0.15; }
.slide-inner { position: relative; z-index: 10; width: 100%; padding: 32px 64px; display: flex; flex-direction: column; align-items: center; gap: 24px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.cards-row { display: flex; align-items: stretch; gap: 28px; width: 100%; justify-content: center; }
.detail-card { flex: 1; max-width: 340px; background: rgba(255,255,255,0.03); border: 1px solid rgba(30,58,95,0.6); border-radius: 14px; padding: 24px; display: flex; flex-direction: column; align-items: center; gap: 10px; position: relative; overflow: hidden; }
.card-accent { position: absolute; top: 0; left: 0; right: 0; height: 3px; }
.richard-accent { background: linear-gradient(90deg, #f59e0b, transparent); }
.laura-accent { background: linear-gradient(90deg, #06b6d4, transparent); }
.card-avatar { width: 80px; height: 80px; border-radius: 50%; overflow: hidden; border: 2px solid rgba(30,58,95,0.6); background: #0f0b1e; }
.card-avatar img { width: 100%; height: 100%; object-fit: cover; }
.char-name { font-size: 22px; font-weight: 700; color: #e2e8f0; }
.char-role { font-size: 13px; color: #8896a8; }
.detail-list { width: 100%; display: flex; flex-direction: column; gap: 6px; border-top: 1px solid rgba(30,58,95,0.4); padding-top: 12px; }
.detail-row { display: flex; align-items: center; gap: 8px; font-size: 13px; color: #b8cfea; }
.detail-dot { width: 6px; height: 6px; border-radius: 50%; flex-shrink: 0; }
.detail-dot.ok { background: #10b981; }
.detail-dot.warn { background: #f59e0b; }
.char-verdict { font-size: 12px; font-weight: 600; padding: 6px 14px; border-radius: 20px; margin-top: 4px; }
.char-verdict.error { background: rgba(239,68,68,0.15); color: #ef4444; border: 1px solid rgba(239,68,68,0.3); }
.char-verdict.success { background: rgba(16,185,129,0.15); color: #10b981; border: 1px solid rgba(16,185,129,0.3); }
.vs-sep { font-size: 32px; font-weight: 300; color: rgba(124,58,237,0.3); display: flex; align-items: center; }
</style>
