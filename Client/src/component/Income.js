import React, { useEffect, useState } from "react";
import Form from "./Form";
import Report from "./Report";
import axios from "axios";

const Income = () => {

  const [Data,setData] = useState({title:'',amount:0,date:'',category:'',description:''})
  const [userId,setUserId] = useState('');
  const [cardData, setCardData] = useState([]);
  const [toggle,setToggle] = useState(true);

  const handleSubmit = async(e)=>{
    e.preventDefault();
    setToggle(false)
    try{
      const {title,amount,date,category,description} = Data;
      // console.log(expenseData,"expensedata",userId);
      const response = await axios.post("http://localhost:8081/income/add-income",{
        title,amount,date,category,description,userId
      });
      console.log(response,"responseData");
    }catch(err){
      console.log(err);
    }
    setToggle(true)
    setData({title: "",
      amount: 0,
      date: "",
      category: "",
      description: ""})
    console.log("data submitted");
  }

  useEffect(()=>{
    const user = JSON.parse(localStorage.getItem("user"));
    setUserId(user.id)
    const fetch = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8081/income/get-all-incomes"
        );
        // console.log(response);
        setCardData(response.data);
      } catch (err) {
        console.log(err);
      }
    };

    fetch();
  },[toggle])


  return (
    <div className="IncomeExpenseContainer">
      <h1 className="heading">Incomes</h1>
      <div className="display-amount">
        <span className="inner-heading">Total Income:</span>
        <span className="income-amount">$7400</span>
      </div>
      <section className="form-and-display">
        <div className="form">
          <form onSubmit={handleSubmit}>
          <Form Data={Data} setData={setData}/>
            <button type="submit" className="button">Add Income</button>
          </form>
        </div>
        <div className="work">
        {toggle && <Report cardData={cardData}/>}
        </div>
      </section>
    </div>
  );
};

export default Income;
