import { useState } from 'react'
import {
  Page, Layout, Card, Form, FormLayout, Select, TextField, Checkbox,
  Button, ButtonGroup, BlockStack, Banner, Text, Spinner, InlineStack
} from '@shopify/polaris'
import type { AangifteInput, TaxReport } from '../types/tax'
import { opentax } from '../api/opentax'
import Report from './Report'

const RICHARD_PRESET: AangifteInput = {
  fiscalYear: 2026,
  grossIncome: 800,
  hoursInBusiness: 50,
  investment: 300,
  isStarter: false,
  hasPartner: false,
}

const LAURA_PRESET: AangifteInput = {
  fiscalYear: 2026,
  grossIncome: 88000,
  hoursInBusiness: 1400,
  investment: 6500,
  isStarter: false,
  hasPartner: true,
}

type FormState = {
  fiscalYear: string
  grossIncome: string
  hoursInBusiness: string
  investment: string
  box3Assets: string
  isStarter: boolean
  hasPartner: boolean
}

function toFormState(input: AangifteInput): FormState {
  return {
    fiscalYear: String(input.fiscalYear),
    grossIncome: String(input.grossIncome),
    hoursInBusiness: input.hoursInBusiness != null ? String(input.hoursInBusiness) : '',
    investment: input.investment != null ? String(input.investment) : '',
    box3Assets: input.box3Assets != null ? String(input.box3Assets) : '',
    isStarter: input.isStarter,
    hasPartner: input.hasPartner,
  }
}

function toInput(form: FormState): AangifteInput {
  return {
    fiscalYear: parseInt(form.fiscalYear, 10),
    grossIncome: parseInt(form.grossIncome, 10) || 0,
    hoursInBusiness: form.hoursInBusiness ? parseInt(form.hoursInBusiness, 10) : undefined,
    investment: form.investment ? parseInt(form.investment, 10) : undefined,
    box3Assets: form.box3Assets ? parseInt(form.box3Assets, 10) : undefined,
    isStarter: form.isStarter,
    hasPartner: form.hasPartner,
  }
}

export default function Calculator() {
  const [form, setForm] = useState<FormState>(toFormState(RICHARD_PRESET))
  const [report, setReport] = useState<TaxReport | null>(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState<string | null>(null)

  const field = (key: keyof FormState) => (val: string | boolean) =>
    setForm(prev => ({ ...prev, [key]: val }))

  async function handleSubmit() {
    setLoading(true)
    setError(null)
    try {
      const result = await opentax.nlAangifte(toInput(form))
      setReport(result)
    } catch (e) {
      setError(e instanceof Error ? e.message : 'Onbekende fout')
    } finally {
      setLoading(false)
    }
  }

  const yearOptions = ['2022', '2023', '2024', '2025', '2026'].map(y => ({ label: y, value: y }))

  return (
    <Page title="Aangifte berekenen" subtitle="Inkomstenbelasting IB-aangifte voor ondernemers">
      <Layout>
        <Layout.Section>
          <Card>
            <Form onSubmit={handleSubmit}>
              <FormLayout>
                <InlineStack gap="200">
                  <ButtonGroup>
                    <Button onClick={() => setForm(toFormState(RICHARD_PRESET))}>
                      Laad Richard
                    </Button>
                    <Button onClick={() => setForm(toFormState(LAURA_PRESET))}>
                      Laad Laura
                    </Button>
                  </ButtonGroup>
                </InlineStack>

                <Select
                  label="Belastingjaar"
                  options={yearOptions}
                  value={form.fiscalYear}
                  onChange={field('fiscalYear')}
                />

                <FormLayout.Group>
                  <TextField
                    label="Bruto-inkomen (€)"
                    type="number"
                    value={form.grossIncome}
                    onChange={field('grossIncome')}
                    autoComplete="off"
                  />
                  <TextField
                    label="Uren in onderneming"
                    type="number"
                    value={form.hoursInBusiness}
                    onChange={field('hoursInBusiness')}
                    helpText="Urencriterium: 1225 uur"
                    autoComplete="off"
                  />
                </FormLayout.Group>

                <FormLayout.Group>
                  <TextField
                    label="Investering (€)"
                    type="number"
                    value={form.investment}
                    onChange={field('investment')}
                    helpText="KIA van toepassing bij €2.901–€398.236"
                    autoComplete="off"
                  />
                  <TextField
                    label="Box 3 vermogen (€)"
                    type="number"
                    value={form.box3Assets}
                    onChange={field('box3Assets')}
                    autoComplete="off"
                  />
                </FormLayout.Group>

                <FormLayout.Group>
                  <Checkbox
                    label="Starter (eerste 3 jaar)"
                    checked={form.isStarter}
                    onChange={field('isStarter')}
                    helpText="+€2.123 startersaftrek"
                  />
                  <Checkbox
                    label="Fiscale partner"
                    checked={form.hasPartner}
                    onChange={field('hasPartner')}
                  />
                </FormLayout.Group>

                <Button submit variant="primary" loading={loading}>
                  Bereken aangifte
                </Button>
              </FormLayout>
            </Form>
          </Card>
        </Layout.Section>

        <Layout.Section>
          {error && (
            <Banner tone="critical" title="Fout bij berekening">
              <Text as="p" variant="bodyMd">{error}</Text>
            </Banner>
          )}
          {loading && (
            <BlockStack gap="400" align="center">
              <Spinner accessibilityLabel="Berekening bezig" />
            </BlockStack>
          )}
          {report && !loading && <Report report={report} />}
        </Layout.Section>
      </Layout>
    </Page>
  )
}
