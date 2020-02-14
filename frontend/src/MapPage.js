import React from 'react';
import { GoogleMap, withScriptjs, withGoogleMap, Marker } from 'react-google-maps';
import { connect } from 'react-redux';
import turbine from "./res/turbine-clear-bold.gif";

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
      {props.lidars.map (location => {
        return(
          <Marker 
            key = {location.id}
            position={location.location}
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
          />
        )
      }
    )}
    </GoogleMap>
  );
}

const WrappedMap = withScriptjs(withGoogleMap(Map))
function MapPage(props){
  return(
    <WrappedMap
      lidars = {props.lidars}
      googleMapURL="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places&key=AIzaSyAuy0y-1edccXfqufhhq3JFUa0NCBtUzsE"
      loadingElement={<div style={{ height: `100%` }} />}
      containerElement={<div style={{ height: `100%` }} />}
      mapElement={<div style={{ height: `100%` }} />}
      location={{lat: 0, lng: 0}}
    />
  );
}
export default connect(state => ({lidars: state.lidars}))(MapPage);
