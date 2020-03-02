import React from "react";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import { getEmail } from "./redux/selectors.js";
import bg from "./res/errorbg.jpg";
import "./ErrorPage.css";

export default function PageNotFound(){
  const email = useSelector(getEmail).email;
  let dest = "/Login";
  if (email !== "")
    dest = "app/Sites";
  return (
    <div className="error-page" style={{backgroundImage: `url(${bg})`}}>
      <div className="error-box">
        <h1>404 - Houston we need more wind</h1>
        <p>We cant seam to find any wind to power the app...</p>
        <Link to={dest}>Click here to get back on track</Link>
      </div>
    </div>
  );
}
