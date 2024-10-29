export interface ContactListPageMeta {
  size: number
  totalElements: number
  totalPages: number
  number: number
}

export interface ContactListLinkMeta {
  self: {
    href: string
  }
  prev?: {
    href: string
  }
  next?: {
    href: string
  }
}

export interface ContactEntity {
  id: string
  firstName: string
  lastName: string
  phoneNumber: string
  profilePicture: string
  address: {
    street: string
    city: string
    state: string
    zipcode: string
    country: string
  }
  _links: {
    self: {
      href: string
    }
    collection: {
      href: string
    }
  }
}

export type ContactCreate = Omit<ContactEntity, 'id' | '_links'>

export interface ContactPage {
  _embedded?: {
    contactList: ContactEntity[]
  }
  _links: ContactListLinkMeta
  page: ContactListPageMeta
}
