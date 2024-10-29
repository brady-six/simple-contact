import type { ContactCreate } from './model'
import React, { useState } from 'react'
import { createContact, fetchContactPage } from '../../data'
import { useContact } from './context'

interface ContactCreateFormProps {
  visible: boolean
  close: () => void
}

export default function ContactCreateForm({ visible, close }: ContactCreateFormProps) {
  const initialFormState: ContactCreate = {
    firstName: '',
    lastName: '',
    phoneNumber: '',
    profilePicture: '',
    address: {
      street: '',
      city: '',
      state: '',
      zipcode: '',
      country: '',
    },
  }

  const [contact, setContact] = useState<ContactCreate>(initialFormState)

  const { pageData, setPageData } = useContact()

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target
    if (name in contact) {
      setContact({ ...contact, [name]: value })
    }
    else {
      setContact({
        ...contact,
        address: { ...contact.address, [name]: value },
      })
    }
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    await createContact(contact)
    const json = await fetchContactPage(pageData._links.self.href)
    setPageData(json)
  }

  const handleCancel = () => {
    setContact(initialFormState)
    close()
  }

  return visible && (
    <form onSubmit={handleSubmit} className="max-w-lg mx-auto p-6 bg-white rounded-lg shadow-md absolute">
      <h2 className="text-2xl font-semibold mb-4">Create Contact</h2>

      <div className="mb-4">
        <label className="block text-gray-700">First Name</label>
        <input
          type="text"
          name="firstName"
          value={contact.firstName}
          onChange={handleChange}
          className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-200"
          required
        />
      </div>

      <div className="mb-4">
        <label className="block text-gray-700">Last Name</label>
        <input
          type="text"
          name="lastName"
          value={contact.lastName}
          onChange={handleChange}
          className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-200"
          required
        />
      </div>

      <div className="mb-4">
        <label className="block text-gray-700">Phone Number</label>
        <input
          type="tel"
          name="phoneNumber"
          value={contact.phoneNumber}
          onChange={handleChange}
          className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-200"
          required
        />
      </div>

      <div className="mb-4">
        <label className="block text-gray-700">Profile Picture URL</label>
        <input
          type="url"
          name="profilePicture"
          value={contact.profilePicture}
          onChange={handleChange}
          className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-200"
          required
        />
      </div>

      <h3 className="text-lg font-semibold mt-6 mb-2">Address</h3>

      <div className="mb-4">
        <label className="block text-gray-700">Street</label>
        <input
          type="text"
          name="street"
          value={contact.address.street}
          onChange={handleChange}
          className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-200"
          required
        />
      </div>

      <div className="mb-4">
        <label className="block text-gray-700">City</label>
        <input
          type="text"
          name="city"
          value={contact.address.city}
          onChange={handleChange}
          className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-200"
          required
        />
      </div>

      <div className="mb-4">
        <label className="block text-gray-700">State</label>
        <input
          type="text"
          name="state"
          value={contact.address.state}
          onChange={handleChange}
          className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-200"
          required
        />
      </div>

      <div className="mb-4">
        <label className="block text-gray-700">Zipcode</label>
        <input
          type="text"
          name="zipcode"
          value={contact.address.zipcode}
          onChange={handleChange}
          className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-200"
          required
        />
      </div>

      <div className="mb-4">
        <label className="block text-gray-700">Country</label>
        <input
          type="text"
          name="country"
          value={contact.address.country}
          onChange={handleChange}
          className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-200"
          required
        />
      </div>

      <div className="flex justify-between mt-4">
        <button
          type="button"
          onClick={handleCancel}
          className="py-2 px-4 bg-gray-300 text-gray-800 rounded-md shadow hover:bg-gray-400 focus:outline-none"
        >
          Cancel
        </button>
        <button
          type="submit"
          className="py-2 px-4 bg-blue-500 text-white rounded-md shadow hover:bg-blue-600 focus:outline-none focus:ring focus:ring-blue-200"
        >
          Create Contact
        </button>
      </div>
    </form>
  )
}
