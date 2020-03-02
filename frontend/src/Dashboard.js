import React from "react";
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
  var kpiTitles = [props.cards[props.kpiID].title]
  for (var i=0; i<props.cards.length; i++) {
    if (i !== props.kpiID) {
      kpiTitles.push(props.cards[i].title)
    }
  }

  return (
    <div className="popup-background">
      <div className="popup-box">
        <div className="popup-header">
          <select className="kpi-dropdown">
            {kpiTitles.map((kpi, i) => {       
              return (<option value={i}>{kpi}</option>) 
            })}
          </select>
          <div className="kpi-timescale">
            <span>Week</span>
            <span>Month</span>
            <span>6 Months</span>
            <span>Year</span>
          </div>
          <i className={props.cards[props.kpiID].passingStyle}></i>
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
  return (
    <div className="lidars-card">
      <div className="kpi-indicator">
        <h3>{props.title}</h3>
        <i className={props.passingStyle}></i>
      </div>
      <div className="dash-content">
        {props.content.map((line, i) => {       
           return (<p>{line}</p>) 
        })}
      </div>
      <div className="lidars-btns">
        <h4>More details</h4>
        <button className="circle-btn" onClick={() => props.togglePopup(props.id)}>
          <i className="fas fa-chevron-right"/>
        </button>
      </div>
    </div>
  );
}

class Dashboard extends React.Component {
  constructor(props) {
    super(props);
    this.togglePopup = this.togglePopup.bind(this);
    this.openPopup = this.openPopup.bind(this);
    this.generateCards = this.generateCards.bind(this);
    this.state = {
      showPopup: false,
      cards: this.generateCards(),
      currentKPI: null,
    };
    
  }

  togglePopup() {
    this.setState({
      showPopup: !this.state.showPopup,
    });
  }

  openPopup(currentKpiID) {
    this.togglePopup();
    this.setState( {
      currentKPI: currentKpiID,
    })
  }

  generateCards() {
    var cards = []
    var titles = [
      "System Availability",
      "Post Processed Data Availability",
      "Data Coverage", "Maintenance Visits",
      "Unscheduled Outages",
      "Comms. Uptime",
      "Mean Wind Speed",
      "Mean Wind Direction",
      "Turbulence Intensity",
      "Wind Shear"
    ]
    var contents = [
      ["1 month average - 91%", "Campaign average - 97%"],
      ["1 month average - 88%", "Campaign average - 88%"],
      ["something"],
      ["0"],
      ["0"],
      ["100%"],
      ["Slope - 1.00", "Coefficient of Determination - 1.00"],
      ["Slope - 1.00", "Coefficient of Determination - 1.00"],
      ["Slope - x", "Correlation Co-efficient - x"],
      ["Shear exponent - x"]
    ]
    var names = ["fas fa-check ok", "fas fa-bacon almost", "fas fa-times bad"]
    for (var i=0; i<10; i++) {
      cards[i] = {title: titles[i], content: contents[i], passingStyle: names[Math.floor(Math.random() * 3)]}
    }
    return cards
  }

  render() {
    return (
      <main>
        <div className="lidars-grid">
          {this.state.cards.map((card, i) => {       
            return (<Card id={i} title={card.title} content={card.content} passingStyle={card.passingStyle} togglePopup={this.openPopup}/>) 
          })}
        </div>
        {this.state.showPopup ?  
          <Popup cards={this.state.cards} togglePopup={this.togglePopup} kpiID={this.state.currentKPI}/>  
          : null  
        }  
      </main>
    );
  }
}

export default Dashboard
