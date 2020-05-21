import React, { useState, useEffect } from "react";
import { AutoSizer } from 'react-virtualized';
import { useParams } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { Line } from 'nivo';
import {
  getMasterKey,
  getLidar,
  getKpis,
  isKpisFetching,
  isKpisError,
} from './redux/selectors.js';
import { fetchKPIs } from './redux/actions.js';
import {
  CardGrid,
  Card,
  CardHeaderFull,
  CardRow,
  CardFooter
} from "./Components/Cards.js";
import { PercentageIndicator } from "./Components/Indicators.js";
import "./Dashboard.css";
import { GoogleMap, withScriptjs, withGoogleMap, Marker } from 'react-google-maps';

import turbine from "./res/turbine-clear-bold.gif";
import { Divider } from "@material-ui/core";
function Popup(props) {
  var kpiTitles = []
  var kpiIdx = props.kpiID;
  for (var i = 0; i < props.cards.length; i++) {
    kpiTitles.push(props.cards[i].name)
  }

  return (
    <div className="popup-background">
      <div className="popup-box">
        <div className="popup-header">
          <CardRow>
            <h2>KPI:</h2>
            <select value={kpiIdx} onChange={
              (event) => {
                props.updatePopup(event.target.value);
              }
            }>
              {kpiTitles.map((kpi, i) => {
                return (<option value={i}>{kpi}</option>)
              })}
            </select>
            <PercentageIndicator percentage={props.percentComplete} />
          </CardRow>
          <CardRow>
            <h2>Timeframe:</h2>
            <select value={props.timeframe} onChange={
              (event) => {
                props.updateTimeframe(event.target.value);
              }
            }>
              <option value={0}>Week</option>
              <option value={1}>Month</option>
              <option value={2}>6 Months</option>
              <option value={3}>Year</option>
            </select>
            <div className="lidars-btns">
              <button className="circle-btn" onClick={() => props.closePopup(null)}>
                <i className="fas fa-times" />
              </button>
            </div>
          </CardRow>
        </div>
        <div className="popup-grid">
          {props.data.map((data, i) => {
            const detailedView = data.detailedview;
            console.log(detailedView);
            if (detailedView.type === "graph") {
              return (
                <Graph data={detailedView.graphData} type={detailedView.graphType} />
              )
            }
            return null;
          })}
        </div>
      </div >
    </div >
  )
}

function Graph(props) {
  let commonProps = {
    margin: { top: 40, right: 40, bottom: 40, left: 40 },
    animate: true,
    axisLeft: {
      orient: 'left',
      legend: 'y-axis',
      legendPosition: 'middle',
      legendOffset: -32,
      tickSize: 5,
      tickPadding: 5,
      tickRotation: 0,
    },
    axisBottom: {
      orient: 'left',
      legend: 'x-axis',
      legendPosition: 'middle',
      legendOffset: 32,
      tickSize: 5,
      tickPadding: 15,
      tickRotation: 0,
    },
  }
  return (
    <div>
      <AutoSizer>
        {({ height, width }) => {
          if (props.type === "line") {
            return (
              <Line
                {...commonProps}
                data={props.data}
                height={height}
                width={width}
                enableSlices='x'
                enableArea={true}
                enableCrosshair={true}
              />
            )
          }
        }
        }
      </AutoSizer>
    </div>
  )
}

function KpiCard(props) {
  return (
    <Card>
      <CardRow>
        <h3>{props.name}</h3>
        <PercentageIndicator percentage={props.percentComplete} />
      </CardRow>
      <div className="dash-content">
        {props.data.map((data, i) => {
          const cardView = data.cardview;
          if (cardView.type === "numeric"){
            return (
              <CardRow>
                <span>{cardView.text}</span>
                <span>{cardView.data}</span>
              </CardRow>
            )
          } else {
            return (<></>);
          }
          return null
        })}
      </div>
      <CardFooter>
        <h4>More details</h4>
        <button className="circle-btn" onClick={() => props.togglePopup(props.id)}>
          <i className="fas fa-chevron-right" />
        </button>
      </CardFooter>
    </Card>
  );
}

function DashboardGrid(props) {
  const [showPopup, setPopup] = useState(false);
  const [currentKPI, setCurrentKPI] = useState(0);
  const [currentTimeframe, setTimeframe] = useState(0);
  const kpis = useSelector(getKpis);
  const fetching = useSelector(isKpisFetching);
  const error = useSelector(isKpisError);
  const token = useSelector(getMasterKey);
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(fetchKPIs({
    token: token,
    id: props.siteId,
  }))}, [props.siteId, token, dispatch]);
  if (fetching)
    return (<p>Loading...</p>);
  if (error)
    return (<p>There wasn an error. Please try again later.</p>);
  return (
    <>
      <CardGrid>
        {kpis.map((card, i) => {
          return (
            <KpiCard key={i} {...card} id={i} togglePopup={(kpiID) => {
              setPopup(true)
              setCurrentKPI(kpiID)
            }} />
          )
        })}
      </CardGrid>
      {showPopup ?
        <Popup
          cards={kpis}
          closePopup={() => { setPopup(false); setTimeframe(0) }}
          updatePopup={(kpiID) => setCurrentKPI(kpiID)}
          kpiID={currentKPI}
          timeframe={currentTimeframe}
          updateTimeframe={(a) => setTimeframe(a)}
          percentComplete={kpis[currentKPI].percentComplete}
          data={kpis[currentKPI].data}
        />
        : null
      }
    </>
  );
}

function SiteInfo(props){
  const site = useSelector(state => getLidar(state, props.siteId));
  return (
    <Card>
      <CardHeaderFull>
        <WrappedMap
          googleMapURL="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places&key=AIzaSyAuy0y-1edccXfqufhhq3JFUa0NCBtUzsE"
          loadingElement={<div style={{ height: `100%`, borderRadius: '10px 10px 0px 0px' }} />}
          containerElement={<div style={{ height: `100%`, borderRadius: '10px 10px 0px 0px' }} />}
          mapElement={<div style={{ height: `100%`, borderRadius: '10px 10px 0px 0px' }} />}
          location={site.location}
        />
      </CardHeaderFull>
      <CardRow>
        <h3>{site.name}</h3>
        <PercentageIndicator percentage={site.totalComplete} />
      </CardRow>
      <p>{site.desc}</p>
    </Card>
  );
}

function Map(props) {
  return (
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
            stylers: [{ visibility: 'off' }]
          },
          {
            featureType: 'road',
            elementType: 'labels',
            stylers: [{ visibility: 'off' }]
          },
        ],
      }}
    >
      <Marker
        position={props.location}
        icon={{
          url: turbine,
          size: {
            width: 256,
            height: 256,
            widthUnit: "px",
            heightUnit: "px"
          },
          scaledSize: {
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

export default function Dashboard() {
  let { siteId } = useParams();
  return (
    <main>
      <SiteInfo siteId={Number(siteId)}/>
      <h2>Key Performance Indicators:</h2>
      <Divider></Divider>
      <DashboardGrid siteId={Number(siteId)}/>
    </main>
  );
}
