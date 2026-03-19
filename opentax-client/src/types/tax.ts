// Mirror of Kotlin API types — fully typed

export interface SchijfApplication {
  bracket: string
  rate: number
  income: number
  tax: number
}

export interface EntrepreneurDeductions {
  zelfstandigenaftrek: number
  startersaftrek: number
  mkbWinstvrijstelling: number
  kia: number
  fiscaleOudedagsreserve: number
  total: number
}

export interface Box1Report {
  grossIncome: number
  entrepreneurDeductions: EntrepreneurDeductions | null
  taxableIncome: number
  schijven: SchijfApplication[]
  totalTax: number
}

export interface Box3Report {
  assets: number
  fictitiousReturn: number
  taxableReturn: number
  tax: number
}

export interface TaxReport {
  fiscalYear: string
  grossIncome: number
  box1: Box1Report
  box3: Box3Report | null
  totalTax: number
  effectiveRate: number
  errors: string[]
}

export interface SchijfTarief {
  upTo: number | null
  rate: number
  description: string
}

export interface TarievenReport {
  fiscalYear: number
  box1Schijven: SchijfTarief[]
  zelfstandigenaftrek: number
  startersaftrek: number
  mkbWinstvrijstelling: number
  box3FictitiousRate: number
}

export interface BEReport {
  grossIncome: number
  taxableIncome: number
  schijven: SchijfApplication[]
  totalTax: number
  effectiveRate: number
}

export interface ComparisonReport {
  fiscalYear: string
  grossIncome: number
  nl: TaxReport
  be: BEReport
  difference: number
  nlEffectiveRate: number
  beEffectiveRate: number
}

export interface AangifteInput {
  fiscalYear: number
  grossIncome: number
  hoursInBusiness?: number
  investment?: number
  isStarter: boolean
  hasPartner: boolean
  partner?: { grossIncome: number }
  ownHome?: { eigenwoningforfait: number; hypotheekrenteaftrek: number }
  box3Assets?: number
}

export type FiscalYear = 2022 | 2023 | 2024 | 2025 | 2026
