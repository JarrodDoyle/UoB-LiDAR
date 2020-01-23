import React from "react";

export function MaterialInput(props){
  console.log(props.required);
  return (
    <div className="Material-Inp-Group">
      <input type={props.type} name={props.name} required></input>
      <span className="highlight"></span>
      <span className="bar"></span>
      <label htmlFor={props.name}>{props.label}</label>
    </div>
  );
}
