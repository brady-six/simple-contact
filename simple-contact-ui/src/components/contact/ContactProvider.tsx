import type React from 'react'
import type { ContactContextType } from './context'
import type { ContactPage } from './model'
import { useEffect, useMemo, useState } from 'react'
import { fetchContactPage } from '../../data'
import { ContactContext } from './context'

export default function ContactProvider({ children }: React.PropsWithChildren) {
  const [pageData, setPageData] = useState<ContactPage>({
    _links: {
      self: {
        href: '/api/contacts',
      },
    },
    page: {
      number: 0,
      size: 0,
      totalElements: 0,
      totalPages: 0,
    },
  })

  useEffect(() => {
    const fetchContacts = async () => {
      const json = await fetchContactPage('/api/contacts')
      setPageData(json)
    }
    fetchContacts()
  }, [])

  const value = useMemo<ContactContextType>(() => ({
    pageData,
    setPageData,
  }), [pageData])

  return (
    <ContactContext.Provider value={value}>
      {children}
    </ContactContext.Provider>
  )
}
