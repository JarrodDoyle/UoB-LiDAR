import React from "react";
import { Link } from "react-router-dom";
import _ from "lodash";
import GridLayout from 'react-grid-layout';

function Card(props) {
  return (
    <div class="dash-card">
      <h3>{props.title} {props.cardID}</h3>
      <p>{props.content}</p>
      <Link to={props.page}>
        <span>More details</span>
      </Link>
    </div>
  );
}

class CardGrid extends React.Component {
  static defaultProps = {
    className: "layout",
    items: 16,
    rowHeight: 30,
    onLayoutChange: function() {},
    cols: 4,
    width: 1830,
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
        y: Math.floor(i / 4) * 3,
        w: 1,
        h: 3,
        i: i.toString()
      };
    });
  }

  generateDOM() {
    return _.map(_.range(this.props.items), function(i) {
      return (
        <div key={i}>
          {/* <span className="text">Card number {i1}</span> */}
          <Card key={i} title="Card" content="Epic card" page="/card-details" cardID={i}/>

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
    <>
    <div>
      <h2>Dashboard</h2>
    </div>
    <div>
      <CardGrid/>
    </div>
    </>
  );
}

export default Dashboard
