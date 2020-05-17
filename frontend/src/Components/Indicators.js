import React from "react";
import "./Indicators.css";

export function PercentageIndicator(props){
  let names = ["fas fa-check ok", "fas fa-bacon almost", "fas fa-times bad"]
  let style = names[props.percentage === 100 ? 0 : props.percentage >= 60 ? 1 : 2];
  return (
    <div {...props} className="percentage-indicator">
      <i className={style}></i>
    </div>
  );
}
