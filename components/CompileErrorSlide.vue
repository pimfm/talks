<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb orb-red" />
    <div class="orb orb-purple" />
    <div class="slide-inner">
      <div class="stamp">COMPILE ERROR</div>
      <h1 class="title">The best kind of error</h1>
      <h2 class="subtitle">is a <span class="accent">compile error</span></h2>
      <div class="reasons">
        <div class="reason">
          <span class="reason-icon">0ms</span>
          <span class="reason-text">Caught before the program runs</span>
        </div>
        <div class="reason">
          <span class="reason-icon">€0</span>
          <span class="reason-text">No tax filed incorrectly</span>
        </div>
        <div class="reason">
          <span class="reason-icon">∅</span>
          <span class="reason-text">No runtime, no user, no prod incident</span>
        </div>
      </div>
      <div class="quote">
        Type systems are lightweight formal methods — they prove properties about your program<br/>
        <span class="quote-attr">— the compiler, silently, every build</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.orb { position: absolute; border-radius: 50%; filter: blur(120px); }
.orb-red { width: 400px; height: 400px; background: radial-gradient(circle, #ef4444 0%, #7f1d1d 60%, transparent 100%); bottom: -150px; left: -100px; opacity: 0.15; }
.orb-purple { width: 500px; height: 500px; background: radial-gradient(circle, #7c3aed 0%, #4c1d95 60%, transparent 100%); top: -200px; right: -150px; opacity: 0.25; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 800px; padding: 48px 64px; display: flex; flex-direction: column; align-items: center; gap: 28px; text-align: center; }
.stamp { font-size: 10px; font-weight: 700; letter-spacing: 0.2em; color: rgba(239,68,68,0.8); border: 2px solid rgba(239,68,68,0.4); padding: 6px 18px; border-radius: 2px; transform: rotate(-2deg); }
.title { font-size: 56px; font-weight: 300; color: #e2e8f0; margin: 0; letter-spacing: -0.03em; }
.subtitle { font-size: 56px; font-weight: 700; margin: 0; letter-spacing: -0.03em; color: #e2e8f0; }
.accent { background: linear-gradient(135deg, #ef4444, #a855f7); -webkit-background-clip: text; -webkit-text-fill-color: transparent; background-clip: text; }
.reasons { display: flex; gap: 32px; }
.reason { display: flex; flex-direction: column; align-items: center; gap: 8px; }
.reason-icon { font-size: 28px; font-weight: 700; color: #a855f7; }
.reason-text { font-size: 13px; color: #8896a8; text-align: center; max-width: 140px; }
.quote { font-size: 15px; color: #475569; font-style: italic; max-width: 600px; line-height: 1.6; border-left: 2px solid rgba(124,58,237,0.4); padding-left: 20px; text-align: left; }
.quote-attr { color: #7c3aed; font-style: normal; font-size: 13px; }
</style>
