import { StrictMode} from 'react'
import { createRoot } from 'react-dom/client'
// import './index.css'
import { HeaderComponent } from './components/headerComponent'
import { BodyComponent } from './components/BodyComponent'

createRoot(document.getElementById('main')).render(
  <StrictMode>
    <HeaderComponent />
    <BodyComponent></BodyComponent>
  </StrictMode>,
)
