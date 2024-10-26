export interface ContactEntity {
  id: string
  firstName: string
  lastName: string
  phoneNumber: string
  address: {
    street: string
    city: string
    state: string
    zipcode: string
    country: string
  }
  profilePicture: string
  _links: {
    self: {
      href: string
    }
    contacts: {
      href: string
    }
  }
}

export interface ContactListEntity {
  _embedded: {
    contactList: ContactEntity[]
  }
  _links: {
    self: {
      href: string
    }
  }
}
