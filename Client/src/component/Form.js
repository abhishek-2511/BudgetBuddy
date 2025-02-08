import React from 'react'

const Form = ({Data,setData}) => {

  const handleChange =  (e)=>{
    setData({...Data,[e.target.name]:e.target.value});
  }
  return (
    <div className='form-container'>
        <input 
        type='text'
        placeholder='Title'
        name='title'
        onChange={handleChange}
        value={Data.title}
        required
        />
        <input 
        type='number'
        placeholder='Amount'
        name='amount'
        onChange={handleChange}
        value={Data.amount}
        required
        />
        <input 
        type='date'
        placeholder='Enter a date'
        name='date'
        onChange={handleChange}
        value={Data.date}
        required
        />
        {/* <select className='income' onChange={handleChange} name='category' value={Data.category}>
            <option value={"select option"} >Select Option</option>
            <option value={"Salary"} >Salary</option>
            <option value={"Freelancing"}>Freelancing</option>
            <option value={"Investments"}>Investments</option>
            <option value={"Stocks"}>Stocks</option>
            <option value={"Bitcoin"}>Bitcoin</option>
            <option value={"Bank Transfer"}>Bank Transfer</option>
            <option value={"You Tube"}>You Tube</option>
            <option value={"Other"}>Other</option>
        </select> */}
        <input className='income' onChange={handleChange} name='category' value={Data.category} placeholder='Caterory' />
        <textarea
        style={{ width: "275px", height: "50px",  resize: "none", paddingLeft: "0.3rem"}} 
        type=''
        placeholder='Description'
        name='description'
        onChange={handleChange}
        value={Data.description}
        required
        />
    </div>
  )
}

export default Form