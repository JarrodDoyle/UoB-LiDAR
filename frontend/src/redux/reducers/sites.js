import {
  ADD_SITE,
  SITE_FETCH,
  SITE_ERROR,
  SITE_FETCH_FIN,
} from '../actionTypes.js';

export const sites = (state = {
  fetching: false,
  error: false,
  sites: [],
}, action) => {
  switch (action.type) {
    case SITE_ERROR:
      return Object.assign({}, state, {
        fetching: false,
        error: true,
      });
    case SITE_FETCH:
      return Object.assign({}, state, {
        fetching: true,
      });
    case SITE_FETCH_FIN:
      return Object.assign({}, state, {
        fetching: false,
        error: false,
      });
    case ADD_SITE:
      return Object.assign({}, state, {
        sites: [
          ...(state.sites.filter(a => a.id !== action.id)),
          {
            id: action.id,
            name: action.name,
            desc: action.desc,
            // totalComplete: action.totalComplete,
            location: {
              lat: action.location.lat,
              lng: action.location.lng,
            },
          }
        ]
      });
    default:
      return state
  }
}

/*
  detailedview: {
    type: "graph",
    graphType: "line",
    graphData: [
      {
        "id": "japan",
        "color": "hsl(227, 70%, 50%)",
        "data": [
          {
            "x": 0,
            "y": 208
          },
          {
            "x": 1,
            "y": 224
          },
          {
            "x": 2,
            "y": 240
          },
          {
            "x": 3,
            "y": 256
          },
        ]
    }
  ]
*/
