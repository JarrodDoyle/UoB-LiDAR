import { ADD_TEAM_MEMBER } from '../actionTypes.js';

export const team = (state = [], action) => {
  switch (action.type){
    case ADD_TEAM_MEMBER:
      return [
        ...state,
        {
          userId: action.userId,
          name: action.name,
          email: action.email,
          sites: Object.assign([],[],action.sites),
        }
      ]
    default:
      return state;
  }
}
