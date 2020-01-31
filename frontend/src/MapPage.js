import React from 'react';
import { GoogleMap, withScriptjs, withGoogleMap, Marker } from 'react-google-maps';

function Map() {
    return(
      <GoogleMap 
        defaultZoom={7}
        defaultCenter={{lat: 0, lng: 0}}
        defaultOptions={{
          fullscreenControl: false,
          mapTypeControl: true,
          streetViewControl: false,
          zoomControl: true,
          mapTypeId: "terrain",
        }}
      >
        {locations.map (location => {
            return(
            <Marker position={location.location}/>
            )
        })}
        
      </GoogleMap>
  
      
    );
  }

  const WrappedMap = withScriptjs(withGoogleMap(Map))

  const locations = [
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

export default function MapPage(){
    return(
        <WrappedMap
          googleMapURL="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places&key=AIzaSyAuy0y-1edccXfqufhhq3JFUa0NCBtUzsE"
          loadingElement={<div style={{ height: `100%` }} />}
          containerElement={<div style={{ height: `100%` }} />}
          mapElement={<div style={{ height: `100%` }} />}
          location={{lat: 0, lng: 0}}
        />
    );
}