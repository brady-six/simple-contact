import { render, screen } from '@testing-library/react'
import { describe, expect, it } from 'vitest'
import App from './App'

describe('app', () => {
  render(<App />)

  it('should render the app', () => {
    const elem = screen.getByText(/Simple Contact/)
    expect(elem).toBeDefined()
  })
})
