import React, { useState } from 'react'
import { Link, useNavigate} from 'react-router';
import Logo from '../utils/logo.webp';

const Login = () => {

    const navigate = useNavigate();
    const [loginData, setLoginData] = useState({username:'',password:''});

    const handleChange = (e)=>{
        setLoginData({...loginData,[e.target.name]:e.target.value});
    }

    const handleSubmit = (e)=>{
        e.preventDefault();
        console.log("Login Done");
        navigate("/")
    }

  return (
    <div className='formContainer' onSubmit={handleSubmit}>
        <form>
            <div className='heading'>
                <img src={Logo} alt='Logo' />
                <h1>Budget Buddy</h1>
            </div>
            <input 
            type='text'
            placeholder='UserName'
            name='username'
            onChange={handleChange}
            />  
            <input 
            type='password'
            placeholder='Password'
            name='password'
            onChange={handleChange}
            />
            <button type='submit'>Submit</button>
            <span>Create an account?<Link to="/register">SignUp</Link></span>
        </form>
        {/* <ToastContainer /> */}
    </div>
  )
}

export default Login