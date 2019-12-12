import React from "react";
import {
  Card, CardImg, CardText, CardBody,
  CardTitle, CardSubtitle, Button, Container
} from 'reactstrap';
import styles from './lidars.module.css';
import { Map, GoogleApiWrapper } from 'google-maps-react';

function Lidar(props){
  return (
    <li>
      <span>{props.name}</span>
      <span>............</span>
      <span>{props.loc}</span>
    </li>
  );
}

export default function Lidars() {
    return (
        <div>
        <h1>Home</h1>
            <div className={styles.container}>
        
                <div id={styles.content}>
                <h2>Site 1</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vitae orci vel enim pretium scelerisque. Pellentesque eleifend nec odio imperdiet pretium. Mauris ullamcorper mi erat.</p> 
                </div>
                
                <div id={styles.map}>
                map
                </div>
        
            </div>
        
            <div className={styles.container}>
        
                <div id={styles.content}>
                <h2>Site 2</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vitae orci vel enim pretium scelerisque. Pellentesque eleifend nec odio imperdiet pretium. Mauris ullamcorper mi erat.</p> 
                </div>
        
                <div id={styles.map}>
                map
                </div>
            </div>
        
            <div className={styles.container}>
        
                <div id={styles.content}>
                <h2>Site 3</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vitae orci vel enim pretium scelerisque. Pellentesque eleifend nec odio imperdiet pretium. Mauris ullamcorper mi erat.</p> 
                </div>
        
                <div id={styles.map}>
                map
                </div>
            </div>
        </div>
  )
};


