import { createSlice } from "@reduxjs/toolkit";
import { expense } from "../utils/icons";

const initialState = {
    Total:0,
    Income:0,
    Expense:0,
    incomes:[],
    expenses:[],
    totals:[]
}

export const IncomeSlice = createSlice({
    name:'income',
    initialState,
    reducers:{
        setIncome: (state,action)=>{
            state.incomes = [...action.payload]
            let sum=0;
            action.payload.map((value)=>{
                sum+=value.amount;
            })
            state.Income = sum;

            state.totals = [...action.payload,...state.expenses]
            state.Total = state.Income - state.Expense;
        },
        setExpense: (state,action)=>{
            state.expenses = [...action.payload]
            let sum=0;
            action.payload.map((value)=>{
                sum+=value.amount;
            })
            state.Expense = sum;

            state.totals = [...state.incomes,...action.payload]
            state.Total = state.Income - state.Expense;
        },
    }
   })

export const {setIncome,setExpense,setTotal} = IncomeSlice.actions

export default IncomeSlice.reducer