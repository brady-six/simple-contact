import type { ContactListEntity } from './model'

interface GetContactsOptions {
  page: number
  size: number
}

export async function getContacts(options: GetContactsOptions) {
  const url = `/api/contacts?page=${options.page}&size=${options.size}`

  const res = await fetch(url)

  const data = await res.json()

  return data satisfies ContactListEntity
}
