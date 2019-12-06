import React from 'react';
import {
  Link
} from "react-router-dom";

function NavBtn(props){
  return (
    <button>
      <Link to={props.page}>{props.name}</Link>
    </button>
  );
}

function Nav(){
  return (
    <nav>
      <NavBtn name="Login" page="Login"/>
      <NavBtn name="Dashboard" page="Dashboard"/>
    </nav>
  );
}

export default Nav;
