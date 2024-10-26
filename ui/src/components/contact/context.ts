import type { ContactListEntity } from '@/lib/model'
import { createContext, useContext } from 'react'

export const ContactContext = createContext<ContactContextType | undefined>(undefined)

export interface ContactContextType {
  contacts: ContactListEntity | undefined
  setContacts: React.Dispatch<React.SetStateAction<ContactListEntity | undefined>>
  page: number
  setPage: React.Dispatch<React.SetStateAction<number>>
  size: number
  setSize: React.Dispatch<React.SetStateAction<number>>
}

export function useContact() {
  const ctx = useContext(ContactContext)
  if (!ctx)
    throw new Error('This must be used within a ContactProvider')
  return ctx
}
