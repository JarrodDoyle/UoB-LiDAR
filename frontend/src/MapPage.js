import React from 'react';
import { GoogleMap, withScriptjs, withGoogleMap, Marker, InfoWindow } from 'react-google-maps';
import { connect } from 'react-redux';
import { toggleSiteMapOpen } from './redux/actions.js';
import turbine from "./res/turbine-clear-bold.gif";

let SiteMarker = connect()((props) => {
  console.log(props.site.id);
  return (
    <Marker 
      key = {props.site.id}
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
      onClick={() => props.dispatch(toggleSiteMapOpen(props.site.id))}
    >
      {props.site.map_open && <p>
        {props.site.id} - {props.site.name}
      </p>}
    </Marker>
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
      {props.sites.map( site => {return(<SiteMarker site={site}/>)} )}
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
export default connect(state => ({sites: state.sites}))(MapPage);
