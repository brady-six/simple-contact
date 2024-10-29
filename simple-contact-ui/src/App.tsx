import ContactCreate from './components/contact/ContactCreate'
import ContactList from './components/contact/ContactList'
import ContactPaginate from './components/contact/ContactPaginate'
import ContactProvider from './components/contact/ContactProvider'

export default function App() {
  return (
    <ContactProvider>
      <ContactPaginate />
      <ContactCreate />
      <ContactList />
    </ContactProvider>
  )
}
