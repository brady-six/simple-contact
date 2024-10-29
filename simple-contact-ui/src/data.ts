import type { ContactCreate, ContactEntity, ContactPage } from './components/contact/model'

export async function fetchContactPage(uri: string) {
  const response = await fetch(uri)
  const json = await response.json()
  return json as ContactPage
}

export async function deleteContact(contact: ContactEntity) {
  await fetch(contact._links.self.href, { method: 'DELETE' })
}

export async function createContact(contact: ContactCreate) {
  const headers = new Headers()
  headers.append('Content-Type', 'application/json')
  const response = await fetch('/api/contacts', { method: 'POST', body: JSON.stringify(contact), headers })
  const newContact = await response.json() as ContactEntity
  return newContact
}
