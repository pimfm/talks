<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />
    <div class="slide-inner">
      <div class="eyebrow">THE OPENTAX API</div>
      <h2 class="title">Route structure</h2>
      <div class="routes-grid">
        <div class="route-group">
          <div class="group-label cyan">Netherlands</div>
          <div class="route-list">
            <div class="route"><span class="method post">POST</span><span class="path">/nl/aangifte</span><span class="route-desc">Full tax report</span></div>
            <div class="route"><span class="method get">GET</span><span class="path">/nl/tarieven/{year}</span><span class="route-desc">All rates for year</span></div>
            <div class="route"><span class="method get">GET</span><span class="path">/nl/schijven/{year}</span><span class="route-desc">Box 1 brackets</span></div>
          </div>
        </div>
        <div class="route-group">
          <div class="group-label purple">Belgium comparison</div>
          <div class="route-list">
            <div class="route"><span class="method post">POST</span><span class="path">/be/aangifte</span><span class="route-desc">NL vs BE comparison</span></div>
          </div>
        </div>
        <div class="route-group">
          <div class="group-label yellow">Profiles</div>
          <div class="route-list">
            <div class="route"><span class="method post">POST</span><span class="path">/profile</span><span class="route-desc">Save user profile</span></div>
            <div class="route"><span class="method get">GET</span><span class="path">/profile/{id}</span><span class="route-desc">Get profile</span></div>
            <div class="route"><span class="method post">POST</span><span class="path">/profile/{id}/aangifte</span><span class="route-desc">Calculate for profile</span></div>
          </div>
        </div>
      </div>
      <div class="stack-row">
        <div class="stack-item">Ktor 3</div>
        <div class="stack-sep">+</div>
        <div class="stack-item">Arrow 2</div>
        <div class="stack-sep">+</div>
        <div class="stack-item">Kotlin 2.3</div>
        <div class="stack-sep">+</div>
        <div class="stack-item">Context Parameters</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.orb { position: absolute; width: 400px; height: 400px; border-radius: 50%; filter: blur(100px); background: radial-gradient(circle, #7c3aed 0%, #4c1d95 60%, transparent 100%); top: -150px; right: -100px; opacity: 0.2; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 860px; padding: 32px 64px; display: flex; flex-direction: column; gap: 20px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 44px; font-weight: 700; color: #e2e8f0; margin: 0; }
.routes-grid { display: flex; flex-direction: column; gap: 16px; }
.route-group { background: rgba(255,255,255,0.02); border: 1px solid rgba(30,58,95,0.5); border-radius: 8px; overflow: hidden; }
.group-label { font-size: 11px; font-weight: 700; letter-spacing: 0.1em; text-transform: uppercase; padding: 7px 16px; }
.group-label.cyan { background: rgba(6,182,212,0.1); color: #06b6d4; }
.group-label.purple { background: rgba(124,58,237,0.1); color: #a855f7; }
.group-label.yellow { background: rgba(245,158,11,0.1); color: #f59e0b; }
.route-list { padding: 8px 16px; display: flex; flex-direction: column; gap: 6px; }
.route { display: flex; align-items: center; gap: 12px; font-size: 13px; }
.method { font-weight: 700; font-size: 10px; padding: 2px 7px; border-radius: 3px; letter-spacing: 0.05em; }
.method.post { background: rgba(124,58,237,0.2); color: #a855f7; }
.method.get { background: rgba(16,185,129,0.2); color: #10b981; }
.path { color: #e2e8f0; font-family: monospace; }
.route-desc { color: #475569; font-size: 12px; }
.stack-row { display: flex; align-items: center; gap: 12px; flex-wrap: wrap; }
.stack-item { background: rgba(124,58,237,0.1); border: 1px solid rgba(124,58,237,0.3); color: #a855f7; font-size: 13px; padding: 6px 14px; border-radius: 20px; }
.stack-sep { color: rgba(124,58,237,0.3); font-size: 18px; }
</style>
