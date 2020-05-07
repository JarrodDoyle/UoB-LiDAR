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
  sites: member.sites,
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

export const registerFetch = () => ({
  type: REGISTER_FETCH,
});

export const registerSuccess = () => ({
  type: REGISTER_SUCCESS,
});

export const registerFailure = () => ({
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
        if (response.status === 200)
          dispatch(registerSuccess());
        else
          dispatch(registerFailure());
      })
      .catch(error => {
        dispatch(registerFailure());
      });
  }
};
