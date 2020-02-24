import React, { useState } from "react";
import { Formik, Form } from "formik";
import { Switch, Redirect, Route, Link } from "react-router-dom";
import { useDispatch } from 'react-redux';
import { MaterialInput, MaterialText } from './Material-Inp.js';
import { setEmail, setMasterApiKey } from './redux/actions.js';
import Tracking from './Tracking.js';
import i0 from './res/login-bg0.jpg';
import i1 from './res/login-bg1.jpg';
import i2 from './res/login-bg2.jpg';
import i3 from './res/login-bg3.jpg';
import i4 from './res/login-bg4.jpg';
import i5 from './res/login-bg5.jpg';
import i6 from './res/login-bg6.jpg';
import i7 from './res/login-bg7.jpg';
import i8 from './res/login-bg8.jpg';
import i9 from './res/login-bg9.jpg';
import i10 from './res/login-bg10.jpg';
import i11 from './res/login-bg11.jpg';
import i12 from './res/login-bg12.jpg';
import i13 from './res/login-bg13.jpg';
import i14 from './res/login-bg14.jpg';
import i15 from './res/login-bg15.jpg';
import i16 from './res/login-bg16.jpg';
import i17 from './res/login-bg17.jpg';
import i18 from './res/login-bg18.jpg';
import i19 from './res/login-bg19.jpg';
import i20 from './res/login-bg20.jpg';
import i21 from './res/login-bg21.jpg';
import i22 from './res/login-bg22.jpg';
import i23 from './res/login-bg23.jpg';
import i24 from './res/login-bg24.jpg';
import i25 from './res/login-bg25.jpg';
import i26 from './res/login-bg26.jpg';
import i27 from './res/login-bg27.jpg';
import i28 from './res/login-bg28.jpg';
import i29 from './res/login-bg29.jpg';
import i30 from './res/login-bg30.jpg';
import i31 from './res/login-bg31.jpg';
import i32 from './res/login-bg32.jpg';
import i33 from './res/login-bg33.jpg';
import i34 from './res/login-bg34.jpg';
import i35 from './res/login-bg35.jpg';
import i36 from './res/login-bg36.jpg';
import i37 from './res/login-bg37.jpg';
import i38 from './res/login-bg38.jpg';
import i39 from './res/login-bg39.jpg';
import i40 from './res/login-bg40.jpg';
import i41 from './res/login-bg41.jpg';
import i42 from './res/login-bg42.jpg';
import i43 from './res/login-bg43.jpg';
import i44 from './res/login-bg44.jpg';
import i45 from './res/login-bg45.jpg';
import i46 from './res/login-bg46.jpg';
import i47 from './res/login-bg47.jpg';
import i48 from './res/login-bg48.jpg';
import i49 from './res/login-bg49.jpg';
import i50 from './res/login-bg50.jpg';
import i51 from './res/login-bg51.jpg';
import i52 from './res/login-bg52.jpg';
import i53 from './res/login-bg53.jpg';
import i54 from './res/login-bg54.jpg';
import i55 from './res/login-bg55.jpg';
import i56 from './res/login-bg56.jpg';
import i57 from './res/login-bg57.jpg';
import i58 from './res/login-bg58.jpg';
import i59 from './res/login-bg59.jpg';
import "./Login.css";

function LoginForm(props){
  const [redirect, setRedirect] = useState(false);
  const dispatch = useDispatch();
  if (redirect){
    return (<Redirect to="/Sites"/>);
  }else{
    return (
      <Formik
        initialValues={{ email: '', password: '', }}
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
          dispatch(setEmail(values.email));
          dispatch(setMasterApiKey("I_am_the_master"));
          setRedirect(true);
        }}
      >
        {({ isSubmitting, isValidating }) => (
          <Form className="Login" method="post">
            <h2>LiDAR {isValidating}</h2>
            <MaterialText type="email" name="email" label="Email"/>
            <MaterialText type="password" name="password" label="Password" required/>
            <div>
              <h3>Login</h3>
              <button type="submit" disabled={isSubmitting} className="circle-btn"><i className="fas fa-chevron-right"/></button>
            </div>
            <div>
              <Link to="/login/register">Create account</Link>
              <Link to="/login/forgot">Forgot password</Link>
            </div>
          </Form>
        )}
      </Formik>
    );
  }
}

