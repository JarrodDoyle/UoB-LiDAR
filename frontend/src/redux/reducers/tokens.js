import { SET_MASTER_API_KEY, ADD_API_KEY } from '../actionTypes.js';

export const tokens = (state = {}, action) => {
  switch (action.type){
    case SET_MASTER_API_KEY:
      return Object.assign({}, state, {
        masterKey: action.key,
      });
    case ADD_API_KEY:
      return Object.assign({}, state, {
        keys: [
          ...state.keys,
          action.key
        ],
      })
    default:
      return state;
  }
}
