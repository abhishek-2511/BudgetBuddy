import React from 'react'

const ViewTransactions = () => {
  return (
    <div className='transactions-container'>
      <div className='transactions-heading'> 
        <h2>All transactions</h2>
      </div>
      <div className='transactions-content'>
        <div>
          <h3>All trnsactions</h3>
        </div>
        <div >
          <div className='total-card'>
            <h3>Total Income</h3>
            <p>$ 5500</p>
          </div >
          <div className='total-card'>
            <h3>Total Expense</h3>
            <p>$ 300</p>
          </div>
          <div className='total-card'>
            <h3>Total Balance</h3>
            <p>$ 5200</p>
          </div>
        </div>
      </div>
    </div>
  )
}

export default ViewTransactions