import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from "react-router-dom";
import { connect } from 'react-redux';
import { addSite } from './redux/actions.js';
import Dashboard from './Dashboard.js';
import Sites from './Sites.js';
import Login from './Login.js';
import Settings from './Settings.js';
import Nav from './Nav.js';
import MapPage from "./MapPage.js";
import './App.css';
import './Material-Inp.css';

function NavPage(){
  return (
    <div className="nav-page">
    <Nav/>
    <Switch>
      <Route exact path="/sites">
        <header>
          <h1>Home</h1>
        </header>
        <Sites/>
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
        <header>
          <h1>Settings</h1>
        </header>
        <Settings/>
      </Route>
    </Switch>
    </div>
  );
}

function App(props) {
  props.dispatch(addSite({
    id: 1,
    name: "Brighton Off-Shore 1",
    desc: "This is an offshore windfarm 1 This is an offshore windfarm 1 This is an offshore windfarm 1 This is an offshore windfarm 1",
    location: {
      lat: 50.643758,
      lng: -0.257144
    }
 }))
  props.dispatch(addSite({
    id: 2,
    name: "Brighton Off-Shore 2",
    desc: "This is an offshore windfarm 2",
    location: {lat: 53.852400,lng: -3.697895}
  }))
  props.dispatch(addSite({
    id: 3,
    name: "North Sea Site 1",
    desc: "This is an offshore windfarm 3",
    location: {lat: 53.415865,lng: 0.689438}
  }))
  props.dispatch(addSite({
    id: 4,
    name: "North Sea Site 2",
    desc: "This is an offshore windfarm 4",
    location: {lat: 50.510669,lng: -2.240459}
  }))
  return (
    <Router>
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
export default connect()(App);
