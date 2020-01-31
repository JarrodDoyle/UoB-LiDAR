import React from "react";
import { Link } from "react-router-dom";
import _ from "lodash";
import "./Dashboard.css";

function Card(props) {
  return (
    <div className="lidars-card">
      <div className="dash-content">
        <div className="kpi-indicator">
          <span>Pass</span>
        </div>
        <h3>{props.title}</h3>
        {props.content.map((line, i) => {       
           return (<p>{line}</p>) 
        })}
      </div>
      <div className="dash-buttons">
        <Link to={props.page}>
          <span className="text">More details</span>
        </Link>
      </div>
    </div>
  );
}

function Dashboard(props) {
  return (
    <main className="lidars-grid">
      <Card title="System Availability" content={["1 month average - 91%", "Campaign average - 97%"]}/>
      <Card title="Post Processed Data Availability" content={["1 month average - 88%", "Campaign average - 88%"]}/>
      <Card title="Data Coverage" content={["something"]}/>
      <Card title="Maintenance Visits" content={["0"]}/>
      <Card title="Unscheduled Outages" content={["0"]}/>
      <Card title="Comms. Uptime" content={["100%"]}/>
      <Card title="Mean Wind Speed" content={["Slope - 1.00", "Coefficient of Determination - 1.00"]}/>
      <Card title="Mean Wind Direction" content={["Slope - 1.00", "Coefficient of Determination - 1.00"]}/>
      <Card title="Turbulence Intensity" content={["Slope - x", "Correlation Co-efficient - x"]}/>
      <Card title="Wind Shear" content={["Shear exponent - x"]}/>
    </main>
  );
}

export default Dashboard
