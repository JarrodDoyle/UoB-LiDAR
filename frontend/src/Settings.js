import React, { useState } from "react";
//import { findByLabelText } from "@testing-library/react";
import { useSelector } from 'react-redux';
import { Formik, Form } from "formik";
import { getEmail, getMasterKey } from './redux/selectors.js';
import { MaterialText } from "./Material-Inp.js";

function CredsCard(props){
  const [success, setSuccess] = useState(false);
  const email = useSelector(getEmail).email;
  return (
    <div className="settings-card">
      <Formik
        initialValues={{ email: '', password: ''}}
        validateOnChange
        validateOnBlur
        validate={ values => {
          const errors = {};
          if (!values.email){
            errors.email = "Required";
          }else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)){
            errors.email = 'Invalid email address';
          }
          if (!values.password){
            errors.password = "Required";
          }
          return errors;
        }}
        onSubmit={(values, { setSubmitting }) => {
          setSubmitting(false);
          setSuccess(true);
        }}
      >
        {({ isSubmitting, isValidating }) => (
          <Form className="material-form">
            <h1>User Credentials</h1>
            <MaterialText type="email" name="email" label="Email" placeholder={email}/>  
            <MaterialText type="password" name="password" label="Password"/>
            <div className="elipticle-btn" style={{width: 170}}>
              <button type="submit">Submit <i className="fas fa-chevron-right"/></button>
            </div>
          </Form>
        )}
      </Formik>
      {success &&
        <p>Succesfully updated</p>
      }
    </div>
  );
}

function ApiCard(props){
  const [success, setSuccess] = useState(false);
  const masterKey = useSelector(getMasterKey).masterKey;
  return (
    <div className="settings-card">
      <Formik
        initialValues={{ email: '', }}
        validateOnChange
        validateOnBlur
        validate={ values => {
          const errors = {};

          return errors;
        }}
        onSubmit={(values, { setSubmitting }) => {
          setSubmitting(false);
          setSuccess(true);
        }}
      >
        {({ isSubmitting, isValidating }) => (
          <Form className="material-form">
            <h1>API Keys</h1>
            <h2>Master Key</h2>
            <p>{masterKey}</p>
            <div className="elipticle-btn" style={{width: 170}}>
              <h5>Submit <i className="fas fa-chevron-right"/></h5>
            </div>
          </Form>
        )}
      </Formik>
      {success &&
        <p>Succesfully udpated</p>
      }
    </div>
  );
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


export default function Settings(props) {
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
<<<<<<< HEAD

/* <main>
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
   </main> */
=======
>>>>>>> fontend
