import React, { useEffect } from 'react'
import Charts from './Charts'
import { rupee } from '../utils/icons'
import { useSelector } from 'react-redux'
import { useDispatch } from 'react-redux'
import axios from 'axios'
import { setExpense, setIncome, setTotal } from '../store/IncomeSlice'


const DashBoard = () => {

  const dispatch = useDispatch();
  const total = useSelector((state) => state.manager.Total);
  const income = useSelector((state) => state.manager.Income);
  const expense = useSelector((state) => state.manager.Expense);
  console.log(income, expense)

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));
    const fetchIncome = async () => {
      try {
        const response = await axios.get(`http://localhost:8081/income/get-incomeByUserId/${user.id}`);
        const sortedItems = [...response.data].sort((a, b) => new Date(a.date) - new Date(b.date));
        dispatch(setIncome(sortedItems));
      } catch (err) {
        console.log(err);
      }
    };

    fetchIncome();

    const fetchExpense = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8081/expense/get-expensesByUserId/${user.id}`
        );
        const sortedItems = [...response.data].sort((a, b) => new Date(a.date) - new Date(b.date));
        dispatch(setExpense(sortedItems));
      } catch (err) {
        console.log(err);
      }
    };

    fetchExpense();
  }, [])

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
            <p style={{ color: "green" }}>{rupee} {income}</p>
          </div >
          <div className='total-card'>
            <h3>Total Expense</h3>
            <p style={{ color: "red" }}>{rupee} {expense}</p>
          </div>
          <div className='total-card'>
            <h3>Total Balance</h3>
            <p style={{ color: "#79adcc" }}>
              {rupee} {total}</p>
          </div>
        </div>
      </div>
    </div>
  )
}

export default DashBoard