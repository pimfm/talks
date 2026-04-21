---
theme: default
title: "Type-Safe Tax Calculations (België)"
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

  <div class="stamp">PERSONENBELASTING<br/>COMPILING...</div>

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

<!--
Hello everyone, thank you for being here.

* Hello
* Thank you
* Kotlin community
-->

---
layout: none
clicks: 6
---

<AboutSlide :clicks="$clicks" />

<!--
In case we haven't met: I'm Pim, software engineer for about 10 years, and Kotlin developer for about 6 years.

I work at Flock, one of today's sponsors.
-->

---
layout: none
clicks: 1
---

<TaxSystemTransitionSlide :clicks="$clicks" />

---
layout: none
clicks: 1
---

<BEAgendaSlide :clicks="$clicks" />

<!--
Sociale bijdragen are Belgium's social contributions for the self-employed — 20.5% of net professional income. For bijberoep (secondary occupation), there's a threshold: below €1,922/year, nothing is owed. We explore 2 types of self-employed and how this rule applies to them.

Then we have the gewone investeringsaftrek, which applied a flat 8% rate on qualifying investments. But there's a twist: the rule was abolished from fiscal year 2024 and replaced by a new system with higher rates.
-->

---
layout: none
---

<BEForfaitIntroSlide />

---
layout: none
---

<BECharactersSlide />

---
layout: none
clicks: 10
---

<script setup>
const beStates = [
  {
    focused: 'richard',
    code: `val inkomen = 800.euro    // bijberoep income
val bijdragen = berekenSocialeBijdragen(
    nettoInkomen = 800,
    status = Bijberoep
)

// bijdragen // ???`,
  },
  {
    focused: 'laura',
    code: `val bijdragen = berekenSocialeBijdragen(
    nettoInkomen = 88_000,
    status = Hoofdberoep
)
// bijdragen = € 18,040 (20.5%)`,
  },
  {
    focused: 'both',
    code: `fun berekenSocialeBijdragen(
    nettoInkomen: Long,
    status: BeroepsStatus
): Long {
    if (status is Bijberoep && nettoInkomen < 1_922) {
        return 0L // below threshold
    }
    return (nettoInkomen * 0.205).toLong()
}`,
  },
  {
    focused: 'richard',
    code: `fun berekenSocialeBijdragen(
    nettoInkomen: Long,
    status: BeroepsStatus
): Long {
    if (status is Bijberoep && nettoInkomen < 1_922) {
        throw BijdragenNietVerschuldigd(nettoInkomen)
    }
    return (nettoInkomen * 0.205).toLong()
}`,
  },
  {
    focused: 'richard',
    code: `val bijdragen = try {
    berekenSocialeBijdragen(
        nettoInkomen = 800,
        status = Bijberoep
    )
} catch (e: BijdragenNietVerschuldigd) {
    info("Below bijberoep threshold.")
    0L
}`,
  },
  {
    focused: 'both',
    code: `fun berekenSocialeBijdragen(
    nettoInkomen: Long,
    status: BeroepsStatus
): Long {
    // ...
}`,
  },
  {
    focused: 'both',
    code: `fun berekenSocialeBijdragen(
    nettoInkomen: Long,
    status: BeroepsStatus
): Long {
    // ...
}`,
    quote: 'Make illegal states unrepresentable',
    quoteAuthor: 'Yaron Minsky',
  },
  {
    focused: 'both',
    code: `fun berekenSocialeBijdragen(
    nettoInkomen: Long,
    status: BeroepsStatus
): Either<BijdragenNietVerschuldigd, Long> {
    // ...
}`,
    quote: 'Make illegal states unrepresentable',
    quoteAuthor: 'Yaron Minsky',
  },
  {
    focused: 'both',
    code: `context(raise: Raise<BijdragenNietVerschuldigd>, year: FiscalYear)
fun berekenSocialeBijdragen(
    nettoInkomen: Long,
    status: BeroepsStatus
): Long {
    if (status is Bijberoep) {
        ensure(nettoInkomen >= BIJBEROEP_DREMPEL) {
            BijdragenNietVerschuldigd(nettoInkomen)
        }
    }
    return (nettoInkomen * 0.205).toLong()
}`,
  },
  {
    focused: 'richard',
    code: `val bijdragen = recover({
    berekenSocialeBijdragen(
        nettoInkomen = 800,
        status = Bijberoep
    )
}) { _: BijdragenNietVerschuldigd ->
    info("Below bijberoep threshold.")
    0L
}`,
  },
  {
    focused: 'both',
    code: `fun berekenSocialeBijdragen(
    nettoInkomen: Long,
    status: BeroepsStatus
): Long | BijdragenNietVerschuldigd {
    // ...
}`,
    note: 'Coming to Kotlin 2.4 as a Beta!',
  },
]
</script>

<CharacterCodeSlide :clicks="$clicks" :states="beStates" />

<!--
Richard works as a DJ as bijberoep (secondary occupation). This year he only earned €800. Since he's bijberoep, the threshold applies: €1,922/year. Below that, no social contributions are owed.

[click]

Laura is a full-time marketing consultant (hoofdberoep). No threshold for hoofdberoep. €88,000 × 20.5% = €18,040 in social contributions.

[click]

