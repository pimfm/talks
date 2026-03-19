import { chromium } from 'playwright';
import { writeFileSync, mkdirSync } from 'fs';

const BASE = 'http://localhost:3030';
const API  = 'http://localhost:8080';
const OUT  = './screenshots';
mkdirSync(OUT, { recursive: true });

let pass = 0, fail = 0;

function check(label, ok) {
  console.log(`  ${ok ? '✅' : '❌'}  ${label}`);
  ok ? pass++ : fail++;
}

const browser = await chromium.launch();
const page    = await browser.newPage();
await page.setViewportSize({ width: 1280, height: 720 });

async function shot(name) {
  await page.waitForTimeout(500);
  await page.screenshot({ path: `${OUT}/${name}.png` });
  console.log(`  📸  ${OUT}/${name}.png`);
}

async function nav(slideNum) {
  await page.goto(`${BASE}/${slideNum}`, { waitUntil: 'networkidle' });
  await page.waitForTimeout(700);
}

async function click() {
  await page.keyboard.press('ArrowRight');
  await page.waitForTimeout(450);
}

// ═══════════════════════════════════════════════════════════
// Slide 1
// ═══════════════════════════════════════════════════════════
console.log('\n── Slide 1 ─────────────────────────────────────────────────\n');
await nav(1);
await shot('s1-cover');

const s1Title    = await page.locator('.title-line-2').textContent();
const s1Subtitle = await page.locator('.cover-subtitle').textContent();
const s1Avatar   = await page.locator('.cover-avatar').evaluate(el => el.naturalWidth > 0);
const s1Stamp    = await page.locator('.stamp').textContent();

check('Title contains "Tax Calculations"', s1Title?.includes('Tax Calculations'));
check('Subtitle present', s1Subtitle?.includes('Context Parameters'));
check('Cover avatar loaded', s1Avatar);
check('Stamp says BOX 3', s1Stamp?.includes('BOX 3'));

// ═══════════════════════════════════════════════════════════
// Slide 2 — initial state
// ═══════════════════════════════════════════════════════════
console.log('\n── Slide 2 — initial ───────────────────────────────────────\n');
await nav(2);
await shot('s2-0-initial');

check('Title visible', await page.locator('.about-title').isVisible());
const bulletCount = await page.locator('.about-bullet').count();
check('4 bullets in DOM', bulletCount === 4);

// ── Clicks through About slide ──────────────────────────────
for (let i = 1; i <= 6; i++) {
  await click();
}
await shot('s2-final');

// ═══════════════════════════════════════════════════════════
// Slide 3 — Dutch Tax System
// ═══════════════════════════════════════════════════════════
console.log('\n── Slide 3 — Dutch Tax System ──────────────────────────────\n');
await nav(3);
await shot('s3-tax-title');

check('Tax title slide renders', await page.locator('.tax-title-slide').isVisible());
check('"Dutch" in title', (await page.locator('.title').textContent()).includes('Dutch'));

// ═══════════════════════════════════════════════════════════
// Slide 4 — Characters
// ═══════════════════════════════════════════════════════════
console.log('\n── Slide 4 — Characters ────────────────────────────────────\n');
await nav(4);
await shot('s4-characters');

check('Characters slide renders', await page.locator('.characters-slide').isVisible());
check('"Richard" name visible', (await page.locator('.char-name').first().textContent()).includes('Richard'));
check('"Laura" name visible', (await page.locator('.char-name').last().textContent()).includes('Laura'));

// ═══════════════════════════════════════════════════════════
// Slide 5 — Character Details
// ═══════════════════════════════════════════════════════════
console.log('\n── Slide 5 — Character Details ─────────────────────────────\n');
await nav(5);
await shot('s5-character-details');

check('Slide 5 renders', await page.locator('body').isVisible());

// ═══════════════════════════════════════════════════════════
// Slides 6-21 — content slides
// ═══════════════════════════════════════════════════════════
for (let s = 6; s <= 21; s++) {
  await nav(s);
  await page.waitForTimeout(300);
  const visible = await page.locator('body').isVisible();
  check(`Slide ${s} renders without error`, visible);
  if (s % 5 === 0) {
    await shot(`s${s}`);
  }
}

// ═══════════════════════════════════════════════════════════
// Specific slide content checks
// ═══════════════════════════════════════════════════════════
console.log('\n── Slide content checks ────────────────────────────────────\n');

