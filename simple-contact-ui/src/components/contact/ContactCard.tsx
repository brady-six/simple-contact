import type { ContactEntity } from './model'
import { deleteContact, fetchContactPage } from '../../data'
import { useContact } from './context'

interface ContactCardProps {
  contact: ContactEntity
}

export default function ContactCard({ contact }: ContactCardProps) {
  const { pageData, setPageData } = useContact()

  const handleDelete = async () => {
    const lastItem = pageData._embedded?.contactList.length === 1
    await deleteContact(contact)
    const json = await fetchContactPage(lastItem ? (pageData._links.prev?.href || '/api/contacts') : pageData._links.self.href)
    setPageData(json)
  }

  return (
    <div className="border flex flex-col rounded-lg p-3 gap-3">

      <div className="self-end">
        <button
          onClick={handleDelete}
          type="button"
          className="border rounded-md"
        >
          <img src="./src/assets/delete.svg" alt="" />
        </button>
      </div>

      <div className="border-b flex flex-col items-center">
        <img src={contact.profilePicture} alt="" />
        <p className="font-semibold text-2xl">
          {contact.firstName}
          {' '}
          {contact.lastName}
        </p>
      </div>

      <p className="text-lg font-mono flex justify-between items-start">
        {contact.phoneNumber}
        <img src="./src/assets/phone.svg" alt="" />
      </p>

      <div>
        <p className="flex justify-between items-start">
          {contact.address.street}
          <img src="./src/assets/location.svg" alt="" />
        </p>

        <p className="font-light">
          {contact.address.city}
          {' '}
          {contact.address.state}
          {' '}
          {contact.address.zipcode}
          {' '}
          {contact.address.country}
        </p>
      </div>
    </div>
  )
}
