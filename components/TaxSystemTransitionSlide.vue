<script setup>
const props = defineProps({ clicks: { type: Number, default: 0 } })
</script>

<template>
  <div class="slide">
    <div class="tax-grid" />
    <div class="orb orb-purple" />
    <div class="orb orb-cyan" />

    <!-- NL panel: launches out on click 1 -->
    <Transition name="nl-exit">
      <div v-if="props.clicks === 0" class="panel">
        <div class="content">
          <h1 class="title">
            <span class="line-1">The Dutch</span>
            <span class="line-2">Tax System</span>
          </h1>
          <div class="divider nl-divider" />
          <div class="logo-wrap">
            <img src="/belastingdienst.png" alt="Belastingdienst" class="tax-logo nl-logo" />
          </div>
        </div>
      </div>
    </Transition>

    <!-- BE panel: materialises from centre on click 1 -->
    <Transition name="be-enter">
      <div v-if="props.clicks >= 1" class="panel">
        <div class="content">
          <h1 class="title">
            <span class="line-1">The Belgian</span>
            <span class="line-2 be-gradient">Tax System</span>
          </h1>
          <div class="divider be-divider" />
          <div class="logo-wrap">
            <img src="/fod-financien.png" alt="FOD Financiën" class="tax-logo fod-logo" />
          </div>
        </div>
      </div>
    </Transition>

  </div>
</template>

<style scoped>
.slide {
  position: absolute;
  inset: 0;
  background: #060e1c;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
}

.tax-grid {
  position: absolute;
  inset: 0;
  background-image:
    linear-gradient(rgba(30, 58, 95, 0.35) 1px, transparent 1px),
    linear-gradient(90deg, rgba(30, 58, 95, 0.35) 1px, transparent 1px),
    linear-gradient(rgba(30, 58, 95, 0.12) 1px, transparent 1px),
    linear-gradient(90deg, rgba(30, 58, 95, 0.12) 1px, transparent 1px);
  background-size: 80px 80px, 80px 80px, 20px 20px, 20px 20px;
  mask-image: radial-gradient(ellipse 90% 80% at 50% 50%, black 30%, transparent 100%);
}

.orb {
  position: absolute;
  border-radius: 50%;
  filter: blur(80px);
  animation: pulse 6s ease-in-out infinite alternate;
  pointer-events: none;
}
.orb-purple {
  width: 500px; height: 500px;
  background: radial-gradient(circle, #7c3aed 0%, #4c1d95 60%, transparent 100%);
  top: -120px; right: -80px;
  opacity: 0.45;
}
.orb-cyan {
  width: 350px; height: 350px;
  background: radial-gradient(circle, #06b6d4 0%, #0e7490 60%, transparent 100%);
  bottom: -100px; left: 30px;
  opacity: 0.3;
  animation-delay: -3s;
}

@keyframes pulse {
  from { opacity: 0.35; transform: scale(1); }
  to   { opacity: 0.55; transform: scale(1.08); }
}

.panel {
  position: absolute;
  inset: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 10;
}

.content {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.title {
  display: flex;
  flex-direction: column;
  line-height: 1.0;
  margin: 0;
  padding: 0;
}

.line-1 {
  font-size: 42px;
  font-weight: 300;
  color: #b8cfea;
  letter-spacing: -0.01em;
  display: block;
}

.line-2 {
  font-size: 72px;
  font-weight: 700;
  background: linear-gradient(135deg, #a855f7 0%, #ec4899 50%, #06b6d4 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  letter-spacing: -0.03em;
  display: block;
  background-size: 200% 200%;
  animation: shimmer 4s ease-in-out infinite alternate;
}

.be-gradient {
  background: linear-gradient(135deg, #888 0%, #FDDA24 45%, #EF3340 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

@keyframes shimmer {
  from { background-position: 0% 50%; }
  to   { background-position: 100% 50%; }
}

.divider {
  width: 64px;
  height: 2px;
  margin: 24px auto;
}

.nl-divider {
  background: linear-gradient(90deg, transparent, #7c3aed, #ec4899, transparent);
}

.be-divider {
  background: linear-gradient(90deg, transparent, #FDDA24, #EF3340, transparent);
}

.logo-wrap {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;
}

.tax-logo {
  height: auto;
}

.nl-logo {
  width: 220px;
  filter: brightness(2.5) saturate(2) drop-shadow(0 0 24px rgba(0, 180, 210, 0.65));
}

.fod-logo {
  width: 300px;
  filter: invert(1) brightness(0.88) drop-shadow(0 0 20px rgba(253, 218, 36, 0.35));
}

.country-tag {
  font-size: 13px;
  font-weight: 600;
  letter-spacing: 0.08em;
  text-transform: uppercase;
  padding: 6px 16px;
  border-radius: 20px;
}

.be-tag {
  border: 1px solid rgba(253,218,36,0.35);
  background: rgba(253,218,36,0.06);
  color: rgba(253,218,36,0.75);
}

/* ── NL exit: catapults left and down with spin + blur ── */
.nl-exit-leave-active {
  transition:
    transform 0.55s cubic-bezier(0.55, 0, 1, 0.45),
    opacity   0.4s  ease,
    filter    0.4s  ease;
}
.nl-exit-leave-to {
  transform: translateX(-140%) translateY(8%) rotate(-14deg) scale(0.6);
  opacity: 0;
  filter: blur(12px);
}

/* ── BE enter: materialises from a focused point with spring bounce ── */
.be-enter-enter-active {
  transition:
    transform 0.75s cubic-bezier(0.34, 1.56, 0.64, 1),
    opacity   0.45s ease 0.15s,
    filter    0.45s ease 0.15s;
}
.be-enter-enter-from {
  transform: scale(0.45) translateY(24px);
  opacity: 0;
  filter: blur(16px);
}
</style>
