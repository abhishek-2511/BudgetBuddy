import React from 'react'
import Cards from './Cards'

const Report = ({cardData}) => {
  return (
    <div className='report-container'>
        {cardData.map((value,index)=>{
          return(
            <Cards value={value}/>
          )
        })}
    </div>
  )
}

export default Report