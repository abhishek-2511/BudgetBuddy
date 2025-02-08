import React, { useState } from "react";
import { Link, useNavigate } from "react-router";
import Logo from "../utils/logo.webp";
import axios from "axios";
import { ToastContainer, toast } from 'react-toastify'

const Login = () => {
  const navigate = useNavigate();
  const [loginData, setLoginData] = useState({ username: "", password: "" });

  const handleChange = (e) => {
    setLoginData({ ...loginData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    // console.log("Login Done");
    try {
      const response = await axios.post("http://localhost:8081/user/login", {
        username: loginData.username,
        password: loginData.password,
      });
      //   console.log(response);
      localStorage.setItem("user", JSON.stringify(response.data));
      navigate("/");
    } catch (err) {
      //   console.log(err.response.data, "error");
      toast.error(err.response.data, {
        position: "top-right",
        autoClose: 5000,
        pauseOnHover: true,
        draggable: true,
      });
    }
  };

  return (
    <div className="formContainer" onSubmit={handleSubmit}>
      <div className="formWrapper">
        <h1>An Expense Tracker App</h1>
        <p>Managing finances has never been easier</p>
      </div>
      <form>
        <div className="heading">
          <img src={Logo} alt="Logo" />
          <h1>Budget Buddy</h1>
        </div>
        <input
          type="text"
          placeholder="UserName"
          name="username"
          onChange={handleChange}
        />
        <input
          type="password"
          placeholder="Password"
          name="password"
          onChange={handleChange}
        />
        <button type="submit">Submit</button>
        <span>
          Create an account?<Link to="/register">SignUp</Link>
        </span>
      </form>
      <ToastContainer />
    </div>
  );
};

export default Login;
