import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";

import Dashboard from './Dashboard.js';
import Nav from './Nav.js';

function Page(props){
  if (props.nav === "true"){
    return (
      <div>
        <Nav/>
        {props.page}
      </div>
    );
  }else{
    return (
      <div>
        {props.page}
      </div>
    );
  }
}

export default function App() {
  return (
    <Router>
      <Switch>
        <Route exact path="/">
          <Page nav="false" page="Home"/>
        </Route>
        <Route exact path="/login">
          <Page nav="false" page="Login"/>
        </Route>
        <Route path="/dashboard">
          <Page nav="true" page="Dashboard"/>
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

