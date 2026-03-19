<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />
    <div class="slide-inner">
      <div class="eyebrow">INKOMSTENBELASTING</div>
      <h2 class="title">Three boxes, <span class="accent">one return</span></h2>
      <div class="boxes-row">

        <div class="box-card box1 active-box">
          <div class="box-label">BOX 1</div>
          <div class="box-name">Werk &amp; Woning</div>
          <div class="box-desc">Income from employment, business profit, pension, own home benefit</div>
          <div class="box-rate">
            <span class="rate-label">Rate</span>
            <span class="rate-val">36.97% / 49.50%</span>
          </div>
          <div class="box-characters">
            <img src="https://api.dicebear.com/9.x/adventurer/svg?seed=Daan&backgroundColor=0f0b1e" class="mini-avatar" alt="Richard" />
            <img src="https://api.dicebear.com/9.x/adventurer/svg?seed=Laura&backgroundColor=0f0b1e" class="mini-avatar" alt="Laura" />
            <span class="char-note">Both Richard &amp; Laura</span>
          </div>
          <div class="focus-tag">↑ This talk</div>
        </div>

        <div class="box-card box2">
          <div class="box-label">BOX 2</div>
          <div class="box-name">Aanmerkelijk Belang</div>
          <div class="box-desc">Dividends and capital gains from ≥5% shareholding in a company</div>
          <div class="box-rate">
            <span class="rate-label">Rate</span>
            <span class="rate-val">24.5% / 31%</span>
          </div>
        </div>

        <div class="box-card box3">
          <div class="box-label">BOX 3</div>
          <div class="box-name">Sparen &amp; Beleggen</div>
          <div class="box-desc">Notional return on savings, investments, second homes, assets</div>
          <div class="box-rate">
            <span class="rate-label">Fictitious rate</span>
            <span class="rate-val">5.88% × 36%</span>
          </div>
          <div class="box-characters">
            <img src="https://api.dicebear.com/9.x/adventurer/svg?seed=Laura&backgroundColor=0f0b1e" class="mini-avatar" alt="Laura" />
            <span class="char-note">Laura (owns home)</span>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.orb { position: absolute; width: 500px; height: 500px; border-radius: 50%; filter: blur(120px); background: radial-gradient(circle, #7c3aed 0%, #4c1d95 60%, transparent 100%); top: -200px; right: -150px; opacity: 0.2; pointer-events: none; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 900px; padding: 36px 64px; display: flex; flex-direction: column; gap: 24px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 44px; font-weight: 700; color: #e2e8f0; margin: 0; letter-spacing: -0.02em; }
.accent { background: linear-gradient(135deg, #a855f7, #06b6d4); -webkit-background-clip: text; -webkit-text-fill-color: transparent; background-clip: text; }
.boxes-row { display: grid; grid-template-columns: repeat(3, 1fr); gap: 16px; align-items: stretch; }
.box-card { border-radius: 12px; padding: 20px; display: flex; flex-direction: column; gap: 10px; border: 1px solid rgba(30,58,95,0.5); background: rgba(255,255,255,0.02); position: relative; }
.active-box { border-color: rgba(168,85,247,0.5); background: rgba(124,58,237,0.07); }
.box1 { }
.box2 { opacity: 0.6; }
.box3 { opacity: 0.7; }
.box-label { font-size: 10px; font-weight: 700; letter-spacing: 0.2em; color: #a855f7; }
.box2 .box-label { color: #06b6d4; }
.box3 .box-label { color: #ec4899; }
.box-name { font-size: 16px; font-weight: 700; color: #e2e8f0; }
.box-desc { font-size: 12px; color: #8896a8; line-height: 1.5; flex: 1; }
.box-rate { display: flex; flex-direction: column; gap: 2px; padding: 8px 0; border-top: 1px solid rgba(30,58,95,0.4); }
.rate-label { font-size: 10px; color: #475569; text-transform: uppercase; letter-spacing: 0.08em; }
.rate-val { font-size: 13px; font-weight: 600; color: #e2e8f0; }
.box-characters { display: flex; align-items: center; gap: 6px; }
.mini-avatar { width: 24px; height: 24px; border-radius: 50%; border: 1px solid rgba(168,85,247,0.4); background: #0f0b1e; }
.char-note { font-size: 11px; color: #64748b; }
.focus-tag { font-size: 10px; font-weight: 700; color: #a855f7; text-transform: uppercase; letter-spacing: 0.1em; padding: 4px 10px; background: rgba(124,58,237,0.15); border: 1px solid rgba(124,58,237,0.3); border-radius: 4px; align-self: flex-start; }
</style>
