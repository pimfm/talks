export interface AangifteInput {
  fiscalYear: number
  grossIncome: number
  hoursInBusiness?: number
  investment?: number
  isStarter: boolean
  hasPartner: boolean
  box3Assets?: number
}

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

export interface TarievenReport {
  fiscalYear: number
  box1Schijven: Array<{ upTo: number | null; rate: number; label: string }>
  zelfstandigenaftrek: number
  startersaftrek: number
  mkbWinstvrijstelling: number
  box3FictitiousRate: number
}
