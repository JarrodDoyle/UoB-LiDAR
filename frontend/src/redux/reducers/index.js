import { combineReducers } from "redux";
import { RESET_STORE} from '../actionTypes.js';
import { sites } from './sites.js';
import { tokens } from './tokens.js';
import { credentials } from './credentials.js';
import { team } from './team.js';
import { org } from './org.js';

const appReducer = combineReducers({
  sites,
  tokens,
  credentials,
  team,
  org,
});

export default (state, action) => {
  if (action.type === RESET_STORE) {
    state = undefined;
  }
  return appReducer(state, action);
}
