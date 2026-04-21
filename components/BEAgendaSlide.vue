<script setup>
import { computed } from 'vue'
const props = defineProps({ clicks: Number })

const state = (index) => computed(() => {
  if (props.clicks < index) return 'hidden'
  if (props.clicks === index) return 'focused'
  return 'grayed'
})

const s1 = state(0)
const s2 = state(1)
</script>

<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />
    <div class="slide-inner">
      <div class="eyebrow">AGENDA</div>
      <h2 class="title">Wat we vandaag <span class="accent">behandelen</span></h2>
      <div class="sections">

        <div class="section-card" :class="s1">
          <div class="section-number">01</div>
          <div class="section-body">
            <div class="section-name">Forfaitaire Beroepskosten</div>
            <div class="section-desc">Hoe Arrow's Raise DSL domeinfouten in het typesysteem brengt</div>
          </div>
          <div class="section-tag">Raise DSL</div>
        </div>

        <div class="section-card" :class="s2">
          <div class="section-number">02</div>
          <div class="section-body">
            <div class="section-name">Investeringsaftrek <span class="section-name-sub">Gewone → Verhoogde</span></div>
            <div class="section-desc">Hoe contextparameters helpen als belastingregels wegvallen of veranderen</div>
          </div>
          <div class="section-tag">Context Parameters</div>
        </div>

      </div>
    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); }
.orb { position: absolute; width: 500px; height: 500px; border-radius: 50%; filter: blur(120px); background: radial-gradient(circle, #7c3aed 0%, #4c1d95 60%, transparent 100%); top: -200px; right: -150px; opacity: 0.2; pointer-events: none; }
.slide-inner { position: relative; z-index: 10; width: 100%; max-width: 900px; padding: 36px 64px; display: flex; flex-direction: column; gap: 28px; }
.eyebrow { font-size: 11px; font-weight: 700; letter-spacing: 0.2em; color: rgba(168,85,247,0.7); text-transform: uppercase; }
.title { font-size: 44px; font-weight: 700; color: #e2e8f0; margin: 0; letter-spacing: -0.02em; }
.accent { background: linear-gradient(135deg, #a855f7, #06b6d4); -webkit-background-clip: text; -webkit-text-fill-color: transparent; background-clip: text; }
.sections { display: flex; flex-direction: column; gap: 14px; }
.section-card { display: flex; align-items: center; gap: 20px; border-radius: 12px; padding: 18px 22px; border: 1px solid rgba(30,58,95,0.5); background: rgba(255,255,255,0.02); transition: opacity 0.4s ease, border-color 0.4s ease, background 0.4s ease, transform 0.4s ease; }
.section-card.focused { border-color: rgba(168,85,247,0.5); background: rgba(124,58,237,0.07); opacity: 1; transform: translateX(4px); }
.section-card.grayed { opacity: 0.3; transform: translateX(0); }
.section-card.hidden { opacity: 0; transform: translateY(6px); pointer-events: none; }
.section-number { font-size: 28px; font-weight: 700; color: rgba(168,85,247,0.25); min-width: 40px; letter-spacing: -0.03em; }
.section-body { flex: 1; display: flex; flex-direction: column; gap: 4px; }
.section-name { font-size: 17px; font-weight: 700; color: #e2e8f0; display: flex; align-items: baseline; gap: 8px; }
.section-name-sub { font-size: 12px; font-weight: 400; color: #64748b; }
.section-desc { font-size: 12px; color: #8896a8; line-height: 1.5; }
.section-tag { font-size: 10px; font-weight: 700; color: #a855f7; text-transform: uppercase; letter-spacing: 0.1em; padding: 4px 10px; background: rgba(124,58,237,0.15); border: 1px solid rgba(124,58,237,0.3); border-radius: 4px; white-space: nowrap; }
</style>
