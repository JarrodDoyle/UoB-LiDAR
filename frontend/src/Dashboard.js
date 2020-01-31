import React from "react";
import { Link } from "react-router-dom";
import _ from "lodash";
import "./Dashboard.css";
import { Line } from 'nivo'
import { AutoSizer } from 'react-virtualized'

const mydata = [
  {
    "id": "japan",
    "color": "hsl(227, 70%, 50%)",
    "data": [
      {
        "x": "plane",
        "y": 256
      },
      {
        "x": "helicopter",
        "y": 88
      },
      {
        "x": "boat",
        "y": 2
      },
      {
        "x": "train",
        "y": 170
      },
      {
        "x": "subway",
        "y": 79
      },
      {
        "x": "bus",
        "y": 246
      },
      {
        "x": "car",
        "y": 126
      },
      {
        "x": "moto",
        "y": 169
      },
      {
        "x": "bicycle",
        "y": 3
      },
      {
        "x": "horse",
        "y": 284
      },
      {
        "x": "skateboard",
        "y": 50
      },
      {
        "x": "others",
        "y": 41
      }
    ]
  }
]

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
    <main>
      <div className="lidars-grid">
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
        {/* <div class="lidars-card"><ResponsiveLine data={mydata}/></div> */}
        {/* <div className="lidars-card">
          <div style={{ height: '100%', display: 'flex' }}>
            <div style={{ width: "100%" }}>
              <ResponsiveLine data={mydata}/>
            </div>
          </div>
        </div> */}
        <div className="lidars-card">
          <AutoSizer>
            {({ height, width }) => (
              <Line
                data={mydata}
                height={height}
                width={width}
              />
            )}
          </AutoSizer>        
        </div>
      </div>
    </main>
  );
}

export default Dashboard
