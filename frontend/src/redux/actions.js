import { ADD_LIDAR } from './actionTypes.js'

export const addLidar = lidar => ({
  type: ADD_LIDAR,
  payload: {
    lidar
  }
});
