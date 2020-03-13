import React, { useState } from "react";
import { AutoSizer } from 'react-virtualized';
import { useParams } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { Line } from 'nivo';
import { getSite, getKpis } from './redux/selectors.js';
import "./Dashboard.css";

// Placedholder data for graphs
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
  var kpiTitles = []
  for (var i=0; i<props.cards.length; i++) {
    kpiTitles.push(props.cards[i].title)
  }

  return (
    <div className="popup-background">
      <div className="popup-box">
        <div className="popup-header">
          <select className="kpi-dropdown" value={props.kpiID} onChange={
            (event) => {
              // console.log(event.target.value)
              props.updatePopup(event.target.value); // Updates the popup with the new KPI id and rerenders.
            }
          }>
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
            <button className="circle-btn" onClick={() => props.closePopup(null)}>
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

class DashboardGridd extends React.Component {
  constructor(props) {
    super(props);
    this.closePopup = this.closePopup.bind(this);
    this.openPopup = this.openPopup.bind(this);
    this.generateCards = this.generateCards.bind(this);
    this.state = {
      showPopup: false,
      cards: this.generateCards(),
      currentKPI: null,
    };
    
  }

  closePopup() {
    this.setState({
      showPopup: false,
    });
  }

  openPopup(kpiID) {
    this.setState({
      showPopup: true,
    });
    this.setState( {
      currentKPI: kpiID,
    });
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
      <>
        <div className="lidars-grid">
          {this.state.cards.map((card, i) => {       
            return (
              <Card id={i}
                title={card.title}
                content={card.content}
                passingStyle={card.passingStyle}
                togglePopup={this.openPopup}
                />
              ) 
          })}
        </div>
        {this.state.showPopup ?  
          <Popup cards={this.state.cards} 
            closePopup={this.closePopup}
            updatePopup={this.openPopup}
            kpiID={this.state.currentKPI}/>  
          : null  
        }  
      </>
    );
  }
}

function Card(props) {
  const parcingIcons = ["fas fa-check ok", "fas fa-bacon almost", "fas fa-times bad"];
  const parcingStyle = parcingIcons[0];
  return (
    <div className="lidars-card">
      <div className="kpi-indicator">
        <h3>{props.name}</h3>
        <i className={parcingStyle}></i>
      </div>
      <div className="dash-content">
        {props.data.map((data, i) => {   
          const cardView = data.cardview;
          console.log(cardView);
          if (cardView.type === "number"){
            // TODO change this to new card style
            return (
              <p>{cardView.text}{cardView.number}</p>
            )
          }
          // TODO support other types  
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

function DashboardGrid(props){
  const [showPopup, setPopup] = useState(false);
  const kpis = useSelector((state) => getKpis(state, props.siteId));
  return (
    <>
      <div className="lidars-grid">
        {kpis.map((card, i) => {       
          return (
          <Card id={i} {...card} togglePopup={() => setPopup(true)}/>
          ) 
        })}
      </div>
      {showPopup ?  
        <Popup 
          cards={this.state.cards}
          closePopup={() => setPopup(false)}
          updatePopup={this.openPopup}
          kpiID={this.state.currentKPI}
        />  
        : null  
      }  
    </>
  );
}

function SiteInfo(props){
  let site = useSelector(state => getSite(state, props.siteId));  
  return (
    <section>
      <h2>{site.name}</h2>
      <p>Description: {site.desc}</p>
      <p>Percentage complete: {site.totalComplete}</p>
    </section>
  );
}

export default function Dashboard(){
  let { siteId } = useParams();
  return (
    <main>
      <SiteInfo siteId={siteId}/>
      <DashboardGrid siteId={siteId}/>
    </main>
  );   
}
