import React from "react";
import {Switch, Redirect, Route,Link} from "react-router-dom";
import {MaterialInput} from './Material-Inp.js';
import i1 from './res/login-bg1.jpg';
import i2 from './res/login-bg2.jpg';
import i3 from './res/login-bg3.jpg';
import i4 from './res/login-bg4.jpg';
import i5 from './res/login-bg5.jpg';
import i6 from './res/login-bg6.jpg';
import i7 from './res/login-bg7.jpg';
import i8 from './res/login-bg8.jpg';

function LoginForm(){
  return (
    <form className="Login">
      <h2>LiDAR</h2>
      <MaterialInput type="email" name="email" label="Email"/>
      <MaterialInput type="password" name="password" label="Password"/>
      <div>
        <h3>Login</h3>
        <Link to="/Lidars" className="login-btn"><i className="fas fa-chevron-right"/></Link>
      </div>
      <div>
        <Link to="/login/register">Create account</Link>
        <Link to="/login/forgot">Forgot password</Link>
      </div>
    </form>
  );
}

function RegistrationForm(){
  return (
    <form className="Login">
    <h2>LiDAR</h2>
      <MaterialInput type="email" name="email" label="Email"/>
      <MaterialInput type="password" name="password" label="Password"/>
      <MaterialInput type="password" name="password" label="Repeat Password"/>
      <div>
        <h3>Register</h3>
        <Link to="/login" className="login-btn"><i className="fas fa-chevron-right"/></Link>
      </div>
      <div>
        <Link to="/login/">I've got a login</Link>
      </div>
    </form>
  );
}

function ForgotForm(){
   return (
    <form className="Login">
    <h2>Forgot pass</h2>
      <MaterialInput type="email" name="email" label="Email"/>
      <div>
        <h3>Get reset link</h3>
        <Link to="/login/forgot-sent" className="login-btn"><i className="fas fa-chevron-right"/></Link>
      </div>
      <div>
        <Link to="/login/">I've got a login</Link>
      </div>
    </form>
  );
}

function ForgotSent(){
  return (
    <div className="Login">
      <h2>Link sent</h2>
      <p>If you entered a valid email address there will be a reset link in your inbox</p>
    </div>
  );
}

export default function Login() {
  let bgs = [i1,i2,i3,i4,i5,i6,i7,i8];
  let bgnum = Math.floor(Math.random() * 8)
  return (
    <div className="Login-container" style={{backgroundImage: `url(${bgs[bgnum]})`}}>
      <Switch>
        <Route exact path="/login">
          <LoginForm/>
        </Route>
        <Route exact path="/login/register">
          <RegistrationForm/>
        </Route>
        <Route exact path="/login/forgot">
          <ForgotForm/>
        </Route>
        <Route exact path="/login/forgot-sent">
          <ForgotSent/>
        </Route>
        <Route path="*">
          <Redirect to="/login"/>
        </Route>
      </Switch>
    </div>
  );
}
