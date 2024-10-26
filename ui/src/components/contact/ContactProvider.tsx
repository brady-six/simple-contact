import type { ContactListEntity } from '@/lib/model'
import { getContacts } from '@/lib/data'
import React, { useEffect, useMemo, useState } from 'react'
import { ContactContext, type ContactContextType } from './context'

export default function ContactProvider({ children }: React.PropsWithChildren) {
  const [contacts, setContacts] = useState<ContactListEntity>()
  const [page, setPage] = useState(0)
  const [size, setSize] = useState(10)

  useEffect(() => {
    getContacts({ page, size }).then(data => setContacts(data))
  }, [page, size])

  const value = useMemo<ContactContextType>(() => {
    return {
      contacts,
      setContacts,
      page,
      setPage,
      size,
      setSize,
    }
  }, [contacts, page, size])

  return (
    <ContactContext.Provider value={value}>
      {children}
    </ContactContext.Provider>
  )
}
