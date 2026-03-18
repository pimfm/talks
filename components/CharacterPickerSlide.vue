<script setup>
const lauraSeeds = ['Merel', 'Silke', 'Elske', 'Noor', 'Femke', 'Lianne', 'Jana', 'Tineke', 'Wieke', 'Marjan']
</script>

<template>
  <div class="picker-slide">
    <div class="section-label">Richard — confirmed: Daan</div>
    <div class="row">
      <div class="pick-card pick-selected">
        <div class="avatar-wrap selected-wrap">
          <img
            src="https://api.dicebear.com/9.x/adventurer/svg?seed=Daan&backgroundColor=0f0b1e"
            alt="Daan"
            class="avatar-img"
          />
        </div>
        <div class="seed-label selected-label">Daan</div>
      </div>
    </div>

    <div class="section-label" style="margin-top: 24px">Laura — pick one</div>
    <div class="row">
      <div v-for="seed in lauraSeeds" :key="seed" class="pick-card">
        <div class="avatar-wrap">
          <img
            :src="`https://api.dicebear.com/9.x/adventurer/svg?seed=${seed}&backgroundColor=0f0b1e`"
            :alt="seed"
            class="avatar-img"
          />
        </div>
        <div class="seed-label">{{ seed }}</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.picker-slide {
  position: absolute;
  inset: 0;
  background: #060e1c;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 32px 48px;
  gap: 12px;
}
.section-label {
  font-size: 11px;
  font-weight: 700;
  letter-spacing: 0.2em;
  text-transform: uppercase;
  color: #7c3aed;
  align-self: flex-start;
}
.row {
  display: flex;
  gap: 20px;
}
.pick-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 8px;
}
.avatar-wrap {
  width: 110px;
  height: 110px;
  border-radius: 50%;
  overflow: hidden;
  border: 2px solid rgba(124, 58, 237, 0.4);
  background: #0f0b1e;
}
.avatar-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
}
.seed-label {
  font-size: 12px;
  color: #64748b;
  letter-spacing: 0.05em;
}
.selected-wrap {
  border-color: rgba(192, 132, 252, 0.8);
  box-shadow: 0 0 20px rgba(192, 132, 252, 0.3);
}
.selected-label {
  color: #c084fc;
  font-weight: 600;
}
</style>
