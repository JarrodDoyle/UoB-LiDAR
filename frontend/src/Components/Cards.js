import React from "react";
import "./Cards.css";

export function CardGrid(props){
  return (
    <div {...props} className="card-grid">
      {props.children}
    </div>
  );
}

export function CardColumn(props){
  return (
    <div {...props} className="card-column">
      {props.children}
    </div>
  );
}

export function Card(props){
  return (
    <div {...props} className="card">
      {props.children}
    </div>
  );
}

export function CardHeaderFull(props){
  return (
    <div {...props} className="card-header-full">
      {props.children}
    </div>
  );
}

export function CardRow(props){
  return (
    <div {...props} className="card-row">
      {props.children}
    </div>
  );
}

export function CardFooter(props){
  return (
    <div {...props} className="card-footer">
      {props.children}
    </div>
  );
}
