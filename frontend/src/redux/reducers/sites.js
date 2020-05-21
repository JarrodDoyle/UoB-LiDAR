import {
  ADD_SITE,
  TOGGLE_SITE_MAP_OPEN,
} from '../actionTypes.js';

export const sites = (state = [], action) => {
  switch (action.type) {
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
          kpis: [
            {
              id: "kpiMaintenanceVisits",
              name: "Maintenance visits",
              description: "Visits to perform maintinance tasks",
              percentComplete: 20,
              data: [
                {
                  id: 45,
                  cardview: {
                    type: "number",
                    text: "Scheduled visits",
                    description: "Number of visits that where pre organised",
                    number: 5,
                  },
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
                            "y": 16
                          },
                          {
                            "x": 1,
                            "y": 32
                          },
                          {
                            "x": 2,
                            "y": 48
                          },
                          {
                            "x": 3,
                            "y": 64
                          },
                        ]
                      }
                    ]
                  }
                },
                {
                  id: 45,
                  cardview: {
                    type: "number",
                    description: "Number of visits that where not organised",
                    text: "Uncheduled visits",
                    number: 25,
                  },
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
                            "y": 80
                          },
                          {
                            "x": 1,
                            "y": 96
                          },
                          {
                            "x": 2,
                            "y": 112
                          },
                          {
                            "x": 3,
                            "y": 128
                          },
                        ]
                      }
                    ]
                  }
                }
              ]
            },
            {
              id: "kpiEpicGamer",
              name: "Epic Gamer",
              description: "haha",
              percentComplete: 100,
              data: [
                {
                  id: 45,
                  cardview: {
                    type: "number",
                    text: "Scheduled visits",
                    description: "Number of visits that where pre organised",
                    number: 5,
                  },
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
                            "y": 144
                          },
                          {
                            "x": 1,
                            "y": 160
                          },
                          {
                            "x": 2,
                            "y": 176
                          },
                          {
                            "x": 3,
                            "y": 192
                          },
                        ]
                      }
                    ]
                  }
                },
                {
                  id: 45,
                  cardview: {
                    type: "number",
                    description: "Number of visits that where not organised",
                    text: "Uncheduled visits",
                    number: 25,
                  },
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
                  }
                },
                {
                  id: 45,
                  cardview: {
                    type: "number",
                    description: "Number of visits that where not organised",
                    text: "Uncheduled visits",
                    number: 25,
                  },
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
                  }
                }
              ]
            }
          ]
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
