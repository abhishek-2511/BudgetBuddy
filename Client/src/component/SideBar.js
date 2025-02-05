import React from 'react'
import { menuItems } from '../utils/menuItems'

const SideBar = ({selectItem,setSelectItem}) => {
  return (
    <div>
          {menuItems.map((value,index)=>{
            return(
              <div 
              className='item' 
              key={index}
              onClick={()=>setSelectItem(value.title)}
              >
                <div>{value.icon}</div>
                <div>{value.title}</div>
              </div>
          )
          })}
        </div>
  )
}

export default SideBar