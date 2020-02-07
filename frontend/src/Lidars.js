import React from "react";
import { GoogleMap, withScriptjs, withGoogleMap, Marker } from 'react-google-maps';
import { Link } from "react-router-dom";
import turbine from "./res/turbine-clear-bold.gif";
import "./Lidars.css";

function Card(props) {
  return (
    <div className="lidars-card">
      <div className="lidars-card-map">
        <WrappedMap
          googleMapURL="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places&key=AIzaSyAuy0y-1edccXfqufhhq3JFUa0NCBtUzsE"
          loadingElement={<div style={{ height: `100%`, borderRadius: '10px' }} />}
          containerElement={<div style={{ height: `100%`, borderRadius: '10px' }} />}
          mapElement={<div style={{ height: `100%`, borderRadius: '10px' }} />}
          location={props.location}
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

const cards = [
  {
    title: "Brighton Off-Shore 1",
    desc: "This is an offshore windfarm 1 This is an offshore windfarm 1 This is an offshore windfarm 1 This is an offshore windfarm 1",
    location: {lat: 50.643758,lng: -0.257144}
  },
  {
    title: "Brighton Off-Shore 2",
    desc: "This is an offshore windfarm 2",
    location: {lat: 53.852400,lng: -3.697895}
  },
  {
    title: "North Sea Site 1",
    desc: "This is an offshore windfarm 3",
    location: {lat: 53.415865,lng: 0.689438}
  },
  {
    title: "North Sea Site 2",
    desc: "This is an offshore windfarm 4",
    location: {lat: 50.510669,lng: -2.240459}
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
    <Link to="/login" className="circle-btn lidars-add">
      <i className="fas fa-plus"/>
    </Link>
  </main>
  );
}

export default LiDARS
