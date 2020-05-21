import {
  ADD_SITE,
  TOGGLE_SITE_MAP_OPEN,
  SET_MASTER_API_KEY,
  ADD_API_KEY,
  UPDATE_API_KEY,
  SET_EMAIL,
  ADD_TEAM_MEMBER,
  UPDATE_TEAM_MEMBER,
  RESET_STORE,
  LOGIN_FETCH,
  LOGIN_SUCCESS,
  LOGIN_FAILURE,
  REGISTER_FETCH,
  REGISTER_FAILURE,
  REGISTER_SUCCESS,
  REGISTER_RESET,
  ORG_FETCHING,
  ORG_ERROR,
  ORG_SET,
  TEAM_FETCHING,
  TEAM_ERROR,
  TEAM_MEMBER_ERROR,
  TEAM_MEMBER_UPDATE,
  TEAM_MEMBER_UPDATING,
  ADD_LIDAR,
  LIDAR_FETCH,
  LIDAR_ERROR,
  LIDAR_FETCH_FIN,
} from './actionTypes.js'

const domain = "http://localhost:6000"

export const addSite = site => ({
  type: ADD_SITE,
  id: site.id,
  name: site.name,
  desc: site.desc,
  totalComplete: site.totalComplete,
  location: site.location,
});

export const addLidar = site => ({
  type: ADD_LIDAR,
  id: site.id,
  name: site.name,
  desc: site.desc,
  totalComplete: 75,
  location: site.location,
});

const lidarsFetching = () => ({
  type: LIDAR_FETCH,
});

const lidarsFetchFin = () => ({
  type: LIDAR_FETCH_FIN,
})

const lidarsError = () => ({
  type: LIDAR_ERROR,
})

export const fetchLidars = token => {
  return (dispatch) => {
    dispatch(lidarsFetching())
    fetch(`${domain}/lidars/get?token=${token}`)
      .then(response => {
        if (response.status === 200) {
          response.json().then(r => {
            r.data.map(lidar => dispatch(addLidar(lidar)));
            dispatch(lidarsFetchFin());
          });
        } else {
          dispatch(lidarsError());
        }
      })
      .catch(error => {
        dispatch(lidarsError());
      });
  }
};

// Will toggle site open/close but also close all others
export const toggleSiteMapOpen = id => ({
  type: TOGGLE_SITE_MAP_OPEN,
  id: id,
});

export const setMasterApiKey = key => ({
  type: SET_MASTER_API_KEY,
  key
});

export const addApiKey = key => ({
  type: UPDATE_API_KEY,
  key: key.key,
  name: key.name,
  sites: key.sites,
});

export const updateApiKey = key => ({
  type: ADD_API_KEY,
  key: key.key,
  name: key.name,
  sites: key.sites,
});

export const setEmail = email => ({
  type: SET_EMAIL,
  email: email,
});

export const addTeamMember = member => ({
  type: ADD_TEAM_MEMBER,
  userId: member.userId,
  name: member.name,
  email: member.email,
  perms: member.perms,
  sites: member.sites,
  lidars: member.lidars,
});

export const updateTeamMember = member => ({
  type: UPDATE_TEAM_MEMBER,
  userId: member.userId,
  name: member.name,
  email: member.email,
  sites: member.sites,
})

export const logout = () => ({
  type: RESET_STORE,
});

export const loginSuccess = user => ({
  type: LOGIN_SUCCESS,
  email: user.email,
});

export const loginFailure = () => ({
  type: LOGIN_FAILURE,
});

export const loginFetch = () => ({
  type: LOGIN_FETCH,
});

export const login = credentials => {
  return (dispatch) => {
    dispatch(loginFetch())
    fetch(domain + "/login", {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json;charset=UTF-8',
      },
      body: JSON.stringify(credentials),
    })
      .then(response => {
        if (response.status === 200) {
          response.json().then(r => {
            dispatch(loginSuccess(credentials));
            dispatch(setMasterApiKey(r.data.master_key));
          });
        } else {
          dispatch(loginFailure());
        }
      })
      .catch(error => {
        dispatch(loginFailure());
      });
  }
};

const registerFetch = () => ({
  type: REGISTER_FETCH,
});

const registerSuccess = () => ({
  type: REGISTER_SUCCESS,
});

const registerFailure = () => ({
  type: REGISTER_FAILURE,
});

export const registerReset = () => ({
  type: REGISTER_RESET,
});

export const register = credentials => {
  return (dispatch) => {
    dispatch(registerFetch());
    fetch(domain + "/register", {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json;charset=UTF-8',
      },
      body: JSON.stringify(credentials),
    })
      .then(response => {
        if (response.status === 200) {
          dispatch(registerSuccess());
        } else {
          dispatch(registerFailure());
        }
      })
      .catch(error => {
        dispatch(registerFailure());
      });
  }
};

const orgFetching = () => ({
  type: ORG_FETCHING,
});

const setOrg = org => ({
  type: ORG_SET,
  org
});

const orgError = () => ({
  type: ORG_ERROR,
});

export const fetchOrganisationDetails = masterToken => {
  return (dispatch) => {
    dispatch(orgFetching());
    fetch(`${domain}/getUserOrganisation?token=${masterToken}`)
      .then(response => {
        if (response.status === 200) {
          response.json().then(r => {
            dispatch(setOrg(r.data))
          });
        } else
          dispatch(orgError());
      })
      .catch(error => {
        dispatch(orgError());
      });
  }
}

const teamFetching = () => ({
  type: TEAM_FETCHING,
});

const teamError = () => ({
  type: TEAM_ERROR,
});

export const fetchTeamMembers = masterToken => {
  return (dispatch) => {
    dispatch(teamFetching());
    fetch(`${domain}/getTeamMembers?token=${masterToken}`)
      .then(response => {
        if (response.status === 200) {
          response.json().then(r => {
            r.data.map(member =>
              dispatch(addTeamMember(member))
            );
          });
        } else
          dispatch(teamError());
      })
      .catch(error => {
        dispatch(teamError());
      });
  }
}

const teamMemberUpdate = () => ({
  type: TEAM_MEMBER_UPDATING,
});

const teamMemberError = () => ({
  type: TEAM_MEMBER_ERROR,
});

const updateMember = member => ({
  type: TEAM_MEMBER_UPDATE,
  member
});

export const updateSitePerm = site => {
  return (dispatch) => {
    dispatch(teamMemberUpdate());
    fetch(`${domain}/?token=${site.masterToken}`)
      .then(response => {
        if (response.status === 200) {
          response.json().then(r => {
            dispatch(updateMember(site.member));
          });
        } else {
          dispatch(teamMemberError());
        }
      })
      .catch(error => {
        dispatch(teamMemberError());
      });
  }
}
