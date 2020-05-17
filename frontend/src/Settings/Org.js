import React, { useEffect } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { Formik, Form } from "formik";
import { Card } from "../Components/Cards.js";
import { Selector } from '../Components/Material-Inp.js';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Popup from "reactjs-popup";
import {
  getMasterKey,
  getOrg,
  getTeamMembers,
} from '../redux/selectors.js';
import { fetchOrganisationDetails, fetchTeamMembers } from '../redux/actions.js';

export function OrgCard(props) {
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

function AddSiteMenu(props){
  return(
    <Popup
      trigger={<i className="fas fa-plus"/>}
      modal
      closeOnDocumentClick
      contentStyle = {{borderRadius: 10}}
    >
      <Formik
        initialValues={{ }}
        validateOnChange
        validateOnBlur
        validate={ values => {
          const errors = {};
          return errors;
        }}
        onSubmit={(values, { setSubmitting }) => {
        }}
      >
        {({ isSubmitting, isValidating }) => (
          <Form className="material-form">
            <h1>Add {props.type} to {props.email}</h1>
            <Selector name="site_id" label={props.type} values={["hi","hello","nbo"]}/>
            <div className="elipticle-btn" style={{width: 170}}>
              <button type="submit">Submit</button>
            </div>
          </Form>
        )}
      </Formik>

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
          <TableCell align="center" colSpan={4}>
            {props.can_change_user_perms &&
              <AddSiteMenu {...props}/>
            }
          </TableCell>
        </TableRow>
      </TableBody>
    </Table>
  );
}

export function TeamMembersCard(props) {
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
          <div className="elipticle-btn" align="right" style={{width:150, margin: 10}}>
              <h5>Add a site</h5>
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
