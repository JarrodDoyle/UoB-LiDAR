import React from "react";
import { Link } from "react-router-dom";
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

function Popup(props) {
  return (
    <div className="popup-background">
      <div className="popup-box">
        <h3>{props.currentKPI}</h3>
        <button onClick={props.togglePopup}>close me</button>
        <div className="popup-grid">
          <div className="popup-grid-item-tall">
            <Graph data={mydata}/>
          </div>
          <div className="popup-grid-item">
            <Graph data={mydata}/>
          </div>
          <div className="popup-grid-item">
            <Graph data={mydata}/>
          </div>
        </div>
      </div>
    </div>
  )
}

function Graph(props) {
  return(
    // <div className="lidars-card">
      <AutoSizer>
        {({ height, width }) => (
          <Line
            data={props.data}
            height={height}
            width={width}
          />
        )}
      </AutoSizer>        
    // </div>
  )
}

function Card(props) {
  let names = ["fas fa-check ok", "fas fa-bacon almost", "fas fa-times bad"]
  let style = names[Math.floor(Math.random() * 3)];
  return (
    <div className="lidars-card">
      <div className="kpi-indicator">
        <h3>{props.title}</h3>
        <i className={style}></i>
      </div>
      <div className="dash-content">
        {props.content.map((line, i) => {       
           return (<p>{line}</p>) 
        })}
      </div>
      <div className="lidars-btns">
        <h4>More details</h4>
        <button className="circle-btn" onClick={props.togglePopup}>
          <i className="fas fa-chevron-right"/>
        </button>
      </div>
    </div>
  );
}

class Dashboard extends React.Component {
  constructor(props) {
    super(props);
    this.state = { showPopup: false };
    this.togglePopup = this.togglePopup.bind(this);
  }

  togglePopup() {
    this.setState({
      showPopup: !this.state.showPopup,
    });
  }

  render() {
    return (
      <main>
        <div className="lidars-grid">
          <Card title="System Availability" content={["1 month average - 91%", "Campaign average - 97%"]} togglePopup={this.togglePopup}/>
          <Card title="Post Processed Data Availability" content={["1 month average - 88%", "Campaign average - 88%"]} togglePopup={this.togglePopup}/>
          <Card title="Data Coverage" content={["something"]} togglePopup={this.togglePopup}/>
          <Card title="Maintenance Visits" content={["0"]} togglePopup={this.togglePopup}/>
          <Card title="Unscheduled Outages" content={["0"]} togglePopup={this.togglePopup}/>
          <Card title="Comms. Uptime" content={["100%"]} togglePopup={this.togglePopup}/>
          <Card title="Mean Wind Speed" content={["Slope - 1.00", "Coefficient of Determination - 1.00"]} togglePopup={this.togglePopup}/>
          <Card title="Mean Wind Direction" content={["Slope - 1.00", "Coefficient of Determination - 1.00"]} togglePopup={this.togglePopup}/>
          <Card title="Turbulence Intensity" content={["Slope - x", "Correlation Co-efficient - x"]} togglePopup={this.togglePopup}/>
          <Card title="Wind Shear" content={["Shear exponent - x"]} togglePopup={this.togglePopup}/>
        </div>
        {this.state.showPopup ?  
          <Popup togglePopup={this.togglePopup} currentKPI={this.state.currentKPI}/>  
          : null  
        }  
      </main>
    );
  }
}

export default Dashboard
