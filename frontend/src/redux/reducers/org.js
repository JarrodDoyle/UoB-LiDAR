import {
  ORG_SET,
  ORG_ERROR,
  ORG_FETCHING,
} from '../actionTypes.js';

export const org = (state = {
  org_id: -1,
  org_name: "",
  user_level: "none",
  can_write_meta: false,
  can_add_user: false,
  can_add_site: false,
  can_add_lidar: false,
  can_change_user_perms: false,
  can_grant_site_access: false,
  fetching: false,
  error: false,
}, action) => {
  switch (action.type) {
    case ORG_SET:
      return Object.assign({}, state, {
        org_id: action.org.org_id,
        org_name: action.org.org_name,
        user_level: action.org.org_user_perm_level,
        can_write_meta: action.org.org_user_write_meta,
        can_add_user: action.org.org_user_add_user,
        can_add_site: action.org.org_user_add_site,
        can_add_lidar: action.org.org_user_add_lidar,
        can_change_user_perms: action.org.org_user_change_perms,
        can_grant_site_access: action.org.org_grant_site_access,
        fetching: false,
        error: false,
      });
    case ORG_FETCHING:
      return Object.assign({}, state, {
        fetching: true,
      });
    case ORG_ERROR:
      return Object.assign({}, state, {
        fetching: true,
        error: true,
      });
    default:
      return state;
  }
}
