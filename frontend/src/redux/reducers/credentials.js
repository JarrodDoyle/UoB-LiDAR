import {
  SET_EMAIL,
  LOGIN_FETCH,
  LOGIN_SUCCESS,
  LOGIN_FAILURE,
  REGISTER_FETCH,
  REGISTER_SUCCESS,
  REGISTER_FAILURE,
  REGISTER_RESET,
} from '../actionTypes.js';

export const credentials = (state = {
  email: "",
  loading: false,
  failure: false,
  regLoading: false,
  regSuccess: false,
  regFailure: false,
}, action) => {
  switch (action.type){
    case SET_EMAIL:
      return Object.assign({}, state, {
        email: action.email,
      });
    case LOGIN_FETCH:
      return Object.assign({}, state, {
        loading: true,
        failure: false,
      });
    case LOGIN_SUCCESS:
      return Object.assign({}, state, {
        email: action.email,
        loading: false,
      });
    case LOGIN_FAILURE:
      return Object.assign({}, state, {
        loading: false,
        failure: true,
      });
    case REGISTER_FETCH:
      return Object.assign({}, state, {
        regLoading: true,
        failure: false,
      });
    case REGISTER_SUCCESS:
      return Object.assign({}, state, {
        regLoading: false,
        regSuccess: true,
      });
    case REGISTER_FAILURE:
      return Object.assign({}, state, {
        regLoading: false,
        regFailure: true,
      });
    case REGISTER_RESET:
      return Object.assign({}, state, {
        regLoading: false,
        regSuccess: false,
        regFailure: false,
      })
    default:
      return state;
  }
}