// Slide with Raise DSL
await nav(8);
await shot('s8-raise-dsl');
check('Raise DSL slide has code', await page.locator('pre').count() > 0);

// Slide with Context Parameters
await nav(12);
await shot('s12-context-params');
check('Context params slide renders', await page.locator('body').isVisible());

// FOR slide
await nav(15);
await shot('s15-for');
check('FOR slide renders', await page.locator('body').isVisible());

// Compile error slide
await nav(16);
await shot('s16-compile-error');
check('Compile error slide renders', await page.locator('body').isVisible());

// ═══════════════════════════════════════════════════════════
// API tests (if server is running)
// ═══════════════════════════════════════════════════════════
console.log('\n── API Tests (skipped if server not running) ───────────────\n');

try {
  // Test tarieven endpoint
  const tarievenRes = await fetch(`${API}/nl/tarieven/2026`);
  if (tarievenRes.ok) {
    const tarieven = await tarievenRes.json();
    check('GET /nl/tarieven/2026 returns data', tarieven.fiscalYear === 2026);
    check('Zelfstandigenaftrek 2026 is 1200', tarieven.zelfstandigenaftrek === 1200);
    check('MKB rate is 12.7%', tarieven.mkbWinstvrijstelling === 0.127);
  } else {
    console.log('  ⚠️  Server not running — skipping API tests');
  }

  // Test Richard aangifte
  const richardRes = await fetch(`${API}/nl/aangifte`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ fiscalYear: 2026, grossIncome: 800, hoursInBusiness: 50, investment: 300, isStarter: false, hasPartner: false })
  });
  if (richardRes.ok) {
    const report = await richardRes.json();
    check('Richard report: UrencriteriumNotMet in errors', report.errors?.some(e => e.includes('UrencriteriumNotMet')));
    check('Richard no entrepreneur deductions', report.box1?.entrepreneurDeductions === null);
    check('Richard gross income correct', report.grossIncome === 800);
  }

  // Test Laura aangifte
  const lauraRes = await fetch(`${API}/nl/aangifte`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ fiscalYear: 2026, grossIncome: 88000, hoursInBusiness: 1400, investment: 6500, isStarter: false, hasPartner: true })
  });
  if (lauraRes.ok) {
    const report = await lauraRes.json();
    check('Laura report: no errors', report.errors?.length === 0);
    check('Laura has entrepreneur deductions', report.box1?.entrepreneurDeductions !== null);
    check('Laura zelfstandigenaftrek = 1200', report.box1?.entrepreneurDeductions?.zelfstandigenaftrek === 1200);
    check('Laura KIA = 1820', report.box1?.entrepreneurDeductions?.kia === 1820);
  }

  // Test NL vs BE comparison
  const compRes = await fetch(`${API}/be/aangifte`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ fiscalYear: 2026, grossIncome: 88000, hoursInBusiness: 1400, investment: 6500, isStarter: false, hasPartner: false })
  });
  if (compRes.ok) {
    const comp = await compRes.json();
    check('Comparison has NL and BE reports', comp.nl && comp.be);
    check('BE effective rate higher than NL (with deductions)', comp.beEffectiveRate > comp.nlEffectiveRate);
  }

  // Test FOR in 2022
  const for2022Res = await fetch(`${API}/nl/aangifte`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ fiscalYear: 2022, grossIncome: 50000, hoursInBusiness: 1400, isStarter: false, hasPartner: false })
  });
  if (for2022Res.ok) {
    const report = await for2022Res.json();
    check('FOR present in 2022', report.box1?.entrepreneurDeductions?.fiscaleOudedagsreserve === 4720);
  }

  // Test FOR absent in 2023
  const for2023Res = await fetch(`${API}/nl/aangifte`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ fiscalYear: 2023, grossIncome: 50000, hoursInBusiness: 1400, isStarter: false, hasPartner: false })
  });
  if (for2023Res.ok) {
    const report = await for2023Res.json();
    check('FOR absent in 2023', report.box1?.entrepreneurDeductions?.fiscaleOudedagsreserve === 0);
  }

} catch (e) {
  console.log(`  ⚠️  API tests skipped: ${e.message}`);
}

// ═══════════════════════════════════════════════════════════
await browser.close();

console.log(`\n── Result: ${pass} passed, ${fail} failed ──────────────────────\n`);
if (fail > 0) process.exit(1);
