import { ADD_LIDAR } from '../actionTypes.js';

export const lidars = (state = [], action) => {
  switch (action.type){
    case ADD_LIDAR:
      return [
        ...state,
        {
          id: action.lidar.id,
          title: action.lidar.title,
          desc: action.lidar.desc,
          location: {
            lat: action.lidar.location.lat,
            lng: action.lidar.location.lng,
          },
        }
      ]
    default:
      return state
  }
}
