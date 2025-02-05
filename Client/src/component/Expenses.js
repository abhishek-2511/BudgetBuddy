import React from "react";
import Form from "./Form";
import Report from "./Report";

const Expenses = () => {

  const handleSubmit = (e)=>{
    e.preventDefault();
    console.log("data submitted");
  }

  return (
    <div className="IncomeExpenseContainer">
      <h1 className="heading">Expenses</h1>
      <div className="display-amount">
        <span className="inner-heading">Total Income:</span>
        <span className="expense-amount">$7400</span>
      </div>
      <section className="form-and-display">
        <div className="form">
          <form onSubmit={handleSubmit}>
            <Form />
            <button type="submit" className="button">Add Expense</button>
          </form>
        </div>
        <div className="work">
          <Report />
        </div>
      </section>
    </div>
  );
};

export default Expenses;
