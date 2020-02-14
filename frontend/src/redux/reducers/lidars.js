import { ADD_LIDAR } from '../actionTypes.js';

export const lidars = (state = [], action) => {
  switch (action.type){
    case ADD_LIDAR:
      return [
        ...state,
        {
          id: action.id,
          name: action.name,
          desc: action.desc,
          location: {
            lat: action.location.lat,
            lng: action.location.lng,
          },
        }
      ]
    default:
      return state
  }
}
