import React from "react";
import styles from './lidars.module.css';
import { GoogleMap, withScriptjs, withGoogleMap } from 'react-google-maps';
import { Link } from "react-router-dom";
import "./Lidars.css";

const ResponsiveGridLayout = WidthProvider(Responsive);
const CardCount = 3;

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
      <p>This is a description</p>
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
      defaultZoom={10}
      defaultCenter={{lat:50.780519, lng: -0.152488}}
    />
  );
}

const WrappedMap = withScriptjs(withGoogleMap(Map))

function LiDARS(props) {
  return (
  <main className="lidars-grid">
    <Card title="Lidar 1"/>
    <Card title="Lidar 2"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
    <Card title="Lidar 3"/>
  </main>
  );
}

export default LiDARS
