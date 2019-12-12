import React from "react";
import _ from "lodash";
import GridLayout from 'react-grid-layout';

class CardGrid extends React.Component {
  static defaultProps = {
    className: "layout",
    items: 16,
    rowHeight: 30,
    onLayoutChange: function() {},
    cols: 4,
    width: 1800,
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
          <span className="text">Card number {i}</span>
        </div>
      );
    });
  }

  onLayoutChange(layout) {
    this.props.onLayoutChange(layout);
  }

  render() {
    // layout is an array of objects, see the demo for more complete usage
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
