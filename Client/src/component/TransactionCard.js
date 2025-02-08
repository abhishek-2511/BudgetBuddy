import React from 'react'


const TransactionCard = ({cardData}) => {
    const rupee = <i className="fa-solid fa-indian-rupee-sign" style={{fontSize:"1rem",paddingTop:"0.3rem"}}></i>
  return (
    <div className='transactionCard-container'>
        {cardData.map((value,index)=>{
          return(
            <div key={index} className='transaction-card'>
                <div>{value.title}</div>
                <div className='amount'>{rupee} {value.amount}</div>
            </div>
          )
        })}
    </div>
  )
}

export default TransactionCard