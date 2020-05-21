import {
  KPI_FETCH,
  KPI_ERROR,
  KPI_ADD,
  KPI_FETCH_FIN,
} from '../actionTypes.js';

export const kpis = (state = {
  fetching: false,
  error: false,
  kpis: []
}, action) => {
  switch (action.type){
    case KPI_FETCH:
      return Object.assign({}, state, {
        fetching: true,
      });
    case KPI_ERROR:
      return Object.assign({}, state, {
        fetching: false,
        error: true,
      });
    case KPI_FETCH_FIN:
      return Object.assign({}, state, {
        fetching: false,
        error: false,
      });

    case KPI_ADD:
      return Object.assign({}, state, {
        kpis: [
          ...(state.kpis.filter(a => a.id !== action.kpi.id)),
          action.kpi,
        ]
      });
    default:
      return state;
  }
}
