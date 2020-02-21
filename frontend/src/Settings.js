import React from "react";
import { Link } from "react-router-dom";
import { connect } from 'react-redux';
import { findByLabelText } from "@testing-library/react";

function eg() {
 return (
   <main>
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
   </main>
 );
}

function CredsCard(props) {
  return (
    <div className="settings-card">
       <h1>hello,</h1>
    </div>
  );
}

function ApiCard(props) {
  return (
    <div className="settings-card">
       <h1>is it me youre looking for?</h1>
    </div>
  )
}

function AdminSettingsCard(props) {
  return (
    <div className="settings-card">
       <h1>because after all these years</h1>
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