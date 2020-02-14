import { ADD_LIDAR, SET_API_KEY } from './actionTypes.js'

export const addLidar = lidar => ({
  type: ADD_LIDAR,
  lidar
});

export const setApiKey = key => ({
  type: SET_API_KEY,
  key
});
