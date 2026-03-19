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

<!--
Hello everyone, thank you all for joining me after this nice little break. It is always great to see so many people that enjoy Kotlin come together.

* Hello
* Thank you
* People that enjoy Kotlin
-->

---
layout: none
clicks: 6
---

<AboutSlide :clicks="$clicks" />

<!--
If we haven't met yet, my name is Pim, and I have been a software engineer for about 10 years now, and a Kotlin engineer for about 6.

I work at Flock, one of the sponsors of today's meetup. We are a software engineering community of about 20 engineers, and we consult for companies like Bol.com, DHL, Port of Rotterdam, or in my case..

Fastned. An EV charging company that is full of ambition, where I work within the team that programs subscriptions and discounts. Like 1 my team launched recently where you'll receive a 10% discount by using the app. You might recognize their charging locations by wooden pillars that hold this logo on top.

And last year, before Fastned and Flock, I used to run my own software company called Jeeves, where we catalogued all office furniture of huge corporate offices and build an office management system to plan and execute professional cleaning and repairs. Some client

And the best board game of them all, with the highest stakes...
-->

---
layout: none
---

<TaxTitleSlide />

<!--
The Tax authority is the final boss of board games, where the
-->

---
layout: none
clicks: 1
---

<AgendaSlide :clicks="$clicks" />

<!--
The zelfstandigenaftrek, or self-employed tax deduction, is an offer for entrepreneurs to deduct from their gross income, so that they pay a little less income tax. But, this deduction is only applicable if you meet certain requirements. We'll explore 2 different types of entrepreneurs and how this rule would apply to them and what this means for our code.

Then we have the FOR, the fiscale oudedagsreserve, or the fiscal reserve for old age. A way for entrepreneurs to build their own pension, but the rule has a bit of a twist. We'll look at what that twist could mean for our code and how context parameters can help us.

Then finally we look at a simplified version of the income tax, where we'll bring these ideas together to have a type-safe tax calculator, aware of its context.
-->

---
layout: none
---

<ZelfstandigenaftrekIntroSlide />

---
layout: none
---

<CharactersSlide />

---
layout: none
clicks: 10
---

<script setup>
const states = [
  {
    focused: 'richard',
    code: `val grossIncome = 800.euro
val netIncome = zelfstandigenAftrek(
    hours = 50,
    income = grossIncome
)

netIncome // € 800`,
  },
  {
    focused: 'laura',
    code: `val grossIncome = 88_000.euro
val netIncome = zelfstandigenAftrek(
    hours = 1_400,
    income = grossIncome
)

netIncome // € 86.800`,
  },
  {
    focused: 'both',
    code: `fun zelfstandigenAftrek(
    hours: Int,
    income: Money
): Money {
    if (hours < 1225) {
        return income
    }

    return income
        .minus(1200.euro)
        .coerceAtLeast(0.euro)
}`,
  },
  {
    focused: 'richard',
    code: `fun zelfstandigenAftrek(
    hours: Int,
    income: Money
): Money {
    if (hours < 1225) {
        throw NotEnoughHours(hours, 1225)
    }

    return income
        .minus(1200.euro)
        .coerceAtLeast(0.euro)
}`,
  },
  {
    focused: 'richard',
    code: `val grossIncome = 800.euro
val netIncome = try {
    zelfstandigenAftrek(
        hours = 50,
        income = grossIncome
    )
} catch (e: NotEnoughHours) {
    info("Not enough hours.")
    grossIncome
}

netIncome // € 800`,
  },
  {
    focused: 'both',
    code: `fun zelfstandigenAftrek(
    hours: Int,
    income: Money
): Money {
    // ...
}`,
  },
  {
    focused: 'both',
    code: `fun zelfstandigenAftrek(
    hours: Int,
    income: Money
): Money {
    // ...
}`,
    quote: 'Make illegal states unrepresentable',
    quoteAuthor: 'Yaron Minsky',
  },
  {
    focused: 'both',
    code: `fun zelfstandigenAftrek(
    hours: Int,
    income: Money
): Either<NotEnoughHours, Money> {
    // ...
}`,
    quote: 'Make illegal states unrepresentable',
    quoteAuthor: 'Yaron Minsky',
  },
  {
    focused: 'both',
    code: `context(raise: Raise<NotEnoughHours>)
fun zelfstandigenAftrek(
    hours: Int,
    income: Money
): Money {
    if (hours < 1225) {
        raise.raise(NotEnoughHours(hours, 1225))
    }

    return income
        .minus(1200.euro)
        .coerceAtLeast(0.euro)
}`,
  },
  {
    focused: 'richard',
    code: `val netIncome = recover({
    zelfstandigenAftrek(hours = 50, income = grossIncome)
}) { _: NotEnoughHours ->
    info("Not enough hours.")
    grossIncome
}`,
  },
  {
    focused: 'both',
    code: `fun zelfstandigenAftrek(
  hours: Int,
  grossIncome: Money
): Money | NotEnoughHours {
    // ...
}`,
    note: 'Coming to Kotlin 2.4 as a Beta!',
  },
]
</script>

