import React, { useState } from "react";
import './Tracking.css';

export default function Tracking(){
  const [closed, setClosed] = useState(false);
  if (closed) {
    return null;
  }else {
    return (
      <div className="tracking">
        <button className="tracking-close" onClick={() => setClosed(true)}><i className="fas fa-times"/></button>
        <h3>A side note...</h3>
        <p>
          We're using <a href="https://hotjar.com">hotjar</a> to give us quantitive data to allow us to justify meeting our targets for the project. This means we're collecting anonymised data. Please also fill out the survay. Thanks 💜💜💜
        </p>
      </div>
    );
  }
}
