import ContactCard from './ContactCard'
import { useContact } from './context'

export default function ContactList() {
  const { contacts } = useContact()

  return (
    <ul>
      {
        contacts?._embedded.contactList.map(c => (
          <li key={c.id}>
            <ContactCard contact={c} />
          </li>
        ))
      }
    </ul>
  )
}
