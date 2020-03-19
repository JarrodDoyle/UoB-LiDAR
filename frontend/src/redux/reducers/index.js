import { combineReducers } from "redux";
import { sites } from './sites.js';
import { tokens } from './tokens.js';
import { credentials } from './credentials.js';
import { team } from './team.js';

export default combineReducers({
  sites,
  tokens,
  credentials,
  team,
});
