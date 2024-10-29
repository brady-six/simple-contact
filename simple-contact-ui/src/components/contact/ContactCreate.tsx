import { useState } from 'react'
import ContactCreateForm from './ContactCreateForm'

export default function ContactCreate() {
  const [isFormVisible, setFormVisible] = useState(false)
  return (
    <div className="flex justify-center my-5">
      <button
        onClick={() => setFormVisible(true)}
        type="button"
        className="bg-green-500 p-2 font-mono text-white rounded-md"
      >
        Create
      </button>
      <ContactCreateForm visible={isFormVisible} close={() => setFormVisible(false)} />
    </div>
  )
}
