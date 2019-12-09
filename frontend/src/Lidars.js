import React from "react";

function Lidar(props){
  return (
    <li>
      <span>{props.name}</span>
      <span>............</span>
      <span>{props.loc}</span>
    </li>
  );
}

export default function Lidars() {
  return (
    <ul>
      <Lidar name="LiDAR 1" loc="USA"/>
      <Lidar name="LiDAR 2" loc="UK"/>
      <Lidar name="LiDAR 3" loc="Japan"/>
    </ul>
  );
}
