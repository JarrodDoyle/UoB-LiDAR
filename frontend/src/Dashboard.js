import React from "react";
import { Link } from "react-router-dom";
import _ from "lodash";
import { Responsive, WidthProvider } from 'react-grid-layout';

const ResponsiveGridLayout = WidthProvider(Responsive);
const CardCount = 10;

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
    items: CardCount,
    onLayoutChange: function() {},
    cols: { lg: 3, md: 2, sm: 2, xs: 1, xxs: 1 },
    rowHeight: 175,
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
      compactType: "vertical", 
      mounted: false,
    }
  }

  componentDidMount() {
    this.setState({ mounted: true});
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

  onBreakpointChange(breakpoint) {
    this.setState({ currentBreakpoint: breakpoint });
  }

  onLayoutChange(layout, layouts) {
    this.props.onLayoutChange(layout, layouts);
  }

  render() {
    return (
      <ResponsiveGridLayout 
        className="layout" 
        layouts={this.state.layouts}
        onBreakPointChange={this.onBreakpointChange}
        onLayoutChange={this.onLayoutChange}
        measureBeforeMount={true}
        compactType={this.state.compactType}
        preventCollision={!this.state.compactType}
        {...this.props} 
      >
        {this.generateDOM()}
      </ResponsiveGridLayout>
    )
  }
}

function generateLayout() {
  return _.map(_.range(0, CardCount), function(item, i) {
    return {
      x: i % 3,
      y: Math.floor(i / 3),
      w: 1,
      h: 1,
      i: i.toString(),
    };
  });
}

function Dashboard(props) {
  return (
    <main>
      <CardGrid/>
    </main>
  );
}

export default Dashboard