<CharacterCodeSlide :clicks="$clicks" :states="states" />

<!--
If we view the tax deduction method as a black box for now, with Richard's company we can simply try out the function and see if they get a tax deduction. Given their 50 hours worked.

[click]

Now for Laura — she's a full-time marketeer working 1400 hours a year. Her gross income is €88.000, and because she meets the 1225 hour requirement, the deduction applies. Her net income comes out at €86.800.

[click]

There is a business rule here that is implicit. When the hour minimum is not matched, we return the current income — we do not apply the deduction.

[click]

We can make this explicit by throwing an exception instead. But notice — this error is not communicated by just looking at the function signature. There is nothing in the return type or parameters that tells you a NotEnoughHours can be thrown.

[click]

And here is what that looks like from Richard's perspective — a try/catch that falls back to the original income when the exception is thrown.
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

<FORIntroSlide />

---
layout: none
clicks: 8
---

<script setup>
const forStates = [
  {
    focused: 'laura',
    code: `val winst = 88_000.euro
val reserve = fiscaleOudedagsreserve(
    winst = winst
)

// reserve // € 8.307`,
  },
  {
    focused: 'laura',
    code: `fun fiscaleOudedagsreserve(
    winst: Money
): Money {
    return minOf(
        winst * 0.0944,
        9_632.euro
    )
}`,
  },
  {
    focused: 'laura',
    code: `fun fiscaleOudedagsreserve(
    year: FiscalYear,
    winst: Money
): Money {
    val max = when (year) {
        is FY2021 -> 9_395.euro
        is FY2022 -> 9_632.euro
    }
    return minOf(winst * 0.0944, max)
}`,
  },
  {
    focused: 'laura',
    code: `context(year: FiscalYear)
fun fiscaleOudedagsreserve(
    winst: Money
): Money {
    val max = when (year) {
        is FY2021 -> 9_395.euro
        is FY2022 -> 9_632.euro
    }
    return minOf(winst * 0.0944, max)
}`,
  },
  {
    focused: 'laura',
    code: `val winst = 88_000.euro

with(FY2021) {
    fiscaleOudedagsreserve(winst)
    // € 8.307 (max: € 9.395)
}

with(FY2022) {
    fiscaleOudedagsreserve(winst)
    // € 8.307 (max: € 9.632)
}`,
  },
  {
    focused: 'laura',
    code: `// FOR was abolished from 2023.
context(year: FY2022)
fun fiscaleOudedagsreserve(
    winst: Money
): Money {
    return minOf(
        winst * 0.0944,
        9_632.euro
    )
}`,
  },
  {
    focused: 'laura',
    code: `val winst = 88_000.euro

with(FY2023) {
    fiscaleOudedagsreserve(winst)
    // Compile error: FY2022
    // context not found
}`,
  },
  {
    focused: 'laura',
    code: `sealed interface TaxSystem
data object NL : TaxSystem
data object BE : TaxSystem

context(year: FY2022, system: NL)
fun fiscaleOudedagsreserve(
    winst: Money
): Money { ... }`,
  },
  {
    focused: 'laura',
    code: `val winst = 88_000.euro

with(FY2022) { with(NL) {
    fiscaleOudedagsreserve(winst)
    // € 8.307 (NL: 9.44%, max € 9.632)
} }

with(FY2022) { with(BE) {
    fiscaleOudedagsreserve(winst)
    // Compile error: NL context not found
    // BE has no FOR equivalent
} }`,
  },
]
</script>

<CharacterCodeSlide :clicks="$clicks" :states="forStates" />

<!--
Laura's winst for 2022 is €88.000. The FOR lets her reserve €8.307 — the full 9.44% of her profit.

[click]

Here is the basic implementation. The Dutch rate is 9.44%, capped at €9.632. Simple enough.

[click]

But tax rules change every year. The maximum FOR amount was different in 2021. Threading the year as an explicit parameter works, but now every calling function needs to pass it along too.

[click]

Context parameters solve this. Declare the fiscal year once at the top — it flows through the entire call graph without threading. The year is available everywhere in scope, automatically.

[click]

Now swapping the fiscal year in the context changes the calculation values. With FY2021, the max is €9.395. With FY2022, it is €9.632. Same function, different context — different result.

[click]

Here is the key insight. The FOR was abolished from 2023. By changing the context type from FiscalYear to the specific FY2022 type, we encode the law directly in the type system.

[click]

Calling the FOR function in FY2023 is now a compile error. The function literally does not exist for that year. The type system is the law.

[click]

We can add the country tax system as a second context parameter. For now it is always NL — but the type is sealed, ready for BE or others.

[click]

With FY2022 and NL in scope, Laura's FOR compiles and returns €8.307. Switch to BE and it is a compile error — Belgium has no FOR equivalent, so no function exists for that context.
-->

---
layout: none
---

<ThankYouSlide />

<!--
Thank you! Happy to take any questions.
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

---
layout: none
---

<BonusEuroSlide />
