<script setup lang="ts">
import { ref } from 'vue'
import Dashboard from './views/Dashboard.vue'
import Calculator from './views/Calculator.vue'
import Tarieven from './views/Tarieven.vue'

type View = 'dashboard' | 'calculator' | 'tarieven'
const view = ref<View>('dashboard')
</script>

<template>
  <div class="app">
    <nav class="nav">
      <div class="nav-brand">
        <span class="brand-icon">λ</span>
        <span class="brand-name">OpenTax</span>
        <span class="brand-tag">type-safe</span>
      </div>
      <div class="nav-links">
        <button :class="{ active: view === 'dashboard' }" @click="view = 'dashboard'">Dashboard</button>
        <button :class="{ active: view === 'calculator' }" @click="view = 'calculator'">Aangifte</button>
        <button :class="{ active: view === 'tarieven' }" @click="view = 'tarieven'">Tarieven</button>
      </div>
    </nav>

    <main class="main">
      <Dashboard v-if="view === 'dashboard'" @navigate="view = $event" />
      <Calculator v-else-if="view === 'calculator'" />
      <Tarieven v-else-if="view === 'tarieven'" />
    </main>
  </div>
</template>

<style scoped>
.app { min-height: 100vh; display: flex; flex-direction: column; }

.nav {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 32px;
  height: 56px;
  background: var(--bg2);
  border-bottom: 1px solid var(--border);
  position: sticky;
  top: 0;
  z-index: 100;
}

.nav-brand { display: flex; align-items: center; gap: 10px; }
.brand-icon { font-size: 22px; color: var(--purple-light); font-weight: 700; }
.brand-name { font-size: 16px; font-weight: 600; color: var(--text); }
.brand-tag {
  font-size: 10px;
  color: var(--cyan);
  border: 1px solid var(--cyan);
  padding: 1px 6px;
  border-radius: 2px;
  letter-spacing: 0.1em;
}

.nav-links { display: flex; gap: 4px; }
.nav-links button {
  background: none;
  border: 1px solid transparent;
  color: var(--text-muted);
  padding: 6px 16px;
  cursor: pointer;
  font-size: 13px;
  border-radius: 4px;
  transition: all 0.15s;
}
.nav-links button:hover { color: var(--text); border-color: var(--border); }
.nav-links button.active {
  color: var(--purple-light);
  border-color: rgba(124, 58, 237, 0.4);
  background: rgba(124, 58, 237, 0.1);
}

.main { flex: 1; padding: 32px; max-width: 1200px; width: 100%; margin: 0 auto; }
</style>
