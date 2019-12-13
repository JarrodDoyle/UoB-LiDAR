import React from "react";
import { Link } from "react-router-dom";
import _ from "lodash";
import GridLayout from 'react-grid-layout';

function Card(props) {
  return (
    <>
      <div className="card-content">
        <h3>{props.title}</h3>
        <div className="kpi-indicator">
          <span>Passing</span>
        </div>
        {props.content.map((line, i) => {       
           return (<p className="text">{line}</p>) 
        })}
      </div>
      <div className="card-buttons">
        <Link to={props.page}>
          <span className="text">More details</span>
        </Link>
      </div>
    </>
  );
}

class CardGrid extends React.Component {
  static defaultProps = {
    className: "layout",
    items: 10,
    onLayoutChange: function() {},
    cols: 3,
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
    return [
      {title: "System Availability", content: ["1 month average - 91%", "Campaign average - 97%"]},
      {title: "Post Processed Data Availability", content: ["1 month average - 88%", "Campaign average - 88%"]},
      {title: "Data Coverage", content: ["something"]},
      {title: "Number of Maintenance Visits", content: ["0"]},
      {title: "Number of Unscheduled Outages", content: ["0"]},
      {title: "Uptime of Communication System", content: ["100%"]},
      {title: "Mean Wind Speed", content: ["Slope - 1.00", "Coefficient of Determination - 1.00"]},
      {title: "Mean Wind Direction", content: ["Slope - 1.00", "Coefficient of Determination - 1.00"]},
      {title: "Turbulence Intensity", content: ["Slope - x", "Correlation Co-efficient - x"]},
      {title: "Wind Shear", content: ["Shear exponent - x"]},
    ]
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
            content={cards[i].content}
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

function Dashboard(props) {
  return (
    <div className="page-body">
      <h2>Dashboard</h2>
      <hr className="hr"></hr>
      <CardGrid/>
    </div>
  );
}

export default Dashboard
