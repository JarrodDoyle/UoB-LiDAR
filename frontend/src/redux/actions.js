import {
  ADD_SITE,
  TOGGLE_SITE_MAP_OPEN,
  SET_MASTER_API_KEY,
  ADD_API_KEY,
  SET_EMAIL,
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
  type: ADD_API_KEY,
  key: key.key,
});

export const setEmail = email => ({
  type: SET_EMAIL,
  email: email,
});
