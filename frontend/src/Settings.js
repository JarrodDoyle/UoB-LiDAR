import React, { useState } from "react";
//import { findByLabelText } from "@testing-library/react";
import { useSelector } from 'react-redux';
import { Formik, Form } from "formik";
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import { getEmail,
  getMasterKey,
  getAPIKeys,
  getSites,
  getTeamMembers,
} from './redux/selectors.js';
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
      <TableContainer component={Paper}>
      <Table  aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Name</TableCell>
            <TableCell align="right">Token</TableCell>
            <TableCell align="right">Sites</TableCell>
            <TableCell align="right">Read</TableCell>
            <TableCell align="right">Write</TableCell>
            <TableCell align="right"></TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {apiData.map((row) => (
            <TableRow key={row.name}>
              <TableCell component="th" scope="row">
                {row.name}
              </TableCell>
              <TableCell align="right">{row.token}</TableCell>
              <TableCell align="right">{row.site}</TableCell>
              <TableCell align="right">{row.read}</TableCell>
              <TableCell align="right">{row.write}</TableCell>
              <TableCell>
                <div className="elipticle-btn">
                  <h5>Edit</h5>
                </div>
                </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    </Card>
  );
}

function TeamMembersCard(props) {
  const users = useSelector(getTeamMembers);
  /*
   * users = [
   *  {
   *    userId: id,
   *    name: bob,
   *    email: bob@bob.com,
   *    sites: [
   *      {
   *        siteid: site1,
   *        write false,
   *        read: false,
   *      }
   *    ]
   *   }
   *  ]
   */
  return (
    <Card>
      <h1>Team Members</h1>
      <TableContainer component={Paper}>
      <Table  aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Name</TableCell>
            <TableCell align="right">Email</TableCell>
            <TableCell align="right">Sites</TableCell>
            <TableCell align="right">Read</TableCell>
            <TableCell align="right">Write</TableCell>
            <TableCell align="right"></TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {apiData.map((row) => (
            <TableRow key={row.name}>
              <TableCell component="th" scope="row">
                {row.name}
              </TableCell>
              <TableCell align="right">{row.token}</TableCell>
              <TableCell align="right">{row.site}</TableCell>
              <TableCell align="right">{row.read}</TableCell>
              <TableCell align="right">{row.write}</TableCell>
              <TableCell>
                <div className="elipticle-btn">
                  <h5>Edit</h5>
                </div>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    </Card>
  );
}



function SitesCard(props) {
  const sites = useSelector(getSites);
  /*
   * sites = [
   *  {
   *   name: name,
   *   desc: description,
   *   location: location,
   *  }
   * ]
   */
  return (
    <Card>
      <h1>Organisation's sites</h1>
    </Card>
  );
}

const useStyles = makeStyles({
  table: {
    minWidth: 650,
  },
});

function createApiData(name, token, site, read, write) {
  return { name, token, site, read, write};
}

const apiData = [
  createApiData('Upload Key', "ea12a61dbasd", ["Site 1", "Site 2"], true, false),
  createApiData('James Mac', "ea12a61dbasd", "Site 1", true, false),
];


export default function Settings(props) {
  return (
  <main>
    <CardColumn>
      <CredsCard/>
      <ApiKeysCard/>
      <TeamMembersCard/>
      <SitesCard/>
    </CardColumn>
  </main>
  );
}
