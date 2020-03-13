import {
  ADD_SITE,
  TOGGLE_SITE_MAP_OPEN,
} from '../actionTypes.js';

export const sites = (state = [], action) => {
  switch (action.type){
    case ADD_SITE:
      return [
        ...state,
        {
          id: action.id,
          name: action.name,
          desc: action.desc,
          totalComplete: action.totalComplete,
          location: {
            lat: action.location.lat,
            lng: action.location.lng,
          },
          map_open: false,
        }
      ]
    case TOGGLE_SITE_MAP_OPEN:
      return state.map((site, index) => {
        if (site.id === action.id) {
          return Object.assign({}, site, {
            map_open: !site.map_open,
          })
        }
        return Object.assign({}, site, {
          map_open: false,
        })
      });
    default:
      return state
  }
}

/*
{
  id: action.id,
  name: action.name,
  desc: action.desc,
  totalComplete: action.totalComplete,
  location: {
    lat: action.location.lat,
    lng: action.location.lng,
  },
  map_open: false,
  kpis: [
    {
      id: ,
      name: ,
      description: , 
      percentComplete: ,
      data: [
        {
          id: ,
          name: ,
          description: , - hover text
          cardview: {
            type: text,
            text: ,
            ==== or ====
            type: numeric,
            text: ,
            data: ,
          }
          detailedview: {
            ==== same as card view types plus: ====
            type: graph,
            graphType: line,
            graphData: {
              graph json
            }
          }
        }
      ]
    }
  ]
}
*/
