import {
  ADD_LIDAR,
  LIDAR_ERROR,
  LIDAR_FETCH,
  LIDAR_FETCH_FIN,
  TOGGLE_SITE_MAP_OPEN,
} from '../actionTypes.js';

export const lidars = (state = {
  fetching: false,
  error: false,
  lidars: []
}, action) => {
  switch (action.type){
    case LIDAR_ERROR:
      return Object.assign({}, state, {
        fetching: false,
        error: true,
      });
    case LIDAR_FETCH:
      return Object.assign({}, state, {
        fetching: true,
      });
    case LIDAR_FETCH_FIN:
      return Object.assign({}, state, {
        fetching: false,
        error: false,
      });
    case ADD_LIDAR:
      return Object.assign({}, state, {
        lidars: [
          ...(state.lidars.filter(a => a.id !== action.id)),
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
            /* kpis: [
              {
                id: "kpiMaintinanceVisits",
                name: "Maintinance visits",
                description: "Visits to perform maintinance tasks",
                percentComplete: 100,
                data: [
                  {
                    id: 45,
                    cardview: {
                      type: "number",
                      text: "Scheduled visits",
                      description: "Number of visits that where pre organised",
                      number: 5,
                    }
                  },
                  {
                    id: 45,
                    cardview: {
                      type: "number",
                      description: "Number of visits that where not organised",
                      text: "Uncheduled visits",
                      number: 25,
                    }
                  }
                ]
              }
            ] */
          }
        ]
      });
    case TOGGLE_SITE_MAP_OPEN:
      return Object.assign({}, state, {
        lidars: state.lidars.map((site, index) => {
          if (site.id === action.id) {
            return Object.assign({}, site, {
              map_open: !site.map_open,
            })
          }
          return Object.assign({}, site, {
            map_open: false,
          })
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
