import React from "react";
import { Link } from "react-router-dom";
import _ from "lodash";
import GridLayout from 'react-grid-layout';

function Card(props) {
  return (
    <>
      <div className="card-content">
        <h3>{props.title} {props.cardID}</h3>
        <div className="kpi-indicator">
          <span>Passing</span>
        </div>
        <p className="text">{props.content}</p>
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
    items: 16,
    onLayoutChange: function() {},
    cols: 4,
    width: 1830-80,
    autoSize: true,
    isDraggable: false,
    isResizable: false,
  };

  constructor(props) {
    super(props)
    const layout = this.generateLayout()
    this.state = {
      layout,
    }
  }

  generateLayout() {
    const p = this.props;
    return _.map(new Array(p.items), function(item, i) {
      return {
        x: (i % 4),
        y: Math.floor(i / 4),
        w: 1,
        h: 1,
        i: i.toString()
      };
    });
  }

  generateDOM() {
    return _.map(_.range(this.props.items), function(i) {
      return (
        <div key={i}>
          <Card title="KPI" content="Epic card" page="/dashboard" cardID={i}/>
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
