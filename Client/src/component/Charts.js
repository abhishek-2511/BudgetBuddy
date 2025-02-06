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

    const data = {
        labels:["Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"],
        datasets:[
            {
                labels:"incomes",
                data:[2000,3000,100,23000,14000,1200,5000],
                borderColor: "green"
            },
            {
                labels:"expenses",
                data:[1000,200,5000,600,1200,900,700],
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