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
        "x": 0,
        "y": 256
      },
      {
        "x": 1,
        "y": 88
      },
      {
        "x": 2,
        "y": 2
      },
      {
        "x": 3,
        "y": 170
      },
    ]
  }
]

function Popup(props) {
  let names = ["fas fa-check ok", "fas fa-bacon almost", "fas fa-times bad"]
  let style = names[Math.floor(Math.random() * 3)];
  return (
    <div className="popup-background">
      <div className="popup-box">
        <div className="popup-header">
          <h2>{props.currentKPI}</h2>
          <div className="kpi-timescale">
            <span>Week</span>
            <span>Month</span>
            <span>6 Months</span>
            <span>Year</span>
          </div>
          <i className={style}></i>
          <div className="lidars-btns">
            <button className="circle-btn" onClick={() => props.togglePopup(null)}>
              <i className="fas fa-times"/>
            </button>
          </div>
        </div>
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
          <div className="popup-grid-item-fat">
            <Graph data={mydata}/>
          </div>
        </div>
      </div>
    </div>
  )
}

function Graph(props) {
  let commonProps = {
    margin: { top: 20, right: 20, bottom: 40, left: 40 },
    animate: true,
    enableSlices: 'x',
  }
  return(
    <AutoSizer>
      {({ height, width }) => (
        // <div className="lidars-card" style={`width: ${width};`}>
          <Line
            {... commonProps}
            data={props.data}
            height={height}
            width={width}
            xScale={{
              type: 'point',
              min: 0,
              max: 'auto',
            }}
            axisLeft={{
              legend: 'y-axis',
            }}
            axisBottom={{
              legend: 'x-axis',
            }}
          />
        // </div>
      )}
    </AutoSizer>        
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
        <button className="circle-btn" onClick={() => props.togglePopup(props.title)}>
          <i className="fas fa-chevron-right"/>
        </button>
      </div>
    </div>
  );
}

class Dashboard extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      showPopup: false,
      popupTitle: null,
    };
    this.togglePopup = this.togglePopup.bind(this);
    this.openPopup = this.openPopup.bind(this);
  }

  togglePopup() {
    this.setState({
      showPopup: !this.state.showPopup,
    });
  }

  openPopup(newPopupTitle) {
    this.togglePopup();
    this.setState( {
      popupTitle: newPopupTitle,
    })
  }

  render() {
    return (
      <main>
        <div className="lidars-grid">
          <Card title="System Availability" content={["1 month average - 91%", "Campaign average - 97%"]} togglePopup={this.openPopup}/>
          <Card title="Post Processed Data Availability" content={["1 month average - 88%", "Campaign average - 88%"]} togglePopup={this.openPopup}/>
          <Card title="Data Coverage" content={["something"]} togglePopup={this.openPopup}/>
          <Card title="Maintenance Visits" content={["0"]} togglePopup={this.openPopup}/>
          <Card title="Unscheduled Outages" content={["0"]} togglePopup={this.openPopup}/>
          <Card title="Comms. Uptime" content={["100%"]} togglePopup={this.openPopup}/>
          <Card title="Mean Wind Speed" content={["Slope - 1.00", "Coefficient of Determination - 1.00"]} togglePopup={this.openPopup}/>
          <Card title="Mean Wind Direction" content={["Slope - 1.00", "Coefficient of Determination - 1.00"]} togglePopup={this.openPopup}/>
          <Card title="Turbulence Intensity" content={["Slope - x", "Correlation Co-efficient - x"]} togglePopup={this.openPopup}/>
          <Card title="Wind Shear" content={["Shear exponent - x"]} togglePopup={this.openPopup}/>
        </div>
        {this.state.showPopup ?  
          <Popup togglePopup={this.togglePopup} currentKPI={this.state.popupTitle}/>  
          : null  
        }  
      </main>
    );
  }
}

export default Dashboard
