import React from "react";
import {Link} from "react-router-dom";
import i1 from './res/login-bg1.jpg';
import i2 from './res/login-bg2.jpg';
import i3 from './res/login-bg3.jpg';
import i4 from './res/login-bg4.jpg';
import i5 from './res/login-bg5.jpg';
import i6 from './res/login-bg6.jpg';
import i7 from './res/login-bg7.jpg';
import i8 from './res/login-bg8.jpg';

function MaterialInput(props){
  console.log(props.required);
  return (
    <div className="Material-Inp-Group">
      <input type={props.type} name={props.name} required></input>
      <span className="highlight"></span>
      <span className="bar"></span>
      <label htmlFor={props.name}>{props.label}</label>
    </div>
  );
}

export default function Login() {
  let bgs = [i1,i2,i3,i4,i5,i6,i7,i8];
  let bgnum = Math.floor(Math.random() * 8)
  return (
    <div className="Login-container" style={{backgroundImage: `url(${bgs[bgnum]})`}}>
      <form className="Login">
        <h2>LiDAR</h2>
        <MaterialInput type="email" name="email" label="Email"/>
        <MaterialInput type="password" name="password" label="Password"/>
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
