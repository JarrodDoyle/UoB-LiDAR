import {
  ADD_SITE,
  TOGGLE_SITE_MAP_OPEN,
  SET_MASTER_API_KEY,
  ADD_API_KEY,
  UPDATE_API_KEY,
  SET_EMAIL,
  ADD_TEAM_MEMBER,
  UPDATE_TEAM_MEMBER,
} from './actionTypes.js'

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
