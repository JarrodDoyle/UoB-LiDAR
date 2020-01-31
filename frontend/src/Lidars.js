import React from "react";
import { GoogleMap, withScriptjs, withGoogleMap } from 'react-google-maps';
import { Link } from "react-router-dom";
import "./Lidars.css";

function Card(props) {
  return (
    <div className="lidars-card">
      <div className="lidars-card-map">
        <WrappedMap
          googleMapURL="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places&key=AIzaSyAuy0y-1edccXfqufhhq3JFUa0NCBtUzsE"
          loadingElement={<div style={{ height: `100%` }} />}
          containerElement={<div style={{ height: `100%` }} />}
          mapElement={<div style={{ height: `100%` }} />}
          
        />
      </div>
      <h3>{props.title}</h3>
      <p>{props.desc}</p>
      <div className="lidars-btns">
        <h4>Go to dash</h4>
        <Link className="circle-btn" to="/Dashboard">
          <i className="fas fa-chevron-right"/>
        </Link>
      </div>
    </div>  
  );
}

function Map() {
  return(
    <GoogleMap 
      defaultZoom={7}
      defaultCenter={{lat:50.780519, lng: -0.152488}}
      defaultOptions={{
          fullscreenControl: false,
          mapTypeControl: false,
          streetViewControl: false,
          zoomControl: false,
          mapTypeId: "terrain",
          minZoom: 7,
          maxZoom: 7,
          gestureHandling: "none",
        }
      }
    />

    
  );
}

const WrappedMap = withScriptjs(withGoogleMap(Map))
const cards = [
  {
    title: "Brighton Off-Shore 1",
    desc: "This is an offshore windfarm 1 This is an offshore windfarm 1 This is an offshore windfarm 1 This is an offshore windfarm 1",
    location: [-1.45342,1.7834]
  },
  {
    title: "Brighton Off-Shore 2",
    desc: "This is an offshore windfarm 2",
    location: [-1.45342,1.7834]
  },
  {
    title: "Brighton Off-Shore 3",
    desc: "This is an offshore windfarm 3",
    location: [-1.45342,1.7834]
  },
  {
    title: "Brighton Off-Shore 4",
    desc: "This is an offshore windfarm 4",
    location: [-1.45342,1.7834]
  }
];
function LiDARS(props) {
  return (
  <main className="lidars-wrapper">
    <div className="lidars-grid">
      {cards.map (card => {
        return(
          <Card title={card.title}  desc={card.desc} location={card.location}/>
        )
      })}
    </div>
  </main>
  );
}

export default LiDARS