There's an implicit business rule here. If bijberoep income is below €1,922, we return 0 — no contributions owed.

[click]

We can make this explicit by throwing an exception. But note — this error is not visible in the function signature.

[click]

And here's how that looks from Richard's perspective — a try/catch that falls back to 0 when the exception is thrown.
-->

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

<BEInvesteringsaftrekIntroSlide />

---
layout: none
clicks: 8
---

<script setup>
const invStates = [
  {
    focused: 'laura',
    code: `val investering = 6_500.euro
val aftrek = gewoneInvesteringsaftrek(
    investering = investering
)

// aftrek // € 520`,
  },
  {
    focused: 'laura',
    code: `context(year: FiscalYear)
fun gewoneInvesteringsaftrek(
    investering: Long
): Long {
    return (investering * 0.08).toLong()
}`,
  },
  {
    focused: 'laura',
    code: `fun gewoneInvesteringsaftrek(
    year: FiscalYear,
    investering: Long
): Long {
    val rate = when (year) {
        is FY2022 -> 0.08
        is FY2023 -> 0.08
        // FY2024+ ???
    }
    return (investering * rate).toLong()
}`,
  },
  {
    focused: 'laura',
    code: `context(year: FiscalYear)
fun gewoneInvesteringsaftrek(
    investering: Long
): Long {
    val rate = when (year) {
        is FY2022 -> 0.08
        is FY2023 -> 0.08
    }
    return (investering * rate).toLong()
}`,
  },
  {
    focused: 'laura',
    code: `val investering = 6_500.euro

with(FY2023) {
    gewoneInvesteringsaftrek(investering)
    // € 520 (8%)
}

with(FY2024) {
    gewoneInvesteringsaftrek(investering)
    // € 520 (8%)
}`,
  },
  {
    focused: 'laura',
    code: `// Gewone aftrek afgeschaft vanaf FJ2024.
context(year: FY2023)
fun gewoneInvesteringsaftrek(
    investering: Long
): Long {
    return (investering * 0.08).toLong()
}`,
  },
  {
    focused: 'laura',
    code: `val investering = 6_500.euro

with(FY2024) {
    gewoneInvesteringsaftrek(investering)
    // Compile error: FY2023
    // context not found
}`,
    errorLines: [4],
  },
  {
    focused: 'laura',
    code: `sealed interface TaxSystem
data object NL : TaxSystem
data object BE : TaxSystem

context(year: FY2023, system: BE)
fun gewoneInvesteringsaftrek(
    investering: Long
): Long { ... }

context(year: VerhoogdeInvesteringsaftrekJaar, system: BE)
fun verhoogdeInvesteringsaftrek(
    investering: Long,
    type: InvesteringsType
): Long { ... }`,
  },
  {
    focused: 'laura',
    code: `val investering = 6_500.euro

with(FY2023) { with(BE) {
    gewoneInvesteringsaftrek(investering)
    // € 520 (BE: 8%)
} }

with(FY2024) { with(BE) {
    verhoogdeInvesteringsaftrek(investering, DIGITALISERING)
    // € 2.600 (BE: 40% digitalisering)
} }

with(FY2024) { with(NL) {
    kia(investering)
    // € 1.820 (NL: 28%)
} }`,
  },
]
</script>

<CharacterCodeSlide :clicks="$clicks" :states="invStates" />

<!--
Laura's investment in 2023: €6,500. The gewone investeringsaftrek gives her €520 — 8% of €6,500.

[click]

Here's the basic implementation. The Belgian rate was flat: 8%. Simple enough.

[click]

But tax rules change. How do we handle the fact that the gewone aftrek is abolished?

[click]

Context parameters solve this. Declare the fiscal year once at the top — it flows through the entire call chain without threading.

[click]

Same year, same result. But there's no compile error yet for FY2024.

[click]

Here's the key insight. The gewone investeringsaftrek is abolished from FY2024. By changing the context type from FiscalYear to the specific FY2023 type, we encode the law in the type system.

[click]

Calling the deduction in FY2024 is now a compile error. The function literally doesn't exist for that year.

[click]

We add the new system as a separate function. The verhoogde investeringsaftrek only exists for VerhoogdeInvesteringsaftrekJaar (FY2024+).

[click]

With FY2023 and BE in scope: €520 (8%). With FY2024 and BE: €2,600 (40% digitalisation) — better than the Dutch KIA of €1,820!
-->

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

<BESummarySlide />

---
layout: none
---

<ThankYouSlide />

<!--
Thank you! Happy to take your questions.
-->

---
layout: none
clicks: 2
---

<ConclusionSlide :clicks="$clicks" />

<!--
[click]

With this type safety, I hope we can one day open-source the Belgian tax system — making calculations programmatically available and verifiable by anyone.

[click]

And for you — I hope concepts like Arrow Raise DSL, Context Parameters, and Rich Errors help you enrich the types in your own projects, and let the compiler handle more flows.
-->

---
layout: none
---

<BEForfaitIntroSlide />

---
layout: none
---

<BEInvesteringsaftrekIntroSlide />

---
layout: none
---

<BEInvesteringsaftrekSlide />

---
layout: none
---

<BELauraAangifteSlide />

---
layout: none
---

<NLvsBESlide />
