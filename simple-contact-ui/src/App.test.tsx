import { render } from '@testing-library/react'
import { describe, expect, it } from 'vitest'
import App from './App'

describe('<App />', () => {
  render(<App />)
  it('should be true', () => {
    expect(1).toBe(1)
  })
})
