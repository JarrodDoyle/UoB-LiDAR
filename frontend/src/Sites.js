import React from "react";
import { GoogleMap, withScriptjs, withGoogleMap, Marker } from 'react-google-maps';
import { Link } from "react-router-dom";
import { useSelector } from 'react-redux';
import { getSites } from './redux/selectors.js';
import turbine from "./res/turbine-clear-bold.gif";
import { 
  CardGrid,
  Card,
  CardHeaderFull,
  CardRow,
  CardFooter
} from "./Cards.js";
import { PercentageIndicator } from "./Indicators.js";
import "./Lidars.css";

function SiteCard(props) {
  return (
    <Card>
      <CardHeaderFull>
        <WrappedMap
          googleMapURL="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places&key=AIzaSyAuy0y-1edccXfqufhhq3JFUa0NCBtUzsE"
          loadingElement={<div style={{ height: `100%`, borderRadius: '10px 10px 0px 0px' }} />}
          containerElement={<div style={{ height: `100%`, borderRadius: '10px 10px 0px 0px' }} />}
          mapElement={<div style={{ height: `100%`, borderRadius: '10px 10px 0px 0px' }} />}
          location={props.location}
        />
      </CardHeaderFull>
      <CardRow>
        <h3>{props.name}</h3>
        <PercentageIndicator percentage={props.totalComplete}/>
      </CardRow>
      
      <p>{props.desc}</p>
      <CardFooter>
        <h4>Go to dash</h4>
        <Link className="circle-btn" to={`/app/Dashboard/${props.id}`}>
          <i className="fas fa-chevron-right"/>
        </Link>
      </CardFooter>
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

export default function Sites() {
  let sites = useSelector(getSites);
  return (
    <main className="lidars-wrapper">
      <CardGrid>
        {sites.map (card => {
          return(
            <SiteCard key={card.id} {...card}/>
          )
        })}
      </CardGrid>
      <Link to="/login" className="circle-btn lidars-add">
        <i className="fas fa-plus"/>
      </Link>
    </main>
  );
}
