import type { ContactEntity } from '@/lib/model'

interface ContactCardProps {
  contact: ContactEntity
}

export default function ContactCard({ contact }: ContactCardProps) {
  return (
    <div className="w-96 border-2 m-3 p-3 rounded-lg flex flex-col">

      <div className="self-end">
        <button type="button" className="focus:bg-gray-200 hover:bg-gray-200 duration-200 ease-in-out rounded-md">
          <img src="./src/assets/more_horiz.svg" />
        </button>
      </div>

      <div className="flex flex-col justify-center items-center border-b pb-5">
        <p className="text-2xl font-semibold py-3">
          {contact.firstName}
          {' '}
          {contact.lastName}
        </p>

        <img src={contact.profilePicture} alt="" className="rounded-full h-28" />
      </div>

      <div className="flex justify-between items-start py-2">
        <p className="font-mono text-lg">{contact.phoneNumber}</p>
        <img src="./src/assets/phone.svg" />
      </div>

      <div className="flex justify-between items-start py-2">

        <div>
          <p>{contact.address.street}</p>
          <p>{contact.address.city}</p>
          <p>{contact.address.state}</p>
          <p>{contact.address.zipcode}</p>
          <p>{contact.address.country}</p>
        </div>

        <img src="./src/assets/location.svg" />

      </div>

    </div>
  )
}
