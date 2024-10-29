import type React from 'react'
import type { ContactPage } from './model'
import { createContext, type SetStateAction, useContext } from 'react'

export interface ContactContextType {
  pageData: ContactPage
  setPageData: React.Dispatch<SetStateAction<ContactPage>>
}

export const ContactContext = createContext<ContactContextType | undefined>(undefined)

export function useContact() {
  const ctx = useContext(ContactContext)
  if (!ctx)
    throw new Error('This must be used in a ContactProvider')
  return ctx
}
