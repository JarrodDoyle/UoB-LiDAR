import React, { useState, useEffect } from "react";
//import { findByLabelText } from "@testing-library/react";
import { useDispatch, useSelector } from 'react-redux';
import { Formik, Form } from "formik";
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import {
  getEmail,
  getMasterKey,
  getAPIKeys,
  getSites,
  getTeamMembers,
  getOrg,
} from './redux/selectors.js';
import { logout, fetchOrganisationDetails, fetchTeamMembers } from './redux/actions.js';
import { MaterialText } from "./Components/Material-Inp.js";
import { CardColumn, Card } from "./Components/Cards.js";
import Popup from "reactjs-popup";

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
      <TableContainer component={Paper}>
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
    </TableContainer>
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

function SitesTable(props){
  return (
    <Table aria-label={`${props.email}'s site permissions`}>
      <TableBody>
        <TableRow align="center">
          <TableCell align="center">{props.type}</TableCell>
          <TableCell align="center">Read</TableCell>
          <TableCell align="center">Write</TableCell>
          <TableCell align="center"></TableCell>
        </TableRow>
        {props.sites.map(site => (
          <TableRow key={site.id} align="center">
            <TableCell align="center">{site.name}</TableCell>
            <TableCell align="center">{site.read ? <i className="fas fa-check"/> : <i className="fas fa-times"/>}</TableCell>
            <TableCell align="center">{site.write ? <i className="fas fa-check"/> : <i className="fas fa-times"/>}</TableCell>
            <TableCell align="center">{props.can_change_user_perms && <i className="fas fa-trash"/>}</TableCell>
          </TableRow>
        ))}
        <TableRow align="center">
          <TableCell align="center" colSpan={4}>{props.can_change_user_perms && <i className="fas fa-plus"/>}</TableCell>
        </TableRow>
      </TableBody>
    </Table>
  );
}

function TeamMembersCard(props) {
  const dispatch = useDispatch();
  const users = useSelector(getTeamMembers);
  const org = useSelector(getOrg);
  const masterKey = useSelector(getMasterKey);

  useEffect(() => {
    dispatch(fetchTeamMembers(masterKey));
  }, [dispatch, masterKey]);

  if (!org.can_add_user && !org.can_change_user_perms)
    return (<></>);

  return (
    <Card>
      <h1>Team Members</h1>
      <TableContainer component={Paper}>
      <Table  aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell align="center">Email</TableCell>
            <TableCell align="center">Role</TableCell>
            <TableCell align="center">Sites</TableCell>
            <TableCell align="center">Lidars</TableCell>
            <TableCell align="center"></TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {users.map((row) => (
            <TableRow key={row.email}>
              <TableCell component="th" scope="row" align="center">
                {row.email}
              </TableCell>
              <TableCell align="center">{row.perms}</TableCell>
              <TableCell align="center">
                <SitesTable email={row.email} sites={row.sites} type="Sites" can_change_user_perms={org.can_change_user_perms}/>
              </TableCell>
              <TableCell align="center">
                <SitesTable email={row.email} sites={row.lidars} type="LiDARs" can_change_user_perms={org.can_change_user_perms}/>
              </TableCell>
              { org.can_change_user_perms &&
                <TableCell align="center">
                  <TeamMemberModalPopup {...row} />
                </TableCell>
              }
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    { org.can_add_user &&
      <div align="center">
          <div className="elipticle-btn" style={{width: 200, margin: 10}}>
            <h5>Add a Team Member</h5>
          </div>
      </div>
    }
    </Card>
  );
}

function TeamMemberModalPopup(props){
  return(
  <Popup
    trigger={<button className="elipticle-btn"><h5>Edit</h5></button>}
    modal
    closeOnDocumentClick
    contentStyle = {{borderRadius: 10}}
  >
    <h1>Edit Team Member - {props.email}</h1>
    <Card>
      <h2>Details</h2>
      {props.email}
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
                  {site.name}
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
          <h5>Delete Team Member</h5>
        </div>
      </div>
    </Popup>
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

function OrgCard(props) {
  const dispatch = useDispatch();
  const masterKey = useSelector(getMasterKey);
  const org = useSelector(getOrg);
  useEffect(() => {
    dispatch(fetchOrganisationDetails(masterKey));
  }, [dispatch, masterKey]);
  return (
    <Card>
      <h1>Organisation Details</h1>
      { org.fetching
        ? <p>Loading...</p>
        :
        <>
          <p>You are a <b>{org.user_level}</b> at {org.org_name} which gives you the following permissions:</p>
          <p>{org.can_add_user  ? <i className="fas fa-check-circle"/> : <i className="fas fa-times-circle"/>} Add users to the organisation</p>
          <p>{org.can_add_site  ? <i className="fas fa-check-circle"/> : <i className="fas fa-times-circle"/>} Add site to the organisation</p>
          <p>{org.can_add_lidar ? <i className="fas fa-check-circle"/> : <i className="fas fa-times-circle"/>} Add lidar to the organisation</p>
          <p>{org.can_change_user_perms  ? <i className="fas fa-check-circle"/> : <i className="fas fa-times-circle"/>} Change a user's permissions</p>
          <p>{org.can_write_meta  ? <i className="fas fa-check-circle"/> : <i className="fas fa-times-circle"/>} Change organisation's details</p>
          <p>{org.can_grant_site_access  ? <i className="fas fa-check-circle"/> : <i className="fas fa-times-circle"/>} Give another organisation access to your sites</p>
        </>
      }
    </Card>
  );
}

function createApiData(name, token, sites, read, write) {
  return { name, token, sites, read, write};
}

const apiData = [
  createApiData('Upload Key', "ea12a61dbasd", ["Site 1", "Site 2"], true, false),
  createApiData('View Key', "ea12a61dbasd", ["Site 1", "Site 2", "Site 3"], true, false),
];

export default function Settings(props) {
  return (
  <main>
    <CardColumn>
      <CredsCard/>
      <ApiKeysCard/>
      <SitesCard/>
      <OrgCard/>
      <TeamMembersCard/>
    </CardColumn>
  </main>
  );
}
