---
theme: default
title: "Type-Safe Tax Calculations"
author: "Pim van Gurp"
colorSchema: dark
aspectRatio: 16/9
canvasWidth: 980
fonts:
  sans: "IBM Plex Mono"
  mono: "IBM Plex Mono"
  weights: "300,400,500,600,700"
defaults:
  layout: default
---

<div class="slide-cover">

  <div class="tax-grid" />
  <div class="orb orb-purple" />
  <div class="orb orb-cyan" />

  <div class="cover-content">
    <h1 class="cover-title">
      <span class="title-line-1">Type-Safe</span>
      <span class="title-line-2">Tax Calculations</span>
    </h1>
    <div class="cover-divider" />
    <p class="cover-subtitle">Using Context Parameters &amp; Arrow's Raise DSL</p>
    <div class="cover-meta">
      <img class="cover-avatar" src="/pim.png" alt="Pim van Gurp" />
      <span class="speaker">Pim van Gurp</span>
      <span class="dot">·</span>
      <span class="company">Software Engineer @ Flock</span>
    </div>
  </div>

  <div class="stamp">BOX 3<br/>COMPILING...</div>

</div>

<style>
.slide-cover {
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
  animation-delay: -3s;
  opacity: 0.35;
}

@keyframes pulse {
  from { opacity: 0.45; transform: scale(1); }
  to   { opacity: 0.65; transform: scale(1.08); }
}

.cover-content {
  position: relative;
  z-index: 10;
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
}


.cover-title {
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

.cover-divider {
  width: 64px;
  height: 2px;
  background: linear-gradient(90deg, transparent, #7c3aed, #ec4899, transparent);
  margin: 24px auto;
}

.cover-subtitle {
  font-size: 18px;
  font-weight: 400;
  color: #b8d4ef;
  letter-spacing: 0.01em;
  margin: 0 0 28px 0;
}

.cover-meta {
  display: flex;
  align-items: center;
  gap: 12px;
  font-size: 13px;
}
.cover-avatar {
  width: 36px;
  height: 36px;
  border-radius: 50%;
  object-fit: cover;
  border: 2px solid rgba(168, 85, 247, 0.6);
  box-shadow: 0 0 12px rgba(124, 58, 237, 0.4);
}
.cover-meta .speaker {
  color: #e2e8f0;
  font-weight: 500;
}
.cover-meta .dot { color: #7c3aed; }
.cover-meta .company { color: #8896a8; }

.stamp {
  position: absolute;
  bottom: 36px; right: 40px;
  border: 2px solid rgba(168, 85, 247, 0.75);
  color: rgba(196, 140, 255, 0.85);
  font-family: 'JetBrains Mono', monospace;
  font-size: 8px;
  font-weight: 700;
  letter-spacing: 0.15em;
  text-align: center;
  line-height: 1.6;
  padding: 6px 10px;
  transform: rotate(-12deg);
  text-transform: uppercase;
  text-shadow: 0 0 10px rgba(168, 85, 247, 0.5);
}
</style>

---
layout: none
clicks: 6
---

<AboutSlide :clicks="$clicks" />

<!--
And the best board game of them all, with the highest stakes...
-->

---
layout: none
---

<TaxTitleSlide />

---
layout: none
---

<BoxSystemSlide />

---
layout: none
---

<CharactersSlide />

---
layout: none
---

<CharacterDetailsSlide />

---
layout: none
---

<TypeSafetySlide />

---
layout: none
---

<RaiseDSLSlide />

---
layout: none
---

<RaiseUsageSlide />

---
layout: none
---

<RaiseVsExceptionsSlide />

---
layout: none
---

<ContextParamsSlide />

---
layout: none
---

<ContextParamsSyntaxSlide />

---
layout: none
---

<ContextForTaxYearSlide />

---
layout: none
---

<ZelfstandigenAftrekSlide />

---
layout: none
---

<FORSlide />

---
layout: none
---

<CompileErrorSlide />

---
layout: none
---

<RichardAangifteSlide />

---
layout: none
---

<LauraAangifteSlide />

---
layout: none
---

<KIASlide />

---
layout: none
---

<CombinedPowerSlide />

---
layout: none
---

<NLvsBESlide />

---
layout: none
---

<TaxReportSlide />

---
layout: none
---

<APIRoutesSlide />

---
layout: none
---

<ProductionSlide />

---
layout: none
---

<SummarySlide />
