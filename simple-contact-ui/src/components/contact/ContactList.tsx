import ContactCard from './ContactCard'
import { useContact } from './context'

export default function ContactList() {
  const { pageData } = useContact()

  return (
    <ul className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
      {
        pageData._embedded?.contactList.map(c => <ContactCard key={c.id} contact={c} />)
      }
    </ul>
  )
}
