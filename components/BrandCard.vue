<script setup>
defineProps({
  brand: String,
  boardgameIndex: { type: Number, default: 0 },
})

const BOARDGAME_IMAGES = [
  { src: '/boardgame-1.jpg', pos: 'center center' },
  { src: '/boardgame-2.jpg', pos: 'center center' },
  { src: '/boardgame-3.jpg', pos: 'center center' },
]
</script>

<template>
  <div class="brand-card-inner">

    <div v-if="brand === 'flock'" class="circle-wrap flock-bg">
      <img src="/flock-logo.png" alt="Flock" class="circle-img contain-img" />
    </div>

    <div v-else-if="brand === 'fastned'" class="circle-wrap fastned-bg">
      <img src="/fastned-logo.png" alt="Fastned" class="circle-img contain-img" />
    </div>

    <div v-else-if="brand === 'chair'" class="circle-wrap">
      <img
        class="circle-img"
        src="/chair.jpg"
        alt="Muuto chair"
        style="object-position: center 55%"
      />
    </div>

    <div v-else-if="brand === 'boardgames'" class="circle-wrap boardgames-wrap">
      <img
        v-for="(img, i) in BOARDGAME_IMAGES"
        :key="i"
        :src="img.src"
        :alt="`Boardgame ${i + 1}`"
        class="circle-img stack-img"
        :class="{ 'stack-visible': i === boardgameIndex }"
        :style="`object-position: ${img.pos}`"
      />
    </div>

  </div>
</template>

<style scoped>
.brand-card-inner {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
  animation: fadeUp 0.35s ease forwards;
}

@keyframes fadeUp {
  from { opacity: 0; transform: translateY(10px); }
  to   { opacity: 1; transform: translateY(0); }
}

.circle-wrap {
  width: 200px;
  height: 200px;
  border-radius: 50%;
  overflow: hidden;
  border: 3px solid rgba(168, 85, 247, 0.55);
  box-shadow:
    0 0 0 6px rgba(124, 58, 237, 0.1),
    0 0 36px rgba(124, 58, 237, 0.25);
  flex-shrink: 0;
}

.circle-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: center center;
  display: block;
}

.contain-img {
  object-fit: contain;
  padding: 24px;
}

.flock-bg   { background: #1a0a2e; }
.fastned-bg { background: #1a1400; }

/* Stacked images — all pre-loaded, cross-fade on active */
.boardgames-wrap {
  position: relative;
}

.stack-img {
  position: absolute;
  inset: 0;
  opacity: 0;
  transition: opacity 0.18s ease;
}

.stack-img.stack-visible {
  opacity: 1;
}
</style>
