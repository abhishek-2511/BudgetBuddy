import React from 'react'
import Charts from './Charts'


const DashBoard = () => {
  return (
    <div className='dashboard-container'>
      <div className='dashboard-heading'> 
        <h2>DashBoard</h2>
      </div>
      <div className='dashboard-content'>
        <div className='chart-content'>
          <Charts />
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

export default DashBoard