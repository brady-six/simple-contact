import { fetchContactPage } from '../../data'
import { useContact } from './context'

export default function ContactPaginate() {
  const { pageData, setPageData } = useContact()

  const toPreviousPage = async () => {
    const prev = await fetchContactPage(pageData._links.prev?.href || pageData._links.self.href)
    setPageData(prev)
  }

  const toNextPage = async () => {
    const next = await fetchContactPage(pageData._links.next?.href || pageData._links.self.href)
    setPageData(next)
  }
  return pageData.page.totalPages > 0 && (
    <div>

      <p>
        {pageData?.page.number + 1}
        {' '}
        of
        {' '}
        {pageData?.page.totalPages}
      </p>

      <button type="button" onClick={toPreviousPage}>
        <img src="./src/assets/arrow_back.svg" alt="" />
      </button>

      <button type="button" onClick={toNextPage}>
        <img src="./src/assets/arrow_forward.svg" alt="" />
      </button>

    </div>
  )
}
