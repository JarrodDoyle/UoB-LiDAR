import { SET_EMAIL } from '../actionTypes.js';

export const credentials = (state = {}, action) => {
  switch (action.type){
    case SET_EMAIL:
      return Object.assign({}, state, {
        email: action.email,
      });
    default:
      return state;
  }
}
