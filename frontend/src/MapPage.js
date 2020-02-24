import React from 'react';
import { GoogleMap, withScriptjs, withGoogleMap, Marker, InfoWindow } from 'react-google-maps';
import { connect } from 'react-redux';
import { toggleSiteMapOpen } from './redux/actions.js';
import { getSites } from './redux/selectors.js';
import turbine from "./res/turbine-clear-bold.gif";
import { Link } from "react-router-dom";

let SiteMarker = connect()((props) => {
  return (
    <>
      <Marker 
        position={props.site.location}
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
        }}
        anchorPoint={{
          x: 32,
          y: 0,
        }}
        shape={{
          coords: [0,0,64,64],
          type: "rect",
        }}
        title={props.site.name}
        onClick={() => props.dispatch(toggleSiteMapOpen(props.site.id))}
      />
      {props.site.map_open &&
        <InfoWindow
          zIndex={100}
          position={{
            lat: props.site.location.lat + 0.3,
            lng: props.site.location.lng,
          }}
          onCloseClick={() => props.dispatch(toggleSiteMapOpen(props.site.id))}
        >
            <div>
              <h2>{props.site.name}</h2>
              <span>{props.site.desc}</span>
              <div className="lidars-btns">
              <Link className="elipticle-btn" to="/Dashboard">
              <h5>Go to dash <i className="fas fa-chevron-right"/></h5>
              </Link>
            </div>
            </div>
        </InfoWindow> 
      }
    </>
  );
})

function Map(props) {
  return(
    <GoogleMap 
      defaultZoom={7}
      defaultCenter={{lat: 51.455916, lng: -2.603008}}
      defaultOptions={{
        fullscreenControl: false,
        mapTypeControl: true,
        streetViewControl: false,
        zoomControl: true,
        mapTypeId: "terrain",
      }}
    >
      {props.sites.map( site => {return(<SiteMarker key={site.id} site={site}/>)} )}
    </GoogleMap>
  );
}

const WrappedMap = withScriptjs(withGoogleMap(Map))
function MapPage(props){
  return(
    <WrappedMap
      sites = {props.sites}
      googleMapURL="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places&key=AIzaSyAuy0y-1edccXfqufhhq3JFUa0NCBtUzsE"
      loadingElement={<div style={{ height: `100%` }} />}
      containerElement={<div style={{ height: `100%` }} />}
      mapElement={<div style={{ height: `100%` }} />}
      location={{lat: 0, lng: 0}}
    />
  );
}
export default connect(getSites)(MapPage);
