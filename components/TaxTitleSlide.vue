<template>
  <div class="tax-title-slide">

    <div class="tax-grid" />
    <div class="orb orb-purple" />
    <div class="orb orb-cyan" />

    <div class="content">
      <h1 class="title">
        <span class="title-line-1">The Dutch</span>
        <span class="title-line-2">Tax System</span>
      </h1>
      <div class="divider" />
      <div class="logo-wrap">
        <img src="/belastingdienst.png" alt="Belastingdienst" class="tax-logo" />
      </div>
    </div>

  </div>
</template>

<style scoped>
.tax-title-slide {
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
  pointer-events: none;
  animation: pulse 6s ease-in-out infinite alternate;
}

.orb-purple {
  width: 500px; height: 500px;
  background: radial-gradient(circle, #7c3aed 0%, #4c1d95 60%, transparent 100%);
  top: -120px; right: -80px;
  opacity: 0.55;
}

.orb-cyan {
  width: 350px; height: 350px;
  background: radial-gradient(circle, #06b6d4 0%, #0e7490 60%, transparent 100%);
  bottom: -100px; left: 30px;
  opacity: 0.35;
  animation-delay: -3s;
}

@keyframes pulse {
  from { opacity: 0.45; transform: scale(1); }
  to   { opacity: 0.65; transform: scale(1.08); }
}

.content {
  position: relative;
  z-index: 10;
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

.title-line-1 {
  font-size: 42px;
  font-weight: 300;
  color: #b8cfea;
  letter-spacing: -0.01em;
  display: block;
}

.title-line-2 {
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

.logo-wrap {
  display: flex;
  align-items: center;
  justify-content: center;
}

.tax-logo {
  width: 220px;
  height: auto;
  filter: brightness(2.5) saturate(2) drop-shadow(0 0 24px rgba(0, 180, 210, 0.65));
}
</style>
