import { chromium } from 'playwright';
import { writeFileSync, mkdirSync } from 'fs';

const BASE = 'http://localhost:3030';
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
check('No brand card on entry', await page.locator('.brand-slot').count() === 0);
const bulletCount = await page.locator('.about-bullet').count();
check('4 bullets in DOM', bulletCount === 4);
const visibleOnEntry = await page.locator('.about-bullet.slidev-vclick-shown').count();
check('No bullets active before first click', visibleOnEntry === 0);

// ── Click 1: Flock ──────────────────────────────────────────
console.log('\n── Click 1: Flock ──────────────────────────────────────────\n');
await click();
await shot('s2-1-flock');

check('Flock circle visible', await page.locator('.flock-bg').isVisible());
check('Fastned circle absent', await page.locator('.fastned-bg').count() === 0);
check('Chair absent', await page.locator('.chair-card').count() === 0);

// ── Click 2: Fastned ─────────────────────────────────────────
console.log('\n── Click 2: Fastned ────────────────────────────────────────\n');
await click();
await shot('s2-2-fastned');

check('Fastned circle visible', await page.locator('.fastned-bg').isVisible());
check('Flock circle gone', await page.locator('.flock-bg').count() === 0);
check('Chair absent', await page.locator('.chair-card').count() === 0);

// ── Click 3: Chair ───────────────────────────────────────────
console.log('\n── Click 3: Chair ──────────────────────────────────────────\n');
await click();
await shot('s2-3-chair');

check('Circle visible (chair)', await page.locator('.circle-wrap').isVisible());
const chairImgLoaded = await page.locator('.circle-img').first().evaluate(el => el.naturalWidth > 0);
check('Chair image loaded', chairImgLoaded);
check('Fastned circle gone', await page.locator('.fastned-bg').count() === 0);

// ── Click 4: Boardgames image 1 ───────────────────────────────
console.log('\n── Click 4: Boardgames ─────────────────────────────────────\n');
await click();
await shot('s2-4-boardgames-1');
check('Circle wrap visible', await page.locator('.circle-wrap').isVisible());
check('All 4 bullets active', await page.locator('.about-bullet.bullet-active').count() === 4);

// ── Click 5: Boardgames image 2 ───────────────────────────────
await click();
await shot('s2-5-boardgames-2');
check('Circle still visible after click 5', await page.locator('.circle-wrap').isVisible());

// ── Click 6: Boardgames image 3 (chess) ──────────────────────
await click();
await shot('s2-6-boardgames-chess');
check('Circle still visible after click 6', await page.locator('.circle-wrap').isVisible());

// ═══════════════════════════════════════════════════════════
// Slide 3 — Dutch Tax System
// ═══════════════════════════════════════════════════════════
console.log('\n── Slide 3 — Dutch Tax System ──────────────────────────────\n');
await nav(3);
await shot('s3-tax-title');

check('Tax title slide renders', await page.locator('.tax-title-slide').isVisible());
check('"The Dutch" in title', (await page.locator('.title').textContent()).includes('Dutch'));
const taxLogoLoaded = await page.locator('.tax-logo').evaluate(el => el.naturalWidth > 0);
check('Tax logo loaded', taxLogoLoaded);

// ═══════════════════════════════════════════════════════════
// Slide 4 — Characters
// ═══════════════════════════════════════════════════════════
console.log('\n── Slide 4 — Characters ────────────────────────────────────\n');
await nav(4);
await shot('s4-characters');

check('Characters slide renders', await page.locator('.characters-slide').isVisible());
check('DJ card present', await page.locator('.card-dj').isVisible());
check('Teacher card present', await page.locator('.card-teacher').isVisible());
check('"Richard" name visible', (await page.locator('.char-name').first().textContent()).includes('Richard'));
check('"Laura" name visible', (await page.locator('.char-name').last().textContent()).includes('Laura'));
check('Stat pips rendered', await page.locator('.pip').count() >= 10);

// ═══════════════════════════════════════════════════════════
await browser.close();

console.log(`\n── Result: ${pass} passed, ${fail} failed ──────────────────────\n`);
if (fail > 0) process.exit(1);
