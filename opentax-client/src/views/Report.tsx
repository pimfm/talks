import { Card, DataTable, Text, Badge, Banner, BlockStack, InlineStack, ProgressBar, Divider } from '@shopify/polaris'
import type { TaxReport } from '../types/tax'

interface Props {
  report: TaxReport
}

export default function Report({ report }: Props) {
  const fmt = (n: number) => `€ ${n.toLocaleString('nl-NL')}`
  const pct = (r: number) => `${(r * 100).toFixed(1)}%`

  const schijvenRows = report.box1.schijven.map(s => [
    s.bracket,
    pct(s.rate),
    fmt(s.income),
    fmt(s.tax),
  ])

  const deductions = report.box1.entrepreneurDeductions

  return (
    <BlockStack gap="400">
      {report.errors.map((err, i) => (
        <Banner key={i} tone="warning" title="Melding">
          <Text as="p" variant="bodyMd">{err}</Text>
        </Banner>
      ))}

      <Card>
        <BlockStack gap="400">
          <InlineStack align="space-between">
            <Text as="h2" variant="headingMd">Aangifte {report.fiscalYear}</Text>
            <Badge tone={report.effectiveRate < 0.30 ? 'success' : report.effectiveRate < 0.40 ? 'attention' : 'critical'}>
              {`Effectief tarief: ${pct(report.effectiveRate)}`}
            </Badge>
          </InlineStack>
          <BlockStack gap="200">
            <InlineStack align="space-between">
              <Text as="span" variant="bodySm" tone="subdued">Bruto-inkomen</Text>
              <Text as="span" variant="bodyMd">{fmt(report.grossIncome)}</Text>
            </InlineStack>
            <InlineStack align="space-between">
              <Text as="span" variant="bodySm" tone="subdued">Belastbaar inkomen box 1</Text>
              <Text as="span" variant="bodyMd">{fmt(report.box1.taxableIncome)}</Text>
            </InlineStack>
            <InlineStack align="space-between">
              <Text as="span" variant="bodySm" tone="subdued">Totale belasting</Text>
              <Text as="span" variant="headingSm">{fmt(report.totalTax)}</Text>
            </InlineStack>
          </BlockStack>
          <ProgressBar
            progress={Math.min(report.effectiveRate * 100, 100)}
            tone={report.effectiveRate < 0.30 ? 'success' : report.effectiveRate < 0.40 ? 'highlight' : 'critical'}
          />
        </BlockStack>
      </Card>

      {deductions && (
        <Card>
          <BlockStack gap="400">
            <Text as="h2" variant="headingMd">Ondernemersaftrek</Text>
            <BlockStack gap="200">
              <InlineStack align="space-between">
                <Text as="span" variant="bodyMd">Zelfstandigenaftrek</Text>
                <Text as="span" variant="bodyMd">{fmt(deductions.zelfstandigenaftrek)}</Text>
              </InlineStack>
              {deductions.startersaftrek > 0 && (
                <InlineStack align="space-between">
                  <Text as="span" variant="bodyMd">Startersaftrek</Text>
                  <Text as="span" variant="bodyMd">{fmt(deductions.startersaftrek)}</Text>
                </InlineStack>
              )}
              {deductions.kia > 0 && (
                <InlineStack align="space-between">
                  <Text as="span" variant="bodyMd">KIA (Kleinschaligheidsinvesteringsaftrek)</Text>
                  <Text as="span" variant="bodyMd">{fmt(deductions.kia)}</Text>
                </InlineStack>
              )}
              {deductions.fiscaleOudedagsreserve > 0 && (
                <InlineStack align="space-between">
                  <Text as="span" variant="bodyMd">Fiscale Oudedagsreserve (FOR)</Text>
                  <Text as="span" variant="bodyMd">{fmt(deductions.fiscaleOudedagsreserve)}</Text>
                </InlineStack>
              )}
              <InlineStack align="space-between">
                <Text as="span" variant="bodyMd">MKB-winstvrijstelling</Text>
                <Text as="span" variant="bodyMd">{fmt(deductions.mkbWinstvrijstelling)}</Text>
              </InlineStack>
              <Divider />
              <InlineStack align="space-between">
                <Text as="span" variant="headingSm">Totaal</Text>
                <Text as="span" variant="headingSm">{fmt(deductions.total)}</Text>
              </InlineStack>
            </BlockStack>
          </BlockStack>
        </Card>
      )}

      <Card>
        <BlockStack gap="400">
          <Text as="h2" variant="headingMd">Box 1 — Schijven</Text>
          <DataTable
            columnContentTypes={['text', 'text', 'numeric', 'numeric']}
            headings={['Schijf', 'Tarief', 'Inkomen in schijf', 'Belasting']}
            rows={schijvenRows}
            totalsName={{ singular: 'Totaal', plural: 'Totaal' }}
          />
        </BlockStack>
      </Card>

      {report.box3 && (
        <Card>
          <BlockStack gap="400">
            <Text as="h2" variant="headingMd">Box 3 — Vermogensbelasting</Text>
            <BlockStack gap="200">
              <InlineStack align="space-between">
                <Text as="span" variant="bodyMd">Vermogen</Text>
                <Text as="span" variant="bodyMd">{fmt(report.box3.assets)}</Text>
              </InlineStack>
              <InlineStack align="space-between">
                <Text as="span" variant="bodyMd">Fictief rendement</Text>
                <Text as="span" variant="bodyMd">{fmt(report.box3.fictitiousReturn)}</Text>
              </InlineStack>
              <InlineStack align="space-between">
                <Text as="span" variant="bodyMd">Belastbaar rendement</Text>
                <Text as="span" variant="bodyMd">{fmt(report.box3.taxableReturn)}</Text>
              </InlineStack>
              <Divider />
              <InlineStack align="space-between">
                <Text as="span" variant="headingSm">Belasting box 3</Text>
                <Text as="span" variant="headingSm">{fmt(report.box3.tax)}</Text>
              </InlineStack>
            </BlockStack>
          </BlockStack>
        </Card>
      )}
    </BlockStack>
  )
}
