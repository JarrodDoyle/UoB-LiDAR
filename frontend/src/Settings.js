import React from "react";
import { Link } from "react-router-dom";
import { connect } from 'react-redux';
import { findByLabelText } from "@testing-library/react";
import {MaterialInput} from "./Material-Inp.js";



class CredsCard extends React.Component{
  constructor(props){
    super(props);
    this.state = {redirect: false};
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async handleSubmit(event){
    //Handle Submit
  }

  render() {
    return (
      <div className="settings-card">
        <form className="material-form" onSubmit={this.handleSubmit}>
          <h1>User Credentials</h1>
          <MaterialInput type="email" name="email" label="Email"/>
          <MaterialInput type="password" name="password" label="Password"/>
          <div className="elipticle-btn" style={{width: 170}}>
          <h5>Submit<i className="fas fa-chevron-right"/></h5>
          </div>
        </form>
      </div>
    );
   }
}

class ApiCard extends React.Component{
  constructor(props){
    super(props);
    this.state = {redirect: false};
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async handleSubmit(event){
    //Handle Submit
  }
  render() {
    return (
      <div className="settings-card">
        <form className="material-form" onSubmit={this.handleSubmit}>
          <h1>Api Credentials</h1>  
          <MaterialInput type="text" name="apikey" label="Google Maps API Key"/>
          <div className="elipticle-btn" style={{width: 170}}>
          <h5>Submit<i className="fas fa-chevron-right"/></h5>
          </div>
        </form>

      </div>
    );
   }
}

function AdminSettingsCard(props) {
  return (
    <div className="settings-card">
       <h1>Admin Settings</h1>
       <h3>Assign lidar to account</h3>
        <h3>Change user perms</h3>
        <h3>Edit lidar (need to add edit link to Lidars page) - with add lidar link</h3>
    </div>
  )
}


function Settings(props) {
  return (
  <main className="lidars-wrapper">
    <div className="settings-grid">
      <CredsCard></CredsCard>
      <ApiCard></ApiCard>
      <AdminSettingsCard></AdminSettingsCard>
      </div>
  </main>
  );
}
export default connect(state => ({sites: state.sites}))(Settings)

{/* <main>
    <section>
      <h2>Credentials</h2>
    </section>
    <section>
      <h2>API Tokens</h2>
    </section>
    <section>
      <h2>Admin settings</h2>
      <h3>Assign lidar to account</h3>
      <h3>Change user perms</h3>
      <h3>Edit lidar (need to add edit link to Lidars page) - with add lidar link</h3>
    </section>
   </main> */}