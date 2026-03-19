import { useState, useEffect } from 'react'
import {
  Page, Layout, Card, Select, DataTable, Text, BlockStack, InlineStack, ProgressBar, Divider, Spinner, Banner
} from '@shopify/polaris'
import type { TarievenReport } from '../types/tax'
import { opentax } from '../api/opentax'

const YEARS = ['2022', '2023', '2024', '2025', '2026']
const YEAR_OPTIONS = YEARS.map(y => ({ label: y, value: y }))

// Zelfstandigenaftrek values per year (for the trend chart)
const ZELFSTANDIGEN_TREND: Record<string, number> = {
  '2022': 6310,
  '2023': 5030,
  '2024': 3750,
  '2025': 2470,
  '2026': 1200,
}

const MAX_ZELFSTANDIGEN = 6310

export default function Tarieven() {
  const [year, setYear] = useState('2026')
  const [report, setReport] = useState<TarievenReport | null>(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    setLoading(true)
    setError(null)
    opentax.tarieven(parseInt(year, 10))
      .then(setReport)
      .catch(e => setError(e instanceof Error ? e.message : 'Fout bij laden'))
      .finally(() => setLoading(false))
  }, [year])

  const pct = (r: number) => `${(r * 100).toFixed(2)}%`
  const fmt = (n: number) => `€ ${n.toLocaleString('nl-NL')}`

  const schijvenRows = report?.box1Schijven.map(s => [
    s.label ?? (s.upTo ? `≤ € ${s.upTo.toLocaleString('nl-NL')}` : 'Overig'),
    pct(s.rate),
  ]) ?? []

  return (
    <Page title="Tarieven" subtitle="Overzicht belastingtarieven 2022–2026">
      <Layout>
        <Layout.Section>
          <Select
            label="Belastingjaar"
            options={YEAR_OPTIONS}
            value={year}
            onChange={setYear}
          />
        </Layout.Section>

        {error && (
          <Layout.Section>
            <Banner tone="critical" title="Fout bij laden">
              <Text as="p" variant="bodyMd">{error}</Text>
            </Banner>
          </Layout.Section>
        )}

        {loading && (
          <Layout.Section>
            <BlockStack align="center" gap="400">
              <Spinner accessibilityLabel="Tarieven laden" />
            </BlockStack>
          </Layout.Section>
        )}

        {report && !loading && (
          <>
            <Layout.Section>
              <Card>
                <BlockStack gap="400">
                  <Text as="h2" variant="headingMd">Box 1 — Schijven {year}</Text>
                  <DataTable
                    columnContentTypes={['text', 'text']}
                    headings={['Schijf', 'Tarief']}
                    rows={schijvenRows}
                  />
                </BlockStack>
              </Card>
            </Layout.Section>

            <Layout.Section variant="oneHalf">
              <Card>
                <BlockStack gap="400">
                  <Text as="h2" variant="headingMd">Ondernemersaftrek {year}</Text>
                  <BlockStack gap="200">
                    <InlineStack align="space-between">
                      <Text as="span" variant="bodyMd">Zelfstandigenaftrek</Text>
                      <Text as="span" variant="bodyMd">{fmt(report.zelfstandigenaftrek)}</Text>
                    </InlineStack>
                    <InlineStack align="space-between">
                      <Text as="span" variant="bodyMd">Startersaftrek</Text>
                      <Text as="span" variant="bodyMd">{fmt(report.startersaftrek)}</Text>
                    </InlineStack>
                    <InlineStack align="space-between">
                      <Text as="span" variant="bodyMd">MKB-winstvrijstelling</Text>
                      <Text as="span" variant="bodyMd">{pct(report.mkbWinstvrijstelling)}</Text>
                    </InlineStack>
                    {report.box3FictitiousRate > 0 && (
                      <>
                        <Divider />
                        <InlineStack align="space-between">
                          <Text as="span" variant="bodyMd">Box 3 fictief rendement</Text>
                          <Text as="span" variant="bodyMd">{pct(report.box3FictitiousRate)}</Text>
                        </InlineStack>
                      </>
                    )}
                  </BlockStack>
                </BlockStack>
              </Card>
            </Layout.Section>

            <Layout.Section variant="oneHalf">
              <Card>
                <BlockStack gap="400">
                  <Text as="h2" variant="headingMd">Zelfstandigenaftrek 2022–2026</Text>
                  <Text as="p" variant="bodyMd" tone="subdued">
                    De aftrek daalt van €6.310 (2022) naar €1.200 (2026).
                  </Text>
                  <BlockStack gap="300">
                    {YEARS.map(y => (
                      <BlockStack gap="100" key={y}>
                        <InlineStack align="space-between">
                          <Text as="span" variant="bodySm" tone={y === year ? 'base' : 'subdued'}>{y}</Text>
                          <Text as="span" variant="bodySm" tone={y === year ? 'base' : 'subdued'}>
                            {fmt(ZELFSTANDIGEN_TREND[y])}
                          </Text>
                        </InlineStack>
                        <ProgressBar
                          progress={(ZELFSTANDIGEN_TREND[y] / MAX_ZELFSTANDIGEN) * 100}
                          tone={y === year ? 'highlight' : 'success'}
                          size="small"
                        />
                      </BlockStack>
                    ))}
                  </BlockStack>
                </BlockStack>
              </Card>
            </Layout.Section>
          </>
        )}
      </Layout>
    </Page>
  )
}
