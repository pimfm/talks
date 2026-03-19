import type { AangifteInput, TaxReport, TarievenReport } from '../types/tax'

const BASE = 'http://localhost:8080'

async function post<T>(path: string, body: unknown): Promise<T> {
  const res = await fetch(`${BASE}${path}`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(body),
  })
  if (!res.ok) throw new Error(`${res.status} ${await res.text()}`)
  return res.json() as Promise<T>
}

async function get<T>(path: string): Promise<T> {
  const res = await fetch(`${BASE}${path}`)
  if (!res.ok) throw new Error(`${res.status} ${await res.text()}`)
  return res.json() as Promise<T>
}

export const opentax = {
  nlAangifte: (input: AangifteInput) => post<TaxReport>('/nl/aangifte', input),
  tarieven: (year: number) => get<TarievenReport>(`/nl/tarieven/${year}`),
  schijven: (year: number) => get<unknown>(`/nl/schijven/${year}`),
}
