import React, { useState } from 'react'
import Logo from '../utils/logo.webp';
import { Link, useNavigate } from 'react-router';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify'

const SignUp = () => {

    const navigate = useNavigate();

    const [RegisterData, setRegisterData] = useState({ username: '', email: '', password: '', confirmPassword: '' });

    const handleChange = (e) => {
        setRegisterData({ ...RegisterData, [e.target.name]: e.target.value });
    }

    const handleSubmit = async (e) => {
        try {
            if (handleValidation()) {
                const { username, email, password } = RegisterData;
                const { data } = await axios.post('http://localhost:8081/user/register', {
                    username,
                    email,
                    password
                });
                navigate("/login");

                if (data.status === 400) {
                    toast.error(data.msg, {
                        position: 'top-right',
                        draggable: true,
                        autoClose: 5000,
                        pauseOnHover: true
                    })
                }
                else if (data.status === true) {
                    toast.success(data.msg, {
                        position: 'top-right',
                        autoClose: 5000,
                        draggable: true,
                        pauseOnHover: true
                    })
                    setRegisterData({ username: '', email: '', password: '', confirmPassword: '' })

                    setTimeout(() => {
                        navigate('/');
                    }, 2000);
                }
            }
        } catch (error) {
            toast.error(error.response.data, {
                position: 'top-right',
                draggable: true,
                autoClose: 5000,
                pauseOnHover: true
            })
        }
    }

    const handleValidation = () => {
        const { username, email, password, confirmPassword } = RegisterData;
        const regEx = new RegExp("[`~!@#$%^&*()\\]\\[+={}/|:;\"\'<>,.?-_]");
        const pass = regEx.test(password);

        if (password !== confirmPassword) {
            toast.error("Enter valid Credentials", {
                position: 'top-right',
                autoClose: 5000,
                pauseOnHover: true,
                draggable: true
            })
            return false;
        }
        else if (!pass) {
            toast.error("special character must include in password", {
                position: 'top-right',
                autoClose: 5000,
                pauseOnHover: true,
                draggable: true
            })
            return false;
        }
        else if (password.length < 7) {
            toast.error("password must contain minimum 8 characters", {
                position: 'top-right',
                autoClose: 5000,
                pauseOnHover: true,
                draggable: true
            })
            return false;
        }
        else if (username.length < 5) {
            console.log(username, "inside validation");
            toast.error("username must contain minimum 8 characters", {
                position: 'top-right',
                autoClose: 5000,
                pauseOnHover: true,
                draggable: true
            })
            return false;
        }
        else if (email.length < 7) {
            toast.error("email must contain minimum 8 characters", {
                position: 'top-right',
                autoClose: 5000,
                pauseOnHover: true,
                draggable: true
            })
            return false;
        }
        return true;

    }

    return (
        <div className='formContainer' >
            <div className="formWrapper">
                <h1>An Expense Tracker App</h1>
                <p>Managing finances has never been easier</p>
            </div>
            <form action={handleSubmit}>
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
                    type='email'
                    placeholder='Email'
                    name='email'
                    onChange={handleChange}
                />
                <input
                    type='password'
                    placeholder='Password'
                    name='password'
                    onChange={handleChange}
                />
                <input
                    type='password'
                    placeholder='Confirm Password'
                    name='confirmPassword'
                    onChange={handleChange}
                />
                <button type='submit'>Submit</button>
                <span>Already have an account?<Link to="/login">Login</Link></span>
            </form>
            <ToastContainer />
        </div>
    )
}

export default SignUp