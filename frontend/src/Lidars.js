import React from "react";
import styles from './lidars.module.css';
import { GoogleMap, withScriptjs, withGoogleMap } from 'react-google-maps';
import { Link } from "react-router-dom";
import _ from "lodash";
import GridLayout from 'react-grid-layout';

function Card(props) {
  return (
  <>
    <div className="lidars-card-content">
    <h3>{props.title}</h3>
    <div className="lidars-card-text">
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vitae orci vel enim pretium scelerisque. Pellentesque eleifend nec odio imperdiet pretium. Mauris ullamcorper mi erat.</p> 
      <div className="card-buttons">
        <Link to="/Dashboard">
          <span className="text">Dashboard</span>
        </Link>
      </div>
    </div>
    <div className="lidars-card-map">
      <WrappedMap
      googleMapURL="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places&key=AIzaSyAuy0y-1edccXfqufhhq3JFUa0NCBtUzsE"
      loadingElement={<div style={{ height: `100%` }} />}
      containerElement={<div style={{ height: `400px` }} />}
      mapElement={<div style={{ height: `100%` }} />}
      />
    </div>
    </div>  
    
  </>
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

class CardGrid extends React.Component {
  static defaultProps = {
    className: "layout",
    items: 3,
    onLayoutChange: function() {},
    cols: 1,
    rows: 3,
    rowHeight: 175,
    width: 1830-80,
    autoSize: true,
    isDraggable: false,
    isResizable: false,
  };

  constructor(props) {
    super(props)
    this.state = {
      layout: this.generateLayout(),
      cards: this.generateCards(),
    }
  }
  
  generateCards() {
    return [{title: "LiDAR Site"},{title: "LiDAR Site"},{title: "LiDAR Site"}]
  }

  generateLayout() {
  const p = this.props;
  return _.map(new Array(p.items), function(item, i) {
    return {
    x: (i % p.cols),
    y: Math.floor(i / p.cols),
    w: 1,
    h: 1,
    i: i.toString()
    };
  });
  }

  generateDOM() {
  var cards = this.state.cards
  return _.map(_.range(this.props.items), function(i) {
    return (
    <div key={i}>
      <Card 
      title={cards[i].title}
      content={"random"}
      page="#" 
      cardID={i}
      />
    </div>
    );
  });
  }

  onLayoutChange(layout) {
  this.props.onLayoutChange(layout);
  }

  render() {
    return (
      <GridLayout 
      className="layout" 
      layout={this.state.layout}
      onLayoutChange={this.onLayoutChange}
      {...this.props} 
      >
      {this.generateDOM()}
      </GridLayout>
    )
    }

}

function LiDARS(props) {
  return (
  <main>
    <CardGrid/>
  </main>
  );
}

export default LiDARS

function Lidars() {
  return (
    <main>
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
    </main>
  )
};


