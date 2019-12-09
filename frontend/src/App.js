import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";

import Dashboard from './Dashboard.js';
import Lidars from './Lidars.js';
import Nav from './Nav.js';
import './App.css';
import 'https://kit.fontawesome.com/b5b28e0ac4.js';

function NavPage(){
  return (
    <div className="nav-page">
    <Nav/>
    <Switch>
      <Route exact path="/lidars">
        <Lidars/>
      </Route>
      <Route exact path="/dashboard">
        <Dashboard/>
      </Route>
    </Switch>
    </div>
  );
}

export default function App() {
  return (
    <Router>
      <Switch>
        <Route exact path="/">
          <Home/>
        </Route>
        <Route exact path="/login">
          <Login/>
        </Route>
        <Route path="*">
          <NavPage/>
        </Route>
      </Switch>
    </Router>
  );
}

// You can think of these components as "pages"
// in your app.

function Home(){
  return (
    <div>
      <h1>Welcome to LiDAR</h1>
      <p>Please login:</p>
      <button>
       <Link to="/Login">Login</Link>
      </button>
    </div>
  );
}

function Login() {
  return (
    <div>
      <h2>Please login to the system</h2>
      <Link to="/Lidar">Login</Link>
    </div>
  );
}

