import React, { useEffect } from 'react';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import { Card } from '../Components/Cards.js';
import { useSelector, useDispatch } from 'react-redux';
import { getLidars, getSites, getMasterKey } from '../redux/selectors.js';
import { fetchLidars, fetchSites } from '../redux/actions.js';

function SitesTable(props) {
  return (
    <Table>
      <TableHead>
        <TableRow>
          <TableCell align="center">ID</TableCell>
          <TableCell align="center">Name</TableCell>
          <TableCell align="center">Description</TableCell>
          <TableCell align="center">Location</TableCell>
          {props.type === "lidar" &&
            <TableCell align="center">Site ID</TableCell>
          }
          <TableCell/>
        </TableRow>
      </TableHead>
      <TableBody>
        {props.rows.map(row =>
        <TableRow key={row.id}>
          <TableCell align="center">{row.id}</TableCell>
          <TableCell align="center">{row.name}</TableCell>
          <TableCell align="center">{row.desc}</TableCell>
          <TableCell align="center">
            <p>Lat: {row.location.lat}</p>
            <p>Lng: {row.location.lng}</p>
          </TableCell>
          {props.type === "lidar" &&
            <TableCell align="center">{row.site_id}</TableCell>
          }
          <TableCell><i className="fas fa-trash"/></TableCell>
        </TableRow>
        )}
      </TableBody>
    </Table>
  );
}

export function SitesCard() {
  const sites = useSelector(getSites);
  const dispatch = useDispatch();
  const token = useSelector(getMasterKey);
  useEffect(() => dispatch(fetchSites(token)), [token, dispatch]);
  return (
    <Card>
      <h1>Organisation's sites</h1>
      <SitesTable rows={sites}/>
      <p style={{textAlign: "center"}}><i className="fas fa-plus"/></p>
    </Card>
  );
}

export function LidarsCard() {
  const lidars = useSelector(getLidars);
  const dispatch = useDispatch();
  const token = useSelector(getMasterKey);
  useEffect(() => dispatch(fetchLidars(token)), [token, dispatch]);
  return (
    <Card>
      <h1>Organisation's LiDARs</h1>
      <SitesTable rows={lidars} type="lidar"/>
      <p style={{textAlign: "center"}}><i className="fas fa-plus"/></p>
    </Card>
  );
}
