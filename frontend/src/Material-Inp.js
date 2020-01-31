import React, {useState} from "react";

export function MaterialInput(props){
  const [val, setVal] = useState(0);
  return (
    <div className="Material-Inp-Group">
      <input type={props.type} name={props.name} required onBlur={(e) => setVal(e.target.value.length)}></input>
      <span className="highlight"></span>
      <span className="bar"></span>
      <label htmlFor={props.name} className={val > 0 ? "active" : ""}>{props.label}</label>
    </div>
  );
}
