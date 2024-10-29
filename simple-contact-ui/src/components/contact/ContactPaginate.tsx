import arrowBack from '../../assets/arrow_back.svg'
import arrowForward from '../../assets/arrow_forward.svg'
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
    <div className="flex flex-col items-center my-5">

      <p>
        {pageData?.page.number + 1}
        {' '}
        of
        {' '}
        {pageData?.page.totalPages}
      </p>

      <div>
        <button type="button" onClick={toPreviousPage}>
          <img src={arrowBack} alt="" />
        </button>

        <button type="button" onClick={toNextPage}>
          <img src={arrowForward} alt="" />
        </button>
      </div>

    </div>
  )
}
