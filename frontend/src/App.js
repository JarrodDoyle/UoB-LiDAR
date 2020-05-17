import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
  useLocation,
} from "react-router-dom";
import { connect, useSelector } from 'react-redux';
import { addSite, addApiKey } from './redux/actions.js';
import { getEmail } from './redux/selectors.js';
import Dashboard from './Dashboard.js';
import Sites from './Sites.js';
import Login from './Login.js';
import Settings from './Settings';
import Nav from './Nav.js';
import MapPage from "./MapPage.js";
import PageNotFound from "./ErrorPage.js";
import './App.css';
import './Components/Material-Inp.css';

function NavPage(){
  const email = useSelector(getEmail);
  let location = useLocation();
  if (email === null || email === ""){
    return (<Redirect to={"/Login?redirect=" + String(location.pathname)}/>);
  }
  return (
    <div className="nav-page">
    <Nav/>
    <Switch>
      <Route path="/app/sites">
        <header>
          <h1>Home</h1>
        </header>
        <Sites/>
      </Route>
      <Route path="/app/dashboard/:siteId">
        <header>
          <h1>Dashboard</h1>
        </header>
        <Dashboard/>
      </Route>
      <Route path="/app/map">
        <header>
          <h1>Map</h1>
        </header>
        <MapPage/>
      </Route>
      <Route path="/app/settings">
        <header>
          <h1>Settings</h1>
        </header>
        <Settings/>
      </Route>
      <Route path="*">
        <Redirect to="/404"/>
      </Route>
    </Switch>
    </div>
  );
}

function App(props) {
  props.dispatch(addApiKey({
    key: "abcdefghijklmnopqrstuvwxyz",
    name: "Hornsea One upload",
    sites: [
      {
        siteid: "site1",
        write: false,
        read: true,
      }
    ],
  }));
  props.dispatch(addSite({
    id: "site1",
    name: "Hornsea One",
    desc: " 174 turbines with a capacity of 1214MW owned by Ørsted (company) and Global Infrastructure Partners",
    totalComplete: 87,
    location: {
      lat: 53.885,
      lng: 1.791111,
    }
 }))
  props.dispatch(addSite({
    id: "stie2",
    name: "Hornsea Two",
    desc: " 165 turbines with a capacity of 1386MW owned by Ørsted (company) and Global Infrastructure Partners",
    totalComplete: 38,
    location: {
      lat: 54.885,
      lng: 1.791111,
    }
  }))
  props.dispatch(addSite({
    id: "site3",
    name: "Triton Knoll",
    desc: "90 Turbines with capacity of 857MW commissioning in 2021",
    totalComplete: 78,
    location: {lat: 53.066667,lng: 0.15}
  }))
  props.dispatch(addSite({
    id: "site4",
    name: "Methil",
    desc: "1 x Samsung 7MW, commissioned in 2013 and owned by Samsung and 2-B energy",
    totalComplete: 100,
    location: {lat: 56.162778,lng: -3.008889}
  }))
  props.dispatch(addSite({
    id: "site5",
    name: "London Array",
    desc: "175 x Siemens SWT-3.6, commissioned in 2013 and owned by Ørsted, E.ON UK Renewables, Masdar",
    totalComplete: 100,
    location: {lat: 51.643889,lng: 1.553611}
  }))
  props.dispatch(addSite({
    id: "site6",
    name: "Gwynt y Môr",
    desc: "160 x Siemens SWT-3.6, commissioned in 2015 and owned by RWE Npower, Stadtwerke München, GIB, Siements",
    totalComplete: 100,
    location: {lat: 53.45,lng: -3.583333}
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
        <Route exact path="/404">
          <PageNotFound/>
        </Route>
        <Route path="/app/*">
          <NavPage/>
        </Route>
        <Route exact path="*">
          <Redirect to="/404"/>
        </Route>
      </Switch>
    </Router>
  );
}
export default connect()(App);
