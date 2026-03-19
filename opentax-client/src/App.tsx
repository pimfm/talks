import { useState } from 'react'
import { AppProvider, Frame, Navigation, Page, TopBar } from '@shopify/polaris'
import enTranslations from '@shopify/polaris/locales/en.json'
import { HomeIcon, CalculatorIcon, ChartVerticalIcon } from '@shopify/polaris-icons'
import Calculator from './views/Calculator'
import Tarieven from './views/Tarieven'
import Dashboard from './views/Dashboard'

type View = 'dashboard' | 'calculator' | 'tarieven'

export default function App() {
  const [view, setView] = useState<View>('dashboard')
  const [mobileNavActive, setMobileNavActive] = useState(false)

  const nav = (
    <Navigation location="/">
      <Navigation.Section items={[
        { label: 'Dashboard', icon: HomeIcon, onClick: () => setView('dashboard'), selected: view === 'dashboard' },
        { label: 'Aangifte', icon: CalculatorIcon, onClick: () => setView('calculator'), selected: view === 'calculator' },
        { label: 'Tarieven', icon: ChartVerticalIcon, onClick: () => setView('tarieven'), selected: view === 'tarieven' },
      ]} />
    </Navigation>
  )

  const topBar = (
    <TopBar
      showNavigationToggle
      onNavigationToggle={() => setMobileNavActive(!mobileNavActive)}
    />
  )

  return (
    <AppProvider i18n={enTranslations}>
      <Frame
        navigation={nav}
        topBar={topBar}
        showMobileNavigation={mobileNavActive}
        onNavigationDismiss={() => setMobileNavActive(false)}
      >
        {view === 'dashboard' && <Dashboard onNavigate={setView} />}
        {view === 'calculator' && <Calculator />}
        {view === 'tarieven' && <Tarieven />}
      </Frame>
    </AppProvider>
  )
}
