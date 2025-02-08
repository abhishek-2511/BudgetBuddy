import React from 'react'
import {Chart as ChartJs,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
    ArcElement
} from 'chart.js'

import {Line} from 'react-chartjs-2'
import { useSelector } from 'react-redux'

ChartJs.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
    ArcElement
)

const Charts = () => {

    const incomes = useSelector((state)=>state.manager.incomes);
    const expenses = useSelector((state)=>state.manager.expenses);
    console.log(expenses.map(value => value.amount));

    const data = {
        labels:incomes.map(value => value.date),
        datasets:[
            {
                labels:"incomes",
                data:incomes.map(value => value.amount),
                borderColor: "green"
            },
            {
                labels:"expenses",
                data:expenses.map(value => value.amount),
                borderColor: "red"
            }
        ]
    }
  return (
    <div className='chart-container'>
        <Line data={data} />
    </div>
  )
}

export default Charts