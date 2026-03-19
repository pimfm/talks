<script setup>
defineProps({
  clicks: { type: Number, default: 0 },
})
</script>

<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb orb-purple" />
    <div class="orb orb-cyan" />

    <div class="slide-inner">
      <div class="eyebrow">CLOSING THOUGHTS</div>

      <Transition name="fade-up">
        <div v-if="clicks >= 1" class="card card-world">
          <div class="card-icon">
            <span class="icon-ring">⊕</span>
          </div>
          <div class="card-body">
            <div class="card-label">Open-Source Tax</div>
            <div class="card-text">
              With this type-safety, I hope we could one day open-source the Dutch and European
              tax system — so calculations are available programmatically, auditable by everyone.
            </div>
          </div>
        </div>
      </Transition>

      <Transition name="fade-up">
        <div v-if="clicks >= 2" class="card card-you">
          <div class="card-icon">
            <span class="icon-ring icon-ring-purple">✦</span>
          </div>
          <div class="card-body">
            <div class="card-label">For You</div>
            <div class="card-text">
              I hope concepts like <span class="hl-cyan">Arrow Raise DSL</span>,
              <span class="hl-purple">Context Parameters</span>, and
              <span class="hl-pink">Rich Errors</span> help you make the types in your
              projects richer — and have more flows handled by the compiler.
            </div>
            <div class="tags">
              <span class="tag tag-cyan">Arrow Raise DSL</span>
              <span class="tag tag-purple">Context Parameters</span>
              <span class="tag tag-pink">Rich Errors</span>
            </div>
          </div>
        </div>
      </Transition>
    </div>
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

.bg-grid {
  position: absolute;
  inset: 0;
  background-image:
    linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px),
    linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px);
  background-size: 60px 60px;
  mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%);
}

.orb { position: absolute; border-radius: 50%; filter: blur(100px); }
.orb-purple {
  width: 500px; height: 500px;
  background: radial-gradient(circle, #7c3aed 0%, #4c1d95 60%, transparent 100%);
  top: -180px; right: -120px;
  opacity: 0.28;
}
.orb-cyan {
  width: 320px; height: 320px;
  background: radial-gradient(circle, #06b6d4 0%, #0e7490 60%, transparent 100%);
  bottom: -100px; left: -60px;
  opacity: 0.18;
}

.slide-inner {
  position: relative;
  z-index: 10;
  width: 100%;
  max-width: 820px;
  padding: 36px 64px;
  display: flex;
  flex-direction: column;
  gap: 28px;
}

.eyebrow {
  font-size: 11px;
  font-weight: 700;
  letter-spacing: 0.2em;
  color: rgba(168,85,247,0.7);
  text-transform: uppercase;
}

/* ── Cards ── */
.card {
  display: flex;
  gap: 24px;
  align-items: flex-start;
  border-radius: 14px;
  padding: 24px 28px;
  border: 1px solid;
}

.card-world {
  border-color: rgba(6,182,212,0.3);
  background: rgba(6,182,212,0.05);
}

.card-you {
  border-color: rgba(168,85,247,0.3);
  background: rgba(124,58,237,0.06);
}

/* ── Icon ring ── */
.icon-ring {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 44px;
  height: 44px;
  border-radius: 50%;
  border: 1px solid rgba(6,182,212,0.4);
  background: rgba(6,182,212,0.1);
  color: #06b6d4;
  font-size: 20px;
  flex-shrink: 0;
  line-height: 1;
}

.icon-ring-purple {
  border-color: rgba(168,85,247,0.4);
  background: rgba(168,85,247,0.1);
  color: #a855f7;
}

/* ── Card body ── */
.card-body {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.card-label {
  font-size: 11px;
  font-weight: 700;
  letter-spacing: 0.14em;
  text-transform: uppercase;
  color: #475569;
}

.card-world .card-label { color: rgba(6,182,212,0.6); }
.card-you  .card-label { color: rgba(168,85,247,0.6); }

.card-text {
  font-size: 16px;
  color: #cbd5e1;
  line-height: 1.65;
}

.hl-cyan   { color: #67e8f9; font-weight: 600; }
.hl-purple { color: #c084fc; font-weight: 600; }
.hl-pink   { color: #f472b6; font-weight: 600; }

/* ── Tags ── */
.tags {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
  margin-top: 4px;
}

.tag {
  font-size: 11px;
  font-weight: 700;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  padding: 4px 10px;
  border-radius: 4px;
  border: 1px solid;
}

.tag-cyan   { color: #67e8f9; border-color: rgba(6,182,212,0.4);   background: rgba(6,182,212,0.08); }
.tag-purple { color: #c084fc; border-color: rgba(192,132,252,0.4); background: rgba(192,132,252,0.08); }
.tag-pink   { color: #f472b6; border-color: rgba(244,114,182,0.4); background: rgba(244,114,182,0.08); }

/* ── Transitions ── */
.fade-up-enter-active { transition: opacity 0.5s ease, transform 0.5s ease; }
.fade-up-enter-from   { opacity: 0; transform: translateY(14px); }
</style>
