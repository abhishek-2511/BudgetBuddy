import React, { useEffect, useState } from "react";
import { rupee } from "../utils/icons";
import { useSelector } from "react-redux";
import Report from "./Report";
import TransactionCard from "./TransactionCard";

const ViewTransactions = () => {
  const total = useSelector((state) => state.manager.Total);
  const income = useSelector((state) => state.manager.Income);
  const expense = useSelector((state) => state.manager.Expense);
  const totalTransactions = useSelector((state) => state.manager.totals);
  useEffect(()=>{
    setCardData(totalTransactions)
  },[totalTransactions])
  const [cardData, setCardData] = useState([]);

  return (
    <div className="transactions-container">
      <div className="transactions-heading">
        <h2>All transactions</h2>
      </div>
      <div className="transactions-content">
        <div>
        <div className="work">
          <TransactionCard cardData={cardData} />
        </div>
        </div>
        <div>
          <div className="total-card">
            <h3>Total Income</h3>
            <p style={{ color: "green" }}>
              {rupee} {income}
            </p>
          </div>
          <div className="total-card" >
            <h3>Total Expense</h3>
            <p style={{ color: "red" }}>
              {rupee} {expense}
            </p>
          </div>
          <div className="total-card" >
            <h3>Total Balance</h3>
            <p style={{ color: "#79adcc" }}>
              {rupee} {total}
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ViewTransactions;
