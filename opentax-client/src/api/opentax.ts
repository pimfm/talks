import type {
  AangifteInput,
  TaxReport,
  TarievenReport,
  ComparisonReport,
  SchijfTarief
} from '@/types/tax'

const BASE = import.meta.env.VITE_API_BASE ?? ''

async function post<T>(path: string, body: unknown): Promise<T> {
  const res = await fetch(`${BASE}${path}`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(body)
  })
  if (!res.ok) throw new Error(`${path} failed: ${res.status}`)
  return res.json() as Promise<T>
}

async function get<T>(path: string): Promise<T> {
  const res = await fetch(`${BASE}${path}`)
  if (!res.ok) throw new Error(`${path} failed: ${res.status}`)
  return res.json() as Promise<T>
}

export const opentax = {
  nlAangifte(input: AangifteInput): Promise<TaxReport> {
    return post<TaxReport>('/nl/aangifte', input)
  },

  nlTarieven(year: number): Promise<TarievenReport> {
    return get<TarievenReport>(`/nl/tarieven/${year}`)
  },

  nlSchijven(year: number): Promise<SchijfTarief[]> {
    return get<SchijfTarief[]>(`/nl/schijven/${year}`)
  },

  beAangifte(input: AangifteInput): Promise<ComparisonReport> {
    return post<ComparisonReport>('/be/aangifte', input)
  }
}
