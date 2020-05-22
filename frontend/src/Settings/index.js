import React, { useState } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { Formik, Form } from "formik";
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import {
  getEmail,
  getMasterKey,
  getAPIKeys,
} from '../redux/selectors.js';
import { logout } from '../redux/actions.js';
import { MaterialText } from "../Components/Material-Inp.js";
import { CardColumn, Card } from "../Components/Cards.js";
import Popup from "reactjs-popup";
import { OrgCard, TeamMembersCard } from './Org.js';
import { SitesCard, LidarsCard } from './Sites.js';

function CredsCard(props){
  const [success, setSuccess] = useState(false);
  const email = useSelector(getEmail);
  const dispatch = useDispatch();
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
              <button type="submit">Submit</button>
            </div>
          </Form>
        )}
      </Formik>
      {success &&
        <p>Succesfully updated</p>
      }
      <button className="elipticle-btn" onClick={() => dispatch(logout())}>
        Logout
      </button>
    </Card>
  );
}

function MasterCard(props){
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

function ApiKeysCard(props) {
  const keys = useSelector(getAPIKeys);
  /* keys = [
   *  {
   *    name: aa,
   *    sites: [
   *      {
   *        siteid: site1,
   *        write: false,
   *        read: true,
   *      },
   *    ]
   *  }
   * ]
   */
  return (
    <Card>
      <h1>API Keys</h1>
      <Table  aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Name</TableCell>
            <TableCell align="right">Token</TableCell>
            <TableCell align="center">Sites</TableCell>
            <TableCell align="right"></TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {keys.map((row) => (
            <TableRow key={row.name}>
              <TableCell component="th" scope="row">
                {row.name}
              </TableCell>
              <TableCell align="right">{row.token}</TableCell>
              <TableCell align="right">
                <div align="center">
                  <TableRow align="inherit">
                    <TableCell>Site</TableCell>
                    <TableCell >Read</TableCell>
                    <TableCell >Write</TableCell>
                  </TableRow>
                  {row.sites.map(site => (
                    <TableRow align="right">
                      <TableCell>{site}</TableCell>
                      <TableCell align="right"><i className="fas fa-check"/></TableCell>
                      <TableCell align="right"><i className="fas fa-times"/></TableCell>
                    </TableRow>
                  ))}
                  </div>
              </TableCell>
              <TableCell align="center">
                <APIModalPopup {...row} />
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    <div align="center">
      <div className="elipticle-btn" style={{width: 200, margin: 10}}>
        <h5>Add an API Key</h5>
      </div>
    </div>
    </Card>
  );
}

// Modal Popup
function APIModalPopup(props){
  return(
  <Popup
    trigger={<button className="elipticle-btn"><h5>Edit</h5></button>}
    modal
    closeOnDocumentClick
    contentStyle = {{borderRadius: 10}}
  >
      <h1>Edit API Key - {props.name}</h1>
      <Card>
        <h2>Token</h2>
        <div>{props.token } <i className="fas fa-trash"/></div>
        <div align="center">
          <div className="elipticle-btn" align="right" style={{width:150, margin: 10}}>
              <h5>Add a Token</h5>
          </div>
        </div>
      </Card>
      <Card>
        <h2>Sites</h2>
          <Table  aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>Name</TableCell>
                <TableCell align="right">Read</TableCell>
                <TableCell align="right">Write</TableCell>
                <TableCell align="right"></TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {props.sites.map((site) => (
                <TableRow key={site}>
                  <TableCell component="th" scope="row">
                    {site}
                  </TableCell>

                  <TableCell align="right"><input type="checkbox" ></input></TableCell>
                  <TableCell align="right"><input type="checkbox"></input></TableCell>
                  <TableCell align="right"><i className="fas fa-trash"/></TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
          <div align="center">
            <div className="elipticle-btn" align="right" style={{width:150, margin: 10}}>
                <h5>Add a site</h5>
            </div>
          </div>
      </Card>
      <div align="center">
        <div className="elipticle-btn" style={{width: 170, margin: 10}}>
          <h5>Save Changes</h5>
        </div>
        <div className="elipticle-btn" style={{width: 170, margin: 10}}>
          <h5>Delete API Key</h5>
        </div>
      </div>
    </Popup>
  );
}

export default function Settings(props) {
  return (
  <main>
    <CardColumn>
      <CredsCard/>
      <ApiKeysCard/>
      <OrgCard/>
      <SitesCard/>
      <LidarsCard/>
      <TeamMembersCard/>
      <MasterCard/>
    </CardColumn>
  </main>
  );
}
