import React from "react";
import {Link} from "react-router-dom";

export default function Login() {
  return (
    <div className="Login-container">
      <form className="Login">
        <h2>LiDAR</h2>
        <label for="email">Email:</label>
        <input type="email" name="email"></input>
        <label for="pass">Password:</label>
        <input type="password" name="password"></input>
        <div>
          <h3>Login</h3>
          <Link to="/Lidars" className="login-btn"><i className="fas fa-chevron-right"/></Link>
        </div>
        <div>
          <Link to="#">Create account</Link>
          <Link to="#">Forgot password</Link>
        </div>
      </form>
    </div>
  );
}
