import React, { useState } from "react";
//import { findByLabelText } from "@testing-library/react";
import { useSelector } from 'react-redux';
import { Formik, Form } from "formik";
import { getEmail, getMasterKey } from './redux/selectors.js';
import { MaterialText } from "./Components/Material-Inp.js";
import { CardColumn, Card } from "./Components/Cards.js";

function CredsCard(props){
  const [success, setSuccess] = useState(false);
  const email = useSelector(getEmail);
  return (
    <Card>
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
    </Card>
  );
}

function ApiCard(props){
  const [success, setSuccess] = useState(false);
  const masterKey = useSelector(getMasterKey);
  return (
    <Card>
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
    </Card>
  );
}

function AdminSettingsCard(props) {
  return (
    <Card>
      <h1>Admin Settings</h1>
      <h3>Assign lidar to account</h3>
      <h3>Change user perms</h3>
      <h3>Edit lidar (need to add edit link to Lidars page) - with add lidar link</h3>
    </Card>
  )
}


export default function Settings(props) {
  return (
  <main>
    <CardColumn>
      <CredsCard></CredsCard>
      <ApiCard></ApiCard>
      <AdminSettingsCard></AdminSettingsCard>
    </CardColumn>
  </main>
  );
}
