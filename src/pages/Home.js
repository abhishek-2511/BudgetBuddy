import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import DashBoard from "../component/DashBoard";
import "../index.css";
import { signOut, user } from "../utils/icons";
import SideBar from "../component/SideBar";
import Expenses from "../component/Expenses";
import Income from "../component/Income";
import ViewTransactions from "../component/ViewTransactions";

const Home = () => {
  const navigate = useNavigate();
  const [selectItem, setSelectItem] = useState("Dashboard");

//   useEffect(()=>{
//       var user = localStorage.getItem("user");
//       if(!user){
//           navigate("/login");
//       }
//   },[])
  var Component = "DashBoard";
  useEffect(() => {
    Component = selectItem;
  }, [selectItem]);

  const handleSignOut = ()=>{
    navigate('/login');
  }

  return (
    <div className="HomeContainer">
      <section className="sidebar">
        <div>
          <div className="profile">
            <span className="logo">{user}</span>
            <div>profile</div>
          </div>
          <div className="items">
            <SideBar selectItem={selectItem} setSelectItem={setSelectItem} />
          </div>
        </div>
        <div className="signOut" onClick={handleSignOut}>
            {signOut}
            <span>Sign Out</span>
        </div>
      </section>
      <section className="mainContainer">
        {
        //   console.log(selectItem)
          selectItem === "Dashboard" ? (
            <DashBoard />
          ) : selectItem === "Expenses" ? (
            <Expenses />
          ) : selectItem === "Income" ? (
            <Income />
          ) : (
            <ViewTransactions />
          )
        }
      </section>
    </div>
  );
};

export default Home;
