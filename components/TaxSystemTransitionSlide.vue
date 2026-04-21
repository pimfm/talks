<script setup>
const props = defineProps({ clicks: { type: Number, default: 0 } })
</script>

<template>
  <div class="slide">
    <div class="tax-grid" />
    <div class="orb orb-purple" />
    <div class="orb orb-cyan" />

    <!-- NL panel: exits left on click 1 -->
    <Transition name="slide-out-left">
      <div v-if="props.clicks === 0" class="panel">
        <div class="content">
          <h1 class="title">
            <span class="line-1">The Dutch</span>
            <span class="line-2">Tax System</span>
          </h1>
          <div class="divider" />
          <div class="logo-wrap">
            <img src="/belastingdienst.png" alt="Belastingdienst" class="tax-logo" />
          </div>
          <div class="country-tag">🇳🇱 Netherlands</div>
        </div>
      </div>
    </Transition>

    <!-- BE panel: enters from right on click 1 -->
    <Transition name="slide-in-right">
      <div v-if="props.clicks >= 1" class="panel">
        <div class="content">
          <h1 class="title">
            <span class="line-1">The Belgian</span>
            <span class="line-2 be-gradient">Tax System</span>
          </h1>
          <div class="divider be-divider" />
          <div class="logo-wrap">
            <img src="/fod-financien.svg" alt="FOD Financiën" class="tax-logo fod-logo" />
          </div>
          <div class="country-tag be-tag">🇧🇪 Belgium</div>
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

/* Panel — sits on top of background, centered */
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
  animation: shimmer 4s ease-in-out infinite alternate;
  background-size: 200% 200%;
}

.be-gradient {
  background: linear-gradient(135deg, #1a1a1a 0%, #FDDA24 40%, #EF3340 100%);
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
  background: linear-gradient(90deg, transparent, #7c3aed, #ec4899, transparent);
  margin: 24px auto;
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
  width: 220px;
  height: auto;
  filter: brightness(2.5) saturate(2) drop-shadow(0 0 24px rgba(0, 180, 210, 0.65));
}

.fod-logo {
  width: 280px;
  filter: none;
  drop-shadow: none;
  opacity: 0.9;
}

.country-tag {
  font-size: 13px;
  font-weight: 600;
  color: #475569;
  letter-spacing: 0.08em;
  text-transform: uppercase;
  padding: 6px 16px;
  border: 1px solid rgba(30,58,95,0.5);
  border-radius: 20px;
  background: rgba(30,58,95,0.2);
}

.be-tag {
  border-color: rgba(253,218,36,0.3);
  background: rgba(253,218,36,0.05);
  color: rgba(253,218,36,0.7);
}

/* ── Slide-out-left transition (NL exits) ── */
.slide-out-left-leave-active {
  transition: transform 0.5s cubic-bezier(0.4, 0, 0.2, 1), opacity 0.4s ease;
}
.slide-out-left-leave-to {
  transform: translateX(-110%);
  opacity: 0;
}

/* ── Slide-in-right transition (BE enters) ── */
.slide-in-right-enter-active {
  transition: transform 0.5s cubic-bezier(0.4, 0, 0.2, 1), opacity 0.4s ease;
}
.slide-in-right-enter-from {
  transform: translateX(110%);
  opacity: 0;
}
</style>