function RegistrationForm(props){
  const [redirect, setRedirect] = useState(false);
  if (redirect){
    return (<Redirect to="/Login"/>);
  }else{
    return (
      <Formik
        initialValues={{ email: '', password: '', passwordr: '', }}
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
          
          if (!values.passwordr){
            errors.passwordr = "Required";
          }

          if (values.password !== values.passwordr){
            errors.passwordr = "Passwords do not match";
          }
          return errors;
        }}
        onSubmit={(values, { setSubmitting }) => {
          setSubmitting(false);
          setRedirect(true);
        }}
      >
        {({ isSubmitting, isValidating }) => (
          <Form className="Login" method="post">
            <h2>LiDAR</h2>
            <MaterialText type="email" name="email" label="Email"/>
            <MaterialText type="password" name="password" label="Password"/>
            <MaterialText type="password" name="passwordr" label="Repeat Password"/>
            <div>
              <h3>Register</h3>
              <button type="submit" className="circle-btn"><i className="fas fa-chevron-right"/></button>
            </div>
            <div>
              <Link to="/login/">I've got a login</Link>
            </div>
          </Form>
        )}
      </Formik>
    );
  }
}

function ForgotForm(props){
  const [redirect, setRedirect] = useState(false);
  if (redirect){
    return (<Redirect to="/login/forgot-sent"/>);
  }else{
    return (
      <Formik
        initialValues={{ email: '', }}
        validateOnChange
        validateOnBlur
        validate={ values => {
          const errors = {};
          if (!values.email){
            errors.email = "Required";
          }else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)){
            errors.email = 'Invalid email address';
          }
          return errors;
        }}
        onSubmit={(values, { setSubmitting }) => {
          setSubmitting(false);
          setRedirect(true);
        }}
      >
        {({ isSubmitting, isValidating }) => (
          <Form className="Login" method="post">
            <h2>LiDAR {isValidating}</h2>
            <MaterialText type="email" name="email" label="Email"/>
            <div>
              <h3>Get reset link</h3>
              <button type="submit" disabled={isSubmitting} className="circle-btn"><i className="fas fa-chevron-right"/></button>
            </div>
            <div>
              <Link to="/login/">I've got a login</Link>
            </div>
          </Form>
        )}
      </Formik>
    );
  }
}

function ForgotSent(){
  return (
    <div className="Login">
      <h2>Link sent</h2>
      <p>If you entered a valid email address there will be a reset link in your inbox</p>
    </div>
  );
}

export default function Login() {
  let bgs = [i0, i1,i2,i3,i4,i5,i6,i7,i8,i9,
    i10,i11,i12,i13,i14,i15,i16,i17,i18,i19,
    i20,i21,i22,i23,i24,i25,i26,i27,i28,i29,
    i30,i31,i32,i33,i34,i35,i36,i37,i38,i39,
    i40,i41,i42,i43,i44,i45,i46,i47,i48,i49,
    i50,i51,i52,i53,i54,i55,i56,i57,i58,i59
  ];
  let bgnum = Math.floor(Math.random() * bgs.length)
  return (
    <>
      <Tracking/>
      <div className="Login-container" style={{backgroundImage: `url(${bgs[bgnum]})`}}>
        <Switch>
          <Route exact path="/login">
            <LoginForm/>
          </Route>
          <Route exact path="/login/register">
            <RegistrationForm/>
          </Route>
          <Route exact path="/login/forgot">
            <ForgotForm/>
          </Route>
          <Route exact path="/login/forgot-sent">
            <ForgotSent/>
          </Route>
          <Route path="*">
            <Redirect to="/login"/>
          </Route>
        </Switch>
      </div>
    </>
  );
}
