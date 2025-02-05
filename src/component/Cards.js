import React from "react";
import { Delete, Transactions } from "../utils/icons";

const Cards = () => {
  return (
    <div className="card">
      <div className="card-logo">{Transactions}</div>
      <section className="card-content">
        <div>
          <span>Freelance</span>
        </div>
        <div className="card-details">
          <div className="amount">
            <span>$ 1200</span>
          </div>
          <div className="date">
            {Transactions}
            <span>12/12/2003</span>
          </div>
          <div className="card-income">
            {Transactions}
            <span>Freelancing Salary</span>
          </div>
        </div>
      </section>
      <div className="delete">
        {Delete}
      </div>
    </div>
  );
};

export default Cards;
