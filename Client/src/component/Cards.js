import React from "react";
import { Delete, Transactions } from "../utils/icons";

const Cards = ({value}) => {

  console.log(value,"Carddata")
  return (
    <div className="card">
      <div className="card-logo">{Transactions}</div>
      <div className="card-content">
        <div>
          <span>{value.title}</span>
        </div>
        <div className="card-details">
          <div className="amount">
            <span>$ {value.amount}</span>
          </div>
          <div className="date">
            {Transactions}
            <span>{value.date}</span>
          </div>
          <div className="card-income">
            {Transactions}
            <span>{value.description}</span>
          </div>
        </div>
      </div>
      <div className="delete">
        {Delete}
      </div>
    </div>
  );
};

export default Cards;
