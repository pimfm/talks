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
Hallo iedereen, bedankt dat jullie er zijn.

* Hallo
* Bedankt
* Kotlin community
-->

---
layout: none
clicks: 6
---

<AboutSlide :clicks="$clicks" />

<!--
Als we elkaar nog niet kennen: ik ben Pim, software engineer voor zo'n 10 jaar, en Kotlin developer voor zo'n 6 jaar.

Ik werk bij Flock, een van de sponsors van vandaag.
-->

---
layout: none
---

<TaxTitleSlide />

---
layout: none
clicks: 1
---

<BEAgendaSlide :clicks="$clicks" />

<!--
De forfaitaire beroepskosten zijn een forfaitaire aftrek voor Belgische zelfstandigen — tot 30% van het netto beroepsinkomen, geplafonneerd op €5.870. Maar deze aftrek is alleen van toepassing als je voldoet aan bepaalde vereisten. We verkennen 2 types zelfstandigen en hoe deze regel op hen van toepassing is.

Dan hebben we de gewone investeringsaftrek, die een vlak tarief van 8% toepaste op kwalificerende investeringen. Maar er is een twist: de regel werd afgeschaft vanaf boekjaar 2024 en vervangen door een nieuw stelsel met hogere tarieven.
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
    code: `val brutoInkomen = 800.euro
val forfait = forfaitaireBeroepskosten(
    nettoInkomen = 800,
    kboGeregistreerd = false
)

forfait // ???`,
  },
  {
    focused: 'laura',
    code: `val brutoInkomen = 88_000.euro
val bijdragen = berekenSocialeBijdragen(88_000)
// bijdragen = 18.040

val forfait = forfaitaireBeroepskosten(
    nettoInkomen = 88_000 - bijdragen,
    kboGeregistreerd = true
)

forfait // € 5.870 (cap bereikt)`,
  },
  {
    focused: 'both',
    code: `fun forfaitaireBeroepskosten(
    nettoInkomen: Long,
    kboGeregistreerd: Boolean
): Long {
    if (!kboGeregistreerd) {
        return 0L // geen aftrek
    }

    return minOf(
        (nettoInkomen * 0.30).toLong(),
        5870L
    )
}`,
  },
  {
    focused: 'richard',
    code: `fun forfaitaireBeroepskosten(
    nettoInkomen: Long,
    kboGeregistreerd: Boolean
): Long {
    if (!kboGeregistreerd) {
        throw GeenKBORegistratie(nettoInkomen)
    }

    return minOf(
        (nettoInkomen * 0.30).toLong(),
        5870L
    )
}`,
  },
  {
    focused: 'richard',
    code: `val forfait = try {
    forfaitaireBeroepskosten(
        nettoInkomen = 800,
        kboGeregistreerd = false
    )
} catch (e: GeenKBORegistratie) {
    info("Niet ingeschreven als zelfstandige.")
    0L
}

forfait // 0`,
  },
  {
    focused: 'both',
    code: `fun forfaitaireBeroepskosten(
    nettoInkomen: Long,
    kboGeregistreerd: Boolean
): Long {
    // ...
}`,
  },
  {
    focused: 'both',
    code: `fun forfaitaireBeroepskosten(
    nettoInkomen: Long,
    kboGeregistreerd: Boolean
): Long {
    // ...
}`,
    quote: 'Make illegal states unrepresentable',
    quoteAuthor: 'Yaron Minsky',
  },
  {
    focused: 'both',
    code: `fun forfaitaireBeroepskosten(
    nettoInkomen: Long,
    kboGeregistreerd: Boolean
): Either<GeenKBORegistratie, Long> {
    // ...
}`,
    quote: 'Make illegal states unrepresentable',
    quoteAuthor: 'Yaron Minsky',
  },
  {
    focused: 'both',
    code: `context(raise: Raise<GeenKBORegistratie>, year: FiscalYear)
fun forfaitaireBeroepskosten(
    nettoInkomen: Long,
    kboGeregistreerd: Boolean
): Long {
    if (!kboGeregistreerd) {
        raise(GeenKBORegistratie(nettoInkomen))
    }

    return minOf(
        (nettoInkomen * 0.30).toLong(),
        forfaitaireBeroepskostenCap()
    )
}`,
  },
  {
    focused: 'richard',
    code: `val forfait = recover({
    forfaitaireBeroepskosten(
        nettoInkomen = 800,
        kboGeregistreerd = false
    )
}) { _: GeenKBORegistratie ->
    info("Niet ingeschreven als zelfstandige.")
    0L
}`,
  },
  {
    focused: 'both',
    code: `fun forfaitaireBeroepskosten(
  nettoInkomen: Long,
  kboGeregistreerd: Boolean
): Long | GeenKBORegistratie {
    // ...
}`,
    note: 'Coming to Kotlin 2.4 as a Beta!',
  },
]
</script>

