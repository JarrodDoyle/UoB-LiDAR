import {
  ADD_LIDAR,
  SET_MASTER_API_KEY,
  ADD_API_KEY,
} from './actionTypes.js'

export const addLidar = lidar => ({
  type: ADD_LIDAR,
  id: lidar.id,
  name: lidar.name,
  desc: lidar.desc,
  location: lidar.location,
});

export const setMasterApiKey = key => ({
  type: SET_MASTER_API_KEY,
  key
});

export const addApiKey = key => ({
  type: ADD_API_KEY,
  key: key.key,
});
