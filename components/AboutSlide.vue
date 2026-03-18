<script setup>
import { computed } from 'vue'
const props = defineProps({ clicks: Number })

const activeBrand = computed(() => {
  if (props.clicks === 1) return 'flock'
  if (props.clicks === 2) return 'fastned'
  if (props.clicks === 3) return 'chair'
  if (props.clicks >= 4) return 'boardgames'
  return null
})

const boardgameIndex = computed(() =>
  props.clicks >= 4 ? Math.min(props.clicks - 4, 2) : 0
)

const bullets = [
  { id: 1, text: 'I work at ', accent: 'Flock',   accentClass: 'color-flock',   suffix: '' },
  { id: 2, text: 'I work at ', accent: 'Fastned', accentClass: 'color-fastned', suffix: ' through Flock' },
  { id: 3, text: 'I used to run my own SaaS company', accent: null },
  { id: 4, text: 'I like bouldering, and board games', accent: null },
]
</script>

<template>
  <div class="about-slide">

    <div class="tax-grid-about" />
    <div class="orb-about-purple" />
    <div class="orb-about-blue" />

    <div class="about-header">
      <h2 class="about-title">Hi, I'm Pim, and:</h2>
    </div>

    <div class="about-body">

      <div class="about-center">
        <div
          v-for="b in bullets"
          :key="b.id"
          class="about-bullet"
          :class="{ 'bullet-active': clicks >= b.id, 'bullet-past': clicks > b.id }"
        >
          <span class="bullet-dot" />
          <span v-if="b.accent">
            {{ b.text }}<span :class="b.accentClass">{{ b.accent }}</span>{{ b.suffix }}
          </span>
          <span v-else>{{ b.text }}</span>
        </div>
      </div>

      <div class="about-right">
        <Transition name="brand-fade" mode="out-in">
          <BrandCard
            v-if="activeBrand"
            :key="activeBrand"
            :brand="activeBrand"
            :boardgame-index="boardgameIndex"
          />
        </Transition>
      </div>

    </div>

  </div>
</template>

<style scoped>
.about-slide {
  position: absolute;
  inset: 0;
  background: #060e1c;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  padding: 40px 56px;
}

.tax-grid-about {
  position: absolute;
  inset: 0;
  background-image:
    linear-gradient(rgba(30, 58, 95, 0.2) 1px, transparent 1px),
    linear-gradient(90deg, rgba(30, 58, 95, 0.2) 1px, transparent 1px);
  background-size: 60px 60px;
  mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%);
  pointer-events: none;
}

.orb-about-purple {
  position: absolute;
  width: 400px; height: 400px;
  border-radius: 50%;
  filter: blur(100px);
  background: radial-gradient(circle, #7c3aed 0%, #4c1d95 60%, transparent 100%);
  top: -150px; right: -100px;
  opacity: 0.35;
  pointer-events: none;
}
.orb-about-blue {
  position: absolute;
  width: 300px; height: 300px;
  border-radius: 50%;
  filter: blur(90px);
  background: radial-gradient(circle, #1e3a5f 0%, #0a1628 60%, transparent 100%);
  bottom: -80px; left: -60px;
  opacity: 0.45;
  pointer-events: none;
}

/* ── Header ── */
.about-header {
  position: relative;
  z-index: 10;
  display: flex;
  align-items: center;
  padding-bottom: 24px;
  border-bottom: 1px solid rgba(30, 58, 95, 0.6);
  margin-bottom: 40px;
}

.about-title {
  font-size: 56px;
  font-weight: 700;
  color: #e2e8f0;
  letter-spacing: -0.03em;
  margin: 0;
}

/* ── Body ── */
.about-body {
  position: relative;
  z-index: 10;
  display: grid;
  grid-template-columns: 1fr 300px;
  align-items: center;
  gap: 48px;
  flex: 1;
}

/* ── Bullets ── */
.about-center {
  display: flex;
  flex-direction: column;
  gap: 22px;
}

.about-bullet {
  display: flex;
  align-items: center;
  gap: 14px;
  font-size: 21px;
  font-weight: 400;
  color: #4a6280;
  transition: color 0.35s ease, opacity 0.35s ease;
  opacity: 0;
}

.about-bullet.bullet-active {
  color: #e2e8f0;
  opacity: 1;
}

.about-bullet.bullet-past {
  color: #3d5266;
}

.color-flock   { color: #f5c800; font-weight: 600; }
.color-fastned { color: #f5c800; font-weight: 600; }

.bullet-dot {
  width: 7px;
  height: 7px;
  border-radius: 50%;
  background: #7c3aed;
  flex-shrink: 0;
  opacity: 0;
  transition: opacity 0.3s ease;
  box-shadow: 0 0 8px rgba(124, 58, 237, 0.8);
}

.bullet-active .bullet-dot {
  opacity: 1;
}

/* ── Right ── */
.about-right {
  height: 260px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.brand-fade-enter-active,
.brand-fade-leave-active {
  transition: opacity 0.25s ease, transform 0.25s ease;
}
.brand-fade-enter-from {
  opacity: 0;
  transform: translateY(8px);
}
.brand-fade-leave-to {
  opacity: 0;
  transform: translateY(-8px);
}
</style>