<CharacterCodeSlide :clicks="$clicks" :states="beStates" />

<!--
Richard werkt als DJ als bijberoep. Dit jaar verdiende hij maar €800. Hij is niet ingeschreven als zelfstandige bij een sociaal verzekeringsfonds — geen KBO-registratie.

[click]

Laura is een full-time marketing consultant als hoofdberoep. Haar bruto-inkomen is €88.000. Ze betaalt eerst sociale bijdragen: 20,5% = €18.040. Na aftrek van de sociale bijdragen: €69.960. De forfaitaire beroepskosten: min(30% × €69.960, €5.870) = €5.870.

[click]

Er zit hier een impliciete businessregel. Als de KBO-registratie niet aanwezig is, retourneren we 0 — geen aftrek.

[click]

We kunnen dit expliciet maken door een exceptie te gooien. Maar let op — deze fout is niet zichtbaar in de functiesignatuur.

[click]

En hier zie je hoe dat eruitziet vanuit Richard's perspectief — een try/catch die terugvalt op 0 als de exceptie wordt gegoooid.
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
Laura's investering in 2023: €6.500. De gewone investeringsaftrek geeft haar €520 — 8% van €6.500.

[click]

Hier is de basisimplementatie. Het Belgische tarief was vlak: 8%. Eenvoudig genoeg.

[click]

Maar belastingregels veranderen. Hoe gaan we om met het feit dat de gewone aftrek afgeschaft wordt?

[click]

Contextparameters lossen dit op. Declareer het boekjaar eenmalig bovenaan — het vloeit door de hele aanroepketen zonder threading.

[click]

Nu geeft hetzelfde boekjaar hetzelfde resultaat. Maar er is nog geen compilatiefout bij FY2024.

[click]

Hier is het sleutelinzicht. De gewone investeringsaftrek is afgeschaft vanaf FJ2024. Door het contexttype te veranderen van FiscalYear naar het specifieke FY2023-type, coderen we de wet in het typesysteem.

[click]

De aftrek aanroepen in FJ2024 is nu een compilatiefout. De functie bestaat letterlijk niet voor dat jaar.

[click]

We voegen het nieuw stelsel toe als een aparte functie. De verhoogde investeringsaftrek bestaat alleen voor VerhoogdeInvesteringsaftrekJaar (FY2024+).

[click]

Met FY2023 en BE in scope: €520 (8%). Met FY2024 en BE: €2.600 (40% digitalisering) — beter dan de Nederlandse KIA van €1.820!
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
Bedankt! Ik beantwoord graag jullie vragen.
-->

---
layout: none
clicks: 2
---

<ConclusionSlide :clicks="$clicks" />

<!--
[click]

Met deze type-veiligheid hoop ik dat we ooit het Belgische belastingstelsel open-source kunnen maken — zodat berekeningen programmatisch beschikbaar zijn en door iedereen gecontroleerd kunnen worden.

[click]

En voor jullie — ik hoop dat concepten zoals Arrow Raise DSL, Contextparameters en Rich Errors helpen om de types in jullie projecten rijker te maken, en meer flows door de compiler te laten afhandelen.
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
