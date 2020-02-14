import { combineReducers } from "redux";
import { sites } from './sites.js';
import { tokens } from './tokens.js';

export default combineReducers({
  sites,
  tokens,
});
