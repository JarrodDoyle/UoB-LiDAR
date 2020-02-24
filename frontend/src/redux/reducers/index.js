import { combineReducers } from "redux";
import { sites } from './sites.js';
import { tokens } from './tokens.js';
import { credentials } from './credentials.js';

export default combineReducers({
  sites,
  tokens,
  credentials,
});
