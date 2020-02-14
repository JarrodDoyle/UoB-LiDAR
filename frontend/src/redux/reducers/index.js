import { combineReducers } from "redux";
import { lidars } from './lidars.js';
import { tokens } from './tokens.js';

export default combineReducers({
  lidars,
  tokens
});
