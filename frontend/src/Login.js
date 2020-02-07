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
import i9 from './res/login-bg9.jpg';
import i10 from './res/login-bg10.png';
import i11 from './res/login-bg11.jpg';
import i12 from './res/login-bg12.jpg';
import i13 from './res/login-bg13.jpg';
import i14 from './res/login-bg14.jpg';
import i15 from './res/login-bg15.jpg';
import i16 from './res/login-bg16.jpg';
import i17 from './res/login-bg17.jpg';
import i18 from './res/login-bg18.jpg';
import i19 from './res/login-bg19.jpg';
import i20 from './res/login-bg20.jpg';
import i21 from './res/login-bg21.jpg';
import i22 from './res/login-bg22.jpg';
import i23 from './res/login-bg23.jpg';
import i24 from './res/login-bg24.jpg';
import i25 from './res/login-bg25.jpg';
import i26 from './res/login-bg26.jpg';
import i27 from './res/login-bg27.jpg';
import i28 from './res/login-bg28.jpg';
import i29 from './res/login-bg29.jpg';
import i30 from './res/login-bg30.jpg';
import i31 from './res/login-bg31.jpg';
import "./Login.css";

class LoginForm extends React.Component{
  constructor(props){
    super(props);
    this.state = {redirect: false};
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async handleSubmit(event){
    this.setState({redirect: true});
    event.preventDefault();
  }

  render() {
    if (this.state.redirect){
      return (<Redirect to="/Lidars"/>);
    }else{
      return (
        <form className="Login" onSubmit={this.handleSubmit}>
          <h2>LiDAR</h2>
          <MaterialInput type="email" name="email" label="Email"/>
          <MaterialInput type="password" name="password" label="Password" required/>
          <div>
            <h3>Login</h3>
            <button type="submit" className="circle-btn"><i className="fas fa-chevron-right"/></button>
          </div>
          <div>
            <Link to="/login/register">Create account</Link>
            <Link to="/login/forgot">Forgot password</Link>
          </div>
        </form>
      );
    }
  }
}

class RegistrationForm extends React.Component{
  constructor(props){
    super(props);
    this.state = {redirect: false};
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit(event){
    this.setState({redirect: true});
    event.preventDefault();
  }

  render() {
    if (this.state.redirect){
      return (<Redirect to="/Login"/>);
    }else{
      return (
        <form className="Login" onSubmit={this.handleSubmit}>
        <h2>LiDAR</h2>
          <MaterialInput type="email" name="email" label="Email"/>
          <MaterialInput type="password" name="password" label="Password"/>
          <MaterialInput type="password" name="password" label="Repeat Password"/>
          <div>
            <h3>Register</h3>
            <button type="submit" className="circle-btn"><i className="fas fa-chevron-right"/></button>
          </div>
          <div>
            <Link to="/login/">I've got a login</Link>
          </div>
        </form>
      );
    }
  }
}

class ForgotForm extends React.Component{
  constructor(props){
    super(props);
    this.state = {redirect: false};
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit(event){
    this.setState({redirect: true});
    event.preventDefault();
  }

  render() {
    if (this.state.redirect){
      return (
        <div className="Login">
          <h2>Link sent</h2>
          <p>If you entered a valid email address there will be a reset link in your inbox</p>
        </div>
      );
    }else{
      return (
        <form className="Login" onSubmit={this.handleSubmit}>
        <h2>Forgot pass</h2>
          <MaterialInput type="email" name="email" label="Email"/>
          <div>
            <h3>Get reset link</h3>
            <button type="submit" className="circle-btn"><i className="fas fa-chevron-right"/></button>
          </div>
          <div>
            <Link to="/login/">I've got a login</Link>
          </div>
        </form>
      );
    }
  }
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
  let bgs = [i1,i2,i3,i4,i5,i6,i7,i8,i9,
    i10,i11,i12,i13,i14,i15,i16,i17,i18,i19,
    i20,i21,i22,i23,i24,i25,i26,i27,i28,i29,
    i30,i31];
  let bgnum = Math.floor(Math.random() * bgs.length)
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
