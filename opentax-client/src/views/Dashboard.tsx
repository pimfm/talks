import { Page, Layout, Card, Text, Badge, Button, BlockStack, InlineStack, Divider } from '@shopify/polaris'

type View = 'dashboard' | 'calculator' | 'tarieven'

interface Props {
  onNavigate: (view: View) => void
}

export default function Dashboard({ onNavigate }: Props) {
  return (
    <Page
      title="OpenTax"
      subtitle="Type-Safe Tax Calculations — Kotlin + Arrow Raise DSL"
    >
      <Layout>
        <Layout.Section>
          <Text as="p" variant="bodyMd" tone="subdued">
            Bereken Nederlandse inkomstenbelasting voor ondernemers. Alle berekeningen zijn type-safe gebouwd met Kotlin context parameters en Arrow Raise.
          </Text>
        </Layout.Section>

        <Layout.Section variant="oneHalf">
          <Card>
            <BlockStack gap="400">
              <InlineStack gap="200" align="start">
                <Text as="h2" variant="headingMd">Richard</Text>
                <Badge tone="warning">Part-time DJ</Badge>
              </InlineStack>
              <Text as="p" variant="bodyMd" tone="subdued">
                50 uur per jaar als DJ — ruim onder het urencriterium van 1225 uur. Geen ondernemersaftrek, geen zelfstandigenaftrek.
              </Text>
              <Divider />
              <BlockStack gap="200">
                <InlineStack align="space-between">
                  <Text as="span" variant="bodySm" tone="subdued">Bruto-inkomen</Text>
                  <Text as="span" variant="bodyMd">€ 800</Text>
                </InlineStack>
                <InlineStack align="space-between">
                  <Text as="span" variant="bodySm" tone="subdued">Uren in onderneming</Text>
                  <Text as="span" variant="bodyMd">50 uur</Text>
                </InlineStack>
                <InlineStack align="space-between">
                  <Text as="span" variant="bodySm" tone="subdued">Investering</Text>
                  <Text as="span" variant="bodyMd">€ 300</Text>
                </InlineStack>
                <InlineStack align="space-between">
                  <Text as="span" variant="bodySm" tone="subdued">Starter</Text>
                  <Text as="span" variant="bodyMd">Nee</Text>
                </InlineStack>
              </BlockStack>
              <Button onClick={() => onNavigate('calculator')}>Bereken aangifte Richard</Button>
            </BlockStack>
          </Card>
        </Layout.Section>

        <Layout.Section variant="oneHalf">
          <Card>
            <BlockStack gap="400">
              <InlineStack gap="200" align="start">
                <Text as="h2" variant="headingMd">Laura</Text>
                <Badge tone="success">Full-time ondernemer</Badge>
              </InlineStack>
              <Text as="p" variant="bodyMd" tone="subdued">
                1400 uur per jaar als freelance developer — boven het urencriterium. Recht op zelfstandigenaftrek, KIA en MKB-winstvrijstelling.
              </Text>
              <Divider />
              <BlockStack gap="200">
                <InlineStack align="space-between">
                  <Text as="span" variant="bodySm" tone="subdued">Bruto-inkomen</Text>
                  <Text as="span" variant="bodyMd">€ 88.000</Text>
                </InlineStack>
                <InlineStack align="space-between">
                  <Text as="span" variant="bodySm" tone="subdued">Uren in onderneming</Text>
                  <Text as="span" variant="bodyMd">1.400 uur</Text>
                </InlineStack>
                <InlineStack align="space-between">
                  <Text as="span" variant="bodySm" tone="subdued">Investering</Text>
                  <Text as="span" variant="bodyMd">€ 6.500</Text>
                </InlineStack>
                <InlineStack align="space-between">
                  <Text as="span" variant="bodySm" tone="subdued">Partner</Text>
                  <Text as="span" variant="bodyMd">Ja</Text>
                </InlineStack>
              </BlockStack>
              <Button onClick={() => onNavigate('calculator')}>Bereken aangifte Laura</Button>
            </BlockStack>
          </Card>
        </Layout.Section>

        <Layout.Section>
          <Card>
            <BlockStack gap="400">
              <Text as="h2" variant="headingMd">Over dit project</Text>
              <Text as="p" variant="bodyMd">
                Dit project demonstreert type-safe belastingberekeningen met Kotlin context parameters (experimenteel) en Arrow Raise DSL. De Fiscale Oudedagsreserve (FOR) bestaat ALLEEN voor boekjaar 2022 — het aanroepen met een ander jaar is een compile-fout.
              </Text>
              <InlineStack gap="200">
                <Button variant="plain" onClick={() => onNavigate('tarieven')}>Bekijk tarieven 2022–2026</Button>
                <Button variant="plain" onClick={() => onNavigate('calculator')}>Open calculator</Button>
              </InlineStack>
            </BlockStack>
          </Card>
        </Layout.Section>
      </Layout>
    </Page>
  )
}
