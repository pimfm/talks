<script setup>
import { computed } from 'vue'

// states: Array<{ focused: 'richard' | 'laura' | 'both', code: string }>
const props = defineProps({
  clicks: { type: Number, default: 0 },
  states: { type: Array, default: () => [] },
})

const current = computed(() => {
  const idx = Math.min(props.clicks, props.states.length - 1)
  return props.states[idx] ?? { focused: 'richard', code: '' }
})

const focused = computed(() => current.value.focused)
const activeCode = computed(() => current.value.code)

function escapeHtml(s) {
  return s.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;')
}

// Simple Kotlin tokeniser — order matters (comments before strings, etc.)
function tokeniseLine(line) {
  const tokens = []
  let rest = line
  while (rest.length) {
    // line comment
    let m = rest.match(/^(\/\/.*)/)
    if (m) { tokens.push(['cm', m[1]]); break }
    // string literal
    m = rest.match(/^("(?:[^"\\]|\\.)*")/)
    if (m) { tokens.push(['st', m[1]]); rest = rest.slice(m[1].length); continue }
    // keyword
    m = rest.match(/^(val|var|fun|class|object|when|is|in|return|if|else|this|null|true|false)\b/)
    if (m) { tokens.push(['kw', m[1]]); rest = rest.slice(m[1].length); continue }
    // number literal
    m = rest.match(/^(\d[\d_]*L?)/)
    if (m) { tokens.push(['nm', m[1]]); rest = rest.slice(m[1].length); continue }
    // type name (starts with uppercase)
    m = rest.match(/^([A-Z][a-zA-Z0-9_]*)/)
    if (m) { tokens.push(['tp', m[1]]); rest = rest.slice(m[1].length); continue }
    // function call (lowercase identifier followed by `(`)
    m = rest.match(/^([a-z_][a-zA-Z0-9_]*)(?=\s*\()/)
    if (m) { tokens.push(['fn', m[1]]); rest = rest.slice(m[1].length); continue }
    // plain identifier / operator / whitespace — one char at a time in runs
    m = rest.match(/^[^a-zA-Z0-9_"\/]+|^[a-z_][a-zA-Z0-9_]*/)
    if (m) { tokens.push(['pl', m[0]]); rest = rest.slice(m[0].length); continue }
    // fallback
    tokens.push(['pl', rest[0]]); rest = rest.slice(1)
  }
  return tokens.map(([type, text]) => {
    const e = escapeHtml(text)
    if (type === 'pl') return e
    return `<span class="hl-${type}">${e}</span>`
  }).join('')
}

function highlightKotlin(raw, errorLines = new Set()) {
  return raw.split('\n').map((line, idx) => {
    const html = tokeniseLine(line)
    return errorLines.has(idx + 1) ? `<span class="hl-error-line">${html}</span>` : html
  }).join('\n')
}

const errorLineSet = computed(() => new Set(current.value.errorLines ?? []))
const highlighted = computed(() => highlightKotlin(activeCode.value ?? '', errorLineSet.value))

const richard = {
  name: 'Richard', role: 'DJ · Part-time',
  seed: 'Daan',
  color: '#c084fc',
  border: 'rgba(192,132,252,0.6)',
  glow: '0 0 40px rgba(192,132,252,0.35)',
}
const laura = {
  name: 'Laura', role: 'Marketeer · Full-time',
  seed: 'Laura',
  color: '#a78bfa',
  border: 'rgba(167,139,250,0.6)',
  glow: '0 0 40px rgba(167,139,250,0.35)',
}
</script>

<template>
  <div class="slide">
    <div class="bg-grid" />
    <div class="orb" />

    <div class="outer" :class="{ 'has-quote': current.quote || current.note }">

      <!-- Top 2/3: characters + code -->
      <div class="layout">

        <!-- Left: Richard -->
        <div class="char-col" :class="focused === 'richard' || focused === 'both' ? 'is-focused' : 'is-faded'">
          <div
            class="avatar-ring avatar-mirrored"
            :style="focused === 'richard' || focused === 'both'
              ? { borderColor: richard.border, boxShadow: richard.glow }
              : { borderColor: 'rgba(30,58,95,0.3)', boxShadow: 'none' }"
          >
            <img
              :src="`https://api.dicebear.com/9.x/adventurer/svg?seed=${richard.seed}&backgroundColor=0f0b1e`"
              :alt="richard.name"
              class="avatar-img"
            />
          </div>
          <div class="char-name" :style="focused === 'richard' || focused === 'both' ? { color: richard.color } : {}">{{ richard.name }}</div>
          <div class="char-role">{{ richard.role }}</div>
        </div>

        <!-- Center: Code -->
        <div class="code-col">
          <div class="code-window">
            <div class="code-dots">
              <span /><span /><span />
            </div>
            <!-- eslint-disable-next-line vue/no-v-html -->
            <pre class="code-pre"><code v-html="highlighted" /></pre>
          </div>
        </div>

        <!-- Right: Laura -->
        <div class="char-col char-right" :class="focused === 'laura' || focused === 'both' ? 'is-focused' : 'is-faded'">
          <div
            class="avatar-ring"
            :style="focused === 'laura' || focused === 'both'
              ? { borderColor: laura.border, boxShadow: laura.glow }
              : { borderColor: 'rgba(30,58,95,0.3)', boxShadow: 'none' }"
          >
            <img
              :src="`https://api.dicebear.com/9.x/adventurer/svg?seed=${laura.seed}&backgroundColor=0f0b1e`"
              :alt="laura.name"
              class="avatar-img"
            />
          </div>
          <div class="char-name" :style="focused === 'laura' || focused === 'both' ? { color: laura.color } : {}">{{ laura.name }}</div>
          <div class="char-role">{{ laura.role }}</div>
        </div>

      </div>

      <!-- Bottom 1/3: quote or note -->
      <div class="quote-area">
        <Transition name="quote-fade">
          <div v-if="current.quote" class="quote-box">
            <div class="quote-text">{{ current.quote }}</div>
            <div v-if="current.quoteAuthor" class="quote-author">— {{ current.quoteAuthor }}</div>
          </div>
          <div v-else-if="current.note" class="note-box">
            <span class="note-badge">Beta</span>
            <span class="note-text">{{ current.note }}</span>
          </div>
        </Transition>
      </div>

    </div>
  </div>
</template>

<style scoped>
.slide { position: absolute; inset: 0; background: #060e1c; overflow: hidden; display: flex; align-items: center; justify-content: center; }
.bg-grid { position: absolute; inset: 0; background-image: linear-gradient(rgba(30,58,95,0.2) 1px, transparent 1px), linear-gradient(90deg, rgba(30,58,95,0.2) 1px, transparent 1px); background-size: 60px 60px; mask-image: radial-gradient(ellipse 100% 100% at 50% 50%, black 20%, transparent 80%); pointer-events: none; }
.orb { position: absolute; width: 500px; height: 500px; border-radius: 50%; filter: blur(130px); background: radial-gradient(circle, #7c3aed 0%, #4c1d95 60%, transparent 100%); top: -220px; right: -160px; opacity: 0.18; pointer-events: none; }

.outer {
  position: relative;
  z-index: 10;
  display: flex;
  flex-direction: column;
  align-items: stretch;
  justify-content: center;
  width: 100%;
  height: 100%;
  max-width: 960px;
  padding: 0 40px;
}

.layout {
  display: grid;
  grid-template-columns: 180px 1fr 180px;
  align-items: center;
  gap: 32px;
  padding: 32px 0;
}

/* When a quote is present: top 2/3 for layout, bottom 1/3 for quote */
.outer.has-quote { justify-content: flex-start; }
.outer.has-quote .layout { flex: 2; }

.quote-area { display: none; }
.outer.has-quote .quote-area {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  padding-bottom: 24px;
}

/* ── Character columns ── */
.char-col {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 12px;
  transition: opacity 0.4s ease;
}
.is-focused { opacity: 1; }
.is-faded   { opacity: 0.18; }

.avatar-ring {
  width: 130px;
  height: 130px;
  border-radius: 50%;
  border: 2px solid;
  overflow: hidden;
  background: #0f0b1e;
  transition: border-color 0.4s ease, box-shadow 0.4s ease;
  flex-shrink: 0;
}

/* Flip Laura so she looks toward the center */
.avatar-mirrored { transform: scaleX(-1); }

.avatar-img { width: 100%; height: 100%; object-fit: cover; display: block; }

.char-name {
  font-size: 18px;
  font-weight: 700;
  color: #e2e8f0;
  letter-spacing: -0.02em;
  transition: color 0.4s ease;
}
.char-role {
  font-size: 11px;
  color: #4a6280;
  letter-spacing: 0.06em;
  text-transform: uppercase;
  text-align: center;
}

/* ── Code window ── */
.code-col { display: flex; flex-direction: column; align-items: stretch; justify-content: center; }

.code-window {
  width: 100%;
  border-radius: 12px;
  border: 1px solid rgba(30,58,95,0.6);
  background: rgba(6,14,28,0.9);
  overflow: hidden;
}

.code-dots {
  display: flex;
  gap: 6px;
  padding: 10px 14px 8px;
  border-bottom: 1px solid rgba(30,58,95,0.4);
}
.code-dots span {
  width: 10px; height: 10px;
  border-radius: 50%;
  background: rgba(30,58,95,0.6);
}
.code-dots span:nth-child(1) { background: rgba(239,68,68,0.4); }
.code-dots span:nth-child(2) { background: rgba(234,179,8,0.4); }
.code-dots span:nth-child(3) { background: rgba(34,197,94,0.4); }

.code-pre {
  margin: 0;
  padding: 22px 24px;
  font-size: 15px;
  line-height: 1.75;
  font-family: 'IBM Plex Mono', monospace;
  color: #e2e8f0;
}

/* ── Quote ── */
.quote-box {
  margin-top: 16px;
  padding: 16px 22px;
  border-radius: 10px;
  border: 1px solid rgba(124,58,237,0.35);
  background: rgba(124,58,237,0.08);
  text-align: center;
}
.quote-text {
  font-size: 17px;
  font-weight: 600;
  color: #e2e8f0;
  line-height: 1.4;
}
.quote-author {
  font-size: 13px;
  color: #8896a8;
  margin-top: 6px;
}
.quote-fade-enter-active, .quote-fade-leave-active { transition: opacity 0.4s ease, transform 0.4s ease; }
.quote-fade-enter-from, .quote-fade-leave-to { opacity: 0; transform: translateY(6px); }

/* ── Note (announcement badge) ── */
.note-box {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 14px;
  padding: 16px 28px;
  border-radius: 10px;
  border: 1px solid rgba(6,182,212,0.35);
  background: rgba(6,182,212,0.06);
  width: 100%;
}
.note-badge {
  font-size: 10px;
  font-weight: 700;
  letter-spacing: 0.15em;
  text-transform: uppercase;
  color: #06b6d4;
  padding: 4px 10px;
  border: 1px solid rgba(6,182,212,0.5);
  border-radius: 4px;
  background: rgba(6,182,212,0.12);
  white-space: nowrap;
}
.note-text {
  font-size: 17px;
  font-weight: 600;
  color: #e2e8f0;
}

/* Kotlin token colours */
:deep(.hl-kw) { color: #c084fc; }
:deep(.hl-tp) { color: #67e8f9; }
:deep(.hl-fn) { color: #a3e635; }
:deep(.hl-nm) { color: #fb923c; }
:deep(.hl-st) { color: #fde68a; }
:deep(.hl-cm) { color: #475569; }
:deep(.hl-error-line) {
  text-decoration: underline wavy #ef4444;
  text-decoration-skip-ink: none;
  text-underline-offset: 4px;
}
</style>
