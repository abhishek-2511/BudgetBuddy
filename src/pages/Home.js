import React, { useEffect } from 'react'
import { useNavigate } from 'react-router';
import DashBoard from '../component/DashBoard';
import '../index.css';
import { user } from '../utils/icons';


const Home = () => {

    const navigate = useNavigate();
    
    // useEffect(()=>{
    //     var user = localStorage.getItem("user");
    //     if(!user){
    //         navigate("/login");
    //     }
    // },[])

  return (
    <div className='HomeContainer'>
        <section className='sidebar'>
            <div>
                <div className="profile">
                    <span className='logo'>{user}</span>
                    profile
                </div>
                <div className="items">
                    <DashBoard />
                </div>
            </div>
            <div className='signOut'>
                Sign Out
            </div>
        </section>
        <section className='mainContainer'>
            Body
        </section>
    </div>
  )
}

export default Home