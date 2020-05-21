import React, { useState } from "react";
import { AutoSizer } from 'react-virtualized';
import { useParams } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { Line } from 'nivo';
import { getLidar, getKpis } from './redux/selectors.js';
import { 
  CardGrid,
  Card,
  CardHeaderFull,
  CardRow,
  CardFooter
} from "./Components/Cards.js";
import { PercentageIndicator } from "./Components/Indicators.js";
import "./Dashboard.css";
import { GoogleMap, withScriptjs, withGoogleMap, Marker } from 'react-google-maps';

import turbine from "./res/turbine-clear-bold.gif";
import { Divider } from "@material-ui/core";

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

/* class DashboardGridd extends React.Component {
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
} */

function KpiCard(props) {
  return (
    <Card>
      <CardRow>
        <h3>{props.name}</h3>
        <PercentageIndicator percentage={props.percentComplete}/> 
      </CardRow>
      <div className="dash-content">
        {props.data.map((data, i) => {   
          const cardView = data.cardview;
          console.log(cardView);
          if (cardView.type === "number"){
            return (
              <CardRow>
                <span>{cardView.text}</span>
                <span>{cardView.number}</span>
              </CardRow>
            )
          } else {
            return (<></>);
          }
          // TODO support other types  
        })}
      </div>
      <CardFooter>
        <h4>More details</h4>
        <button className="circle-btn" onClick={() => props.togglePopup(props.id)}>
          <i className="fas fa-chevron-right"/>
        </button>
      </CardFooter>
    </Card>
  );
}

function DashboardGrid(props){
  const [showPopup, setPopup] = useState(false);
  const kpis = useSelector((state) => getKpis(state, props.siteId));
  return (
    <>
      <CardGrid>
        {kpis.map((card, i) => {       
          return (
          <KpiCard id={i} {...card} togglePopup={() => setPopup(true)}/>
          ) 
        })}
      </CardGrid>
      {showPopup ?  
        <Popup 
          cards={kpis}
          closePopup={() => setPopup(false)}
          updatePopup={() => setPopup(true)}
          kpiID={"55"}
        />  
        : null  
      }  
    </>
  );
}

function SiteInfo(props){
  const site = useSelector(state => getLidar(state, props.siteId));  
  return (
    <Card>
      <CardHeaderFull>
        <WrappedMap
          googleMapURL="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places&key=AIzaSyAuy0y-1edccXfqufhhq3JFUa0NCBtUzsE"
          loadingElement={<div style={{ height: `100%`, borderRadius: '10px 10px 0px 0px' }} />}
          containerElement={<div style={{ height: `100%`, borderRadius: '10px 10px 0px 0px' }} />}
          mapElement={<div style={{ height: `100%`, borderRadius: '10px 10px 0px 0px' }} />}
          location={site.location}
        />
      </CardHeaderFull>
      <CardRow>
        <h3>{site.name}</h3>
        <PercentageIndicator percentage={site.totalComplete}/>
      </CardRow>
      
      <p>{site.desc}</p>
    </Card>
  );
}

function Map(props) {
  return(
    <GoogleMap 
      defaultZoom={6}
      defaultCenter={props.location}
      defaultOptions={{
        fullscreenControl: false,
        mapTypeControl: false,
        streetViewControl: false,
        zoomControl: false,
        panControl: false,
        draggable: false,
        draggableCursor: "default",
        mapTypeId: "terrain",
        minZoom: 6,
        maxZoom: 6,
        gestureHandling: "none",
        styles: [
          {
            featureType: 'road',
            elementType: 'geometry',
            stylers: [{visibility: 'off'}]
          },
          {
            featureType: 'road',
            elementType: 'labels',
            stylers: [{visibility: 'off'}]
          },
        ],
      }}
    >
      <Marker
        position={props.location}
        icon={{
          url: turbine,
            size:{
              width: 256,
              height: 256,
              widthUnit: "px",
              heightUnit: "px"
            },
            scaledSize:{
              width: 64,
              height: 64,
              widthUnit: "px",
              heightUnit: "px",
            },
            anchor: {
              x: 32,
              y: 64,
            }
          }
        }
      />
    </GoogleMap>
  );
}
const WrappedMap = withScriptjs(withGoogleMap(Map))

export default function Dashboard(){
  let { siteId } = useParams();
  return (
    <main>
      <SiteInfo siteId={siteId}/>
      <h2>Key Performance Indicators:</h2>
      <Divider></Divider>
      <DashboardGrid siteId={siteId}/>
    </main>
  );   
}
