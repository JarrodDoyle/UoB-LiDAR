import React from 'react';
import {
  Link
} from "react-router-dom";

function NavBtn(props){
  return (
    <Link to={props.page}>
      <i className={props.icon}></i>
      <span>{props.name}</span>
    </Link>
  );
}

function Nav(){
  return (
    <nav className="Nav">
      <NavBtn name="LiDARs" page="Lidars" icon="fas fa-home"/>
      <NavBtn name="Dash" page="Dashboard" icon="fas fa-chart-line"/>
      <NavBtn name="Map" page="Home" icon="fas fa-map-marker-alt"/>
    </nav>
  );
}

export default Nav;
