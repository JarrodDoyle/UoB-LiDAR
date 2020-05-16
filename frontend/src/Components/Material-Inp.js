import React, {useState} from "react";
import { Field, ErrorMessage } from "formik";

export function MaterialInput(props){
  const [val, setVal] = useState(0);
  return (
    <div className="Material-Inp-Group">
      <input type={props.type} name={props.name} required onBlur={(e) => setVal(e.target.value.length)} placeholder={props.placeholder}></input>
      <span className="highlight"></span>
      <span className="bar"></span>
      <label htmlFor={props.name} className={val > 0 ? "active" : ""}>{props.label}</label>
    </div>
  );
}

export function MaterialText(props){
  const [val, setVal] = useState(0);
  return (
    <div className="Material-Inp-Group">
      <Field {...props} onBlur={(e) => setVal(e.target.value.length)}></Field>
      <span className="highlight"></span>
      <span className="bar"></span>
      <label htmlFor={props.name} className={val > 0 ? "active" : ""}>{props.label}</label>
      <ErrorMessage name={props.name}/>
    </div>
  );
}

export function Selector(props) {
  return (
    <div>
      <label htmlFor={props.name}>{props.label}</label>
      <select {...props}>
        {props.values.map(i =>
          <option value={i}>{i}</option>
        )}
      </select>
    </div>
  );
}
