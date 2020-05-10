import { ADD_TEAM_MEMBER } from '../actionTypes.js';

export const team = (state = {
  fetching: false,
  error: false,
  members: [],
}, action) => {
  switch (action.type){
    case ADD_TEAM_MEMBER:
      return Object.assign({}, state, {
        members: [
          ...(state.members.filter(member => member.email !== action.email)),
          {
            userId: action.userId,
            name: action.name,
            email: action.email,
            perms: action.perms,
            sites: Object.assign([],[],action.sites),
            lidars: Object.assign([],[],action.lidars),
          }
        ],
      });
    default:
      return state;
  }
}
