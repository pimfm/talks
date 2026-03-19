<script setup lang="ts">
defineProps<{
  label: string
  value: string | number
  subValue?: string
  color?: 'purple' | 'cyan' | 'green' | 'yellow' | 'red'
}>()
</script>

<template>
  <div :class="['stat-bar', `color-${color ?? 'purple'}`]">
    <div class="stat-label">{{ label }}</div>
    <div class="stat-value">{{ value }}</div>
    <div v-if="subValue" class="stat-sub">{{ subValue }}</div>
  </div>
</template>

<style scoped>
.stat-bar {
  background: var(--bg2);
  border: 1px solid var(--border);
  border-radius: 6px;
  padding: 16px 20px;
  display: flex;
  flex-direction: column;
  gap: 4px;
  position: relative;
  overflow: hidden;
}
.stat-bar::before {
  content: '';
  position: absolute;
  left: 0; top: 0; bottom: 0;
  width: 3px;
}
.color-purple::before { background: var(--purple-light); }
.color-cyan::before { background: var(--cyan); }
.color-green::before { background: var(--green); }
.color-yellow::before { background: var(--yellow); }
.color-red::before { background: var(--red); }

.stat-label { font-size: 11px; color: var(--text-muted); text-transform: uppercase; letter-spacing: 0.1em; }
.stat-value { font-size: 24px; font-weight: 600; color: var(--text); }
.stat-sub { font-size: 12px; color: var(--text-muted); }
</style>
