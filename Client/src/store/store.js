import {configureStore} from '@reduxjs/toolkit'
import IncomeReducer from './IncomeSlice'

export const store = configureStore({
    reducer:{
        manager:IncomeReducer,
    },
})