import { combineReducers } from "redux";
import { RESET_STORE} from '../actionTypes.js';
import { sites } from './sites.js';
import { tokens } from './tokens.js';
import { credentials } from './credentials.js';
import { team } from './team.js';

const appReducer = combineReducers({
  sites,
  tokens,
  credentials,
  team,
});

export default (state, action) => {
  if (action.type === RESET_STORE) {
    state = undefined;
  }
  return appReducer(state, action);
}
