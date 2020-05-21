import {
  ADD_TEAM_MEMBER,
  TEAM_MEMBER_UPDATE,
  TEAM_MEMBER_UPDATING,
  TEAM_MEMBER_ERROR,
} from '../actionTypes.js';

export const team = (state = {
  fetching: false,
  members: [],
  updateing: false,
  error: false,
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
            updating: false,
          }
        ],
      });
    case TEAM_MEMBER_UPDATING:
      return Object.assign({}, state, {
        updating: true,
      });
    case TEAM_MEMBER_ERROR:
      return Object.assign({}, state, {
        updating: false,
        error: true,
      });
    case TEAM_MEMBER_UPDATE:
      return Object.assign({}, state, {
        updating: false,
        error: false,
        memebers: [
          ...(state.members.filter(member => member.email !== action.email)),
          action.member,
        ],
      });
    default:
      return state;
  }
}
