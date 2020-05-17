import { SET_MASTER_API_KEY, ADD_API_KEY } from '../actionTypes.js';

export const tokens = (state = {masterKey: "", keys: []}, action) => {
  switch (action.type){
    case SET_MASTER_API_KEY:
      return Object.assign({}, state, {
        masterKey: action.key,
      });
    case ADD_API_KEY:
      return Object.assign({}, state, {
        keys: [
          ...state.keys,
          {
            key: action.key,
            name: action.name,
            /* A list of sites the key can interact with
             * Type: array of dictionaries
             * {
             *   siteid: ,
             *   write: True/false,
             *   read: True/false,
             * }
             */
            sites:  action.sites.map((site, index) => {
              return Object.assign({}, {}, action.site);
            }),
          }
        ],
      })
    default:
      return state;
  }
}
