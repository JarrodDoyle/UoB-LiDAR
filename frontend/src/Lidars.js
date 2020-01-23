import React from "react";
import styles from './lidars.module.css';
import { GoogleMap, withScriptjs, withGoogleMap } from 'react-google-maps';
import { Link } from "react-router-dom";
import _ from "lodash";
import { Responsive, WidthProvider } from 'react-grid-layout';

const ResponsiveGridLayout = WidthProvider(Responsive);
const CardCount = 3;

function Card(props) {
  return (
    <>
      <div className="lidars-card-content">
        <h3>{props.title}</h3>
        <div>
          <div className="lidars-card-text">
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vitae orci vel enim pretium scelerisque. Pellentesque eleifend nec odio imperdiet pretium. Mauris ullamcorper mi erat.</p> 
          </div>
          <div className="lidars-card-map">
              <WrappedMap
              googleMapURL="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places&key=AIzaSyAuy0y-1edccXfqufhhq3JFUa0NCBtUzsE"
              loadingElement={<div style={{ height: `100%` }} />}
              containerElement={<div style={{ height: `100%` }} />}
              mapElement={<div style={{ height: `100%` }} />}
              />
          </div>
        </div>
      </div>    
      <div className="card-buttons">
        <Link to="/Dashboard">
          <span className="text">Dashboard</span>
        </Link>
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
        items: CardCount,
        onLayoutChange: function() {},
        cols: { lg: 1, md: 1, sm: 1, xs: 1, xxs: 1 },
        rowHeight: 300,
        autoSize: true,
        isDraggable: false,
        isResizable: false,
        initialLayout: generateLayout(),
  };

  constructor(props) {
    super(props)
    this.state = {
      layouts: { lg: this.props.initialLayout },
      cards: this.generateCards(),
      currentBreakpoint: "lg",
    }
    this.onBreakpointChange = this.onBreakpointChange.bind(this);
  }

  generateCards() {
    return [{title: "LiDAR Site 1"},{title: "LiDAR Site 2"},{title: "LiDAR Site 3"}]
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

  onBreakpointChange(breakpoint) {
    this.setState({ currentBreakpoint: breakpoint });
  }

  onLayoutChange(layout) {
    this.props.onLayoutChange(layout);
  }

  render() {
    return (
      <ResponsiveGridLayout 
        className="layout" 
        layouts={this.state.layouts}
        onBreakpointChange={this.onBreakpointChange}
        onLayoutChange={this.onLayoutChange}
        {...this.props} 
      >
        {this.generateDOM()}
      </ResponsiveGridLayout>
    )
  }
}

function generateLayout() {
  return _.map(new Array(CardCount), function(item, i) {
    return {
      x: 1,
      y: i,
      w: 1,
      h: 1,
      i: i.toString()
    };
  });
}

function LiDARS(props) {
    return (
    <main>
      <CardGrid/>
    </main>
  );
}

export default LiDARS