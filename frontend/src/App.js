import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from "react-router-dom";
import {Helmet} from "react-helmet";

import Dashboard from './Dashboard.js';
import Lidars from './Lidars.js';
import Login from './Login.js';
import Settings from './Settings.js';
import Nav from './Nav.js';
import MapPage from "./MapPage.js";
import icon from "./res/turbine-fin.gif";
import './App.css';
import './Material-Inp.css';
function NavPage(){
  return (
    <div className="nav-page">
    <Nav/>
    <Switch>
      <Route exact path="/lidars">
        <header>
          <h1>Home</h1>
        </header>
        <Lidars/>
      </Route>
      <Route exact path="/dashboard">
        <header>
          <h1>Dashboard</h1>
        </header>
        <Dashboard/>
      </Route>
      <Route exact path="/map">
        <header>
        <h1>Map</h1>
        </header>
        <MapPage/>
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
        <title>LiDAR</title>
        <link rel="shortcut icon" href={icon}/>
        <script src="https://kit.fontawesome.com/b5b28e0ac4.js" crossorigin="anonymous"></script>
      </Helmet>
      <Switch>
        <Route exact path="/">
          <Redirect to="/login/"/>
        </Route>
        <Route path="/login*">
          <Login/>
        </Route>
        <Route path="*">
          <NavPage/>
        </Route>
      </Switch>
    </Router>
  );
}
