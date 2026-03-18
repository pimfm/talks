<script setup>
const MAX_HOURS      = 1400
const MAX_MARGIN     = 88000
const MAX_INVESTMENT = 4500

function fmt(val, type) {
  if (type === 'hours') return val.toLocaleString('nl-NL') + 'h'
  return '€' + val.toLocaleString('nl-NL')
}

const richard = {
  name: 'Richard',
  profession: 'DJ',
  engagement: 'Part-time',
  seed: 'Daan',
  color: '#c084fc',
  border: 'rgba(192, 132, 252, 0.45)',
  glow: 'rgba(192, 132, 252, 0.25)',
  bg: 'rgba(192, 132, 252, 0.06)',
  hours: 50,
  margin: 800,
  investment: 300,
}

const laura = {
  name: 'Laura',
  profession: 'Marketeer',
  engagement: 'Full-time',
  seed: 'Laura',  // original long-hair seed
  color: '#7c3aed',
  border: 'rgba(124, 58, 237, 0.5)',
  glow: 'rgba(124, 58, 237, 0.3)',
  bg: 'rgba(124, 58, 237, 0.08)',
  hours: 1400,
  margin: 88000,
  investment: 4500,
}

const stats = [
  { label: 'Hours / year', key: 'hours',      max: MAX_HOURS,      type: 'hours' },
  { label: 'Gross margin',  key: 'margin',     max: MAX_MARGIN,     type: 'euro'  },
  { label: 'Investment',    key: 'investment', max: MAX_INVESTMENT, type: 'euro'  },
]
</script>

<template>
  <div class="characters-slide">

    <div class="bg-grid" />
    <div class="orb orb-l" />
    <div class="orb orb-r" />

    <div class="slide-inner">

      <div class="eyebrow">MEET THE TAXPAYERS</div>

      <div class="cards-row">

        <template v-for="(char, i) in [richard, laura]" :key="char.name">

          <div
            class="card"
            :style="{
              background: char.bg,
              borderColor: char.border,
              '--c': char.color,
              '--glow': char.glow,
            }"
          >
            <div class="card-top-line" :style="{ background: `linear-gradient(90deg, ${char.color}, transparent)` }" />

            <div class="avatar-wrap" :style="{ borderColor: char.border, boxShadow: `0 0 28px ${char.glow}` }">
              <img
                :src="`https://api.dicebear.com/9.x/adventurer/svg?seed=${char.seed}&backgroundColor=0f0b1e`"
                :alt="char.name"
                class="avatar-img"
              />
            </div>

            <div class="char-name">{{ char.name }}</div>

            <div class="profession-block">
              <span class="profession-label">{{ char.profession }}</span>
              <span class="engagement-label">{{ char.engagement }}</span>
            </div>

            <div class="stats-list">
              <div v-for="s in stats" :key="s.key" class="stat-item">
                <div class="stat-header">
                  <span class="stat-label">{{ s.label }}</span>
                  <span class="stat-value" :style="{ color: char.color }">{{ fmt(char[s.key], s.type) }}</span>
                </div>
                <div class="bar-track">
                  <div
                    class="bar-fill"
                    :style="{
                      width: Math.max(2, (char[s.key] / s.max) * 100) + '%',
                      background: `linear-gradient(90deg, ${char.color}, ${char.color}88)`,
                      boxShadow: `0 0 8px ${char.glow}`,
                    }"
                  />
                </div>
              </div>
            </div>

          </div>

          <div v-if="i === 0" class="amp">&amp;</div>

        </template>

      </div>

    </div>
  </div>
</template>

<style scoped>
.characters-slide {
  position: absolute;
  inset: 0;
  background: #060e1c;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
}

.bg-grid {
  position: absolute;
  inset: 0;
  background-image:
    linear-gradient(rgba(30, 58, 95, 0.22) 1px, transparent 1px),
    linear-gradient(90deg, rgba(30, 58, 95, 0.22) 1px, transparent 1px);
  background-size: 60px 60px;
  mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%);
  pointer-events: none;
}

.orb {
  position: absolute;
  border-radius: 50%;
  filter: blur(100px);
  pointer-events: none;
}
.orb-l {
  width: 400px; height: 400px;
  background: radial-gradient(circle, #c084fc 0%, #7c3aed 50%, transparent 100%);
  bottom: -150px; left: -100px;
  opacity: 0.2;
}
.orb-r {
  width: 350px; height: 350px;
  background: radial-gradient(circle, #7c3aed 0%, #4c1d95 60%, transparent 100%);
  top: -120px; right: -80px;
  opacity: 0.2;
}

.slide-inner {
  position: relative;
  z-index: 10;
  width: 100%;
  padding: 32px 64px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 24px;
}

.eyebrow {
  font-size: 15px;
  font-weight: 700;
  letter-spacing: 0.18em;
  color: rgba(192, 132, 252, 0.75);
  text-transform: uppercase;
}

/* ── Cards row ── */
.cards-row {
  display: flex;
  align-items: center;
  gap: 32px;
  width: 100%;
  justify-content: center;
}

/* ── Card ── */
.card {
  flex: 1;
  max-width: 320px;
  border: 1px solid;
  border-radius: 14px;
  padding: 24px 22px 22px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 12px;
  position: relative;
  overflow: hidden;
}

.card-top-line {
  position: absolute;
  top: 0; left: 0; right: 0;
  height: 2px;
}

/* ── Avatar ── */
.avatar-wrap {
  width: 130px;
  height: 130px;
  border-radius: 50%;
  overflow: hidden;
  border: 2px solid;
  flex-shrink: 0;
  background: #0f0b1e;
}

.avatar-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
}

/* ── Name + Profession ── */
.char-name {
  font-size: 26px;
  font-weight: 700;
  color: #e2e8f0;
  letter-spacing: -0.02em;
  margin-top: -2px;
}

.profession-block {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 2px;
  margin-top: -6px;
}

.profession-label {
  font-size: 17px;
  font-weight: 600;
  color: var(--c);
  letter-spacing: -0.01em;
}

.engagement-label {
  font-size: 11px;
  font-weight: 400;
  color: #4a6280;
  letter-spacing: 0.05em;
  text-transform: uppercase;
}

/* ── Stats ── */
.stats-list {
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-top: 4px;
  border-top: 1px solid rgba(30, 58, 95, 0.5);
  padding-top: 14px;
}

.stat-item {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.stat-header {
  display: flex;
  justify-content: space-between;
  align-items: baseline;
}

.stat-label {
  font-size: 10px;
  font-weight: 500;
  color: #4a6280;
  letter-spacing: 0.06em;
  text-transform: uppercase;
}

.stat-value {
  font-size: 13px;
  font-weight: 700;
  letter-spacing: -0.01em;
}

.bar-track {
  height: 4px;
  background: rgba(30, 58, 95, 0.5);
  border-radius: 2px;
  overflow: hidden;
}

.bar-fill {
  height: 100%;
  border-radius: 2px;
  transition: width 0.6s ease;
}

/* ── Ampersand ── */
.amp {
  font-size: 52px;
  font-weight: 300;
  color: rgba(124, 58, 237, 0.35);
  flex-shrink: 0;
  line-height: 1;
  user-select: none;
}
</style>
