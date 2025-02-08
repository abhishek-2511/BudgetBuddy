import React from 'react'
import { Delete, Transactions } from "../utils/icons";
import axios from 'axios';

const Report = ({cardData,setCardId}) => {

  const rupee = <i className="fa-solid fa-indian-rupee-sign" style={{fontSize:"1rem",paddingTop:"0.3rem"}}></i>

  const handleDelete = async(id)=>{

    const response = await axios.delete(`http://localhost:8081/user/deleteExpenseOrIncomeById/${id}`);
    setCardId(id);
  }


  return (
    <div className='report-container'>
        {cardData.map((value,index)=>{
          return(
            <div className="card" key={index}>
                  <div className="card-logo">{Transactions}</div>
                  <div className="card-content">
                    <div>
                      <span>{value.title}</span>
                    </div>
                    <div className="card-details">
                      <div className="amount">
                        <span>{rupee} {value.amount}</span>
                      </div>
                      <div className="date">
                        {Transactions}
                        <span>{value.date}</span>
                      </div>
                      <div className="card-income">
                        {Transactions}
                        <span>{value.description}</span>
                      </div>
                    </div>
                  </div>
                  <div className="delete" onClick={()=>handleDelete(value.id)}>
                    {Delete}
                  </div>
                </div>
          )
        })}
    </div>
  )
}

export default Report