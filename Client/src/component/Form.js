import React from 'react'

const Form = () => {
  return (
    <div className='form-container'>
        <input 
        type='text'
        placeholder='Salary title'
        name='salaryTitle'
        required
        />
        <input 
        type='text'
        placeholder='Salary Amount'
        name='salaryTitle'
        required
        />
        <input 
        type='date'
        placeholder='Enter a date'
        name='Date'
        required
        />
        <select className='income'>
            <option value={"select option"}>Select Option</option>
            <option value={"select option"}>Salary</option>
            <option value={"select option"}>Freelancing</option>
            <option value={"select option"}>Investments</option>
            <option value={"select option"}>Stocks</option>
            <option value={"select option"}>Bitcoin</option>
            <option value={"select option"}>Bank Transfer</option>
            <option value={"select option"}>You Tube</option>
            <option value={"select option"}>Other</option>
        </select>
        <textarea
        type=''
        placeholder='Salary title'
        name='salaryTitle'
        required
        />
    </div>
  )
}

export default Form