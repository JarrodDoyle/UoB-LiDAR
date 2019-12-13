import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
  Link
} from "react-router-dom";
import {Helmet} from "react-helmet";

import Dashboard from './Dashboard.js';
import Lidars from './Lidars.js';
import Login from './Login.js';
import Settings from './Settings.js';
import Nav from './Nav.js';
import './App.css';

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
      <Route exact path="/map">
        <h1>Map coming soon</h1>
      </Route>
      <Route exact path="/settings">
        <Settings/>
      </Route>
    </Switch>
    </div>
  );
}

export default function App() {
  return (
    <Router>
      <Helmet>
        <script src="https://kit.fontawesome.com/b5b28e0ac4.js" crossorigin="anonymous"></script>
      </Helmet>
      <Switch>
        <Route exact path="/">
          <Redirect to="/login"/>
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
