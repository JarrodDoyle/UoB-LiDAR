import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";

import Dashboard from './Dashboard.js';
import Nav from './Nav.js';

export default function App() {
  return (
    <Router>
      <Nav/>
          <hr />

        {/*
          A <Switch> looks through all its children <Route>
          elements and renders the first one whose path
          matches the current URL. Use a <Switch> any time
          you have multiple routes, but you want only one
          of them to render at a time
        */}
        <Switch>
          <Route exact path="/">
            <Home />
          </Route>
          <Route exact path="/login">
            <Login />
          </Route>
          <Route path="/dashboard">
            <Dashboard />
          </Route>
        </Switch>
    </Router>
  );
}

// You can think of these components as "pages"
// in your app.
//

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
      <h2>Login</h2>
    </div>
  );
}

