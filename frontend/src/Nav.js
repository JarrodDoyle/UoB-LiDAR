import React from 'react';
import {
  Link
} from "react-router-dom";

function NavBtn(props){
  return (
    <Link to={props.page}>
      <i class="{props.icons}"></i>
      <span>{props.name}</span>
    </Link>
  );
}

function Nav(){
  return (
    <nav className="Nav">
      <NavBtn name="LiDARs" page="Lidars"/>
      <NavBtn name="Dashboard" page="Dashboard"/>
    </nav>
  );
}

export default Nav;
