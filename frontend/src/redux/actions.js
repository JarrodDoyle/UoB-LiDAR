import {
  ADD_SITE,
  SET_MASTER_API_KEY,
  ADD_API_KEY,
} from './actionTypes.js'

export const addSite = site => ({
  type: ADD_SITE,
  id: site.id,
  name: site.name,
  desc: site.desc,
  location: site.location,
});

export const setMasterApiKey = key => ({
  type: SET_MASTER_API_KEY,
  key
});

export const addApiKey = key => ({
  type: ADD_API_KEY,
  key: key.key,
});
