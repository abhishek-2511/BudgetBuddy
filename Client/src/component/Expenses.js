import React, { useEffect, useState } from "react";
import Form from "./Form";
import Report from "./Report";
import axios from "axios";
import { rupee } from "../utils/icons";
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";
import { setExpense } from "../store/IncomeSlice";

const Expenses = () => {

  const dispatch = useDispatch();

  const expense = useSelector((state)=>state.manager.Expense)

  const [Data, setData] = useState({
    title: "",
    amount: 0,
    date: "",
    category: "",
    description: "",
  });
  const [userId, setUserId] = useState("");
  const [cardData, setCardData] = useState([]);
  const [toggle,setToggle] = useState(true);
  const [cardId,setCardId] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    setToggle(false)
    try {
      const { title, amount, date, category, description } = Data;
      // console.log(expenseData,"expensedata",userId);
      const response = await axios.post(
        "http://localhost:8081/expense/add-expense",
        {
          title,
          amount,
          date,
          category,
          description,
          userId,
        }
      );
      console.log(response, "responseData");
      setToggle(true)
      setData({title: "",
        amount: 0,
        date: "",
        category: "",
        description: ""})
    } catch (err) {
      console.log(err);
    }
    console.log("data submitted");
  };

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));
    setUserId(user.id);

    const fetch = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8081/expense/get-expensesByUserId/${user.id}`
        );
        // console.log(response);
        setCardData(response.data);
        dispatch(setExpense(response.data));
      } catch (err) {
        console.log(err);
      }
    };

    fetch();
  }, [toggle,cardId]);

  return (
    <div className="IncomeExpenseContainer">
      <h1 className="heading">Expenses</h1>
      <div className="display-amount">
        <span className="inner-heading">Total Income:</span>
        <span className="expense-amount">{rupee} {expense}</span>
      </div>
      <section className="form-and-display">
        <div className="form">
          <form onSubmit={handleSubmit}>
            <Form Data={Data} setData={setData} />
            <button type="submit" className="button">
              Add Expense
            </button>
          </form>
        </div>
        <div className="work">
          {toggle && <Report cardData={cardData} setCardId={setCardId}/>}
        </div>
      </section>
    </div>
  );
};

export default Expenses;
