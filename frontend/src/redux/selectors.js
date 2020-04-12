export const getSite        = ( state, id ) => state.sites.filter(a => a.id === id)[0];
export const getSites       = ( state     ) => state.sites;
export const getMasterKey   = ( state     ) => state.tokens.masterKey;
export const getAPIKeys     = ( state     ) => state.tokens.keys;
export const getEmail       = ( state     ) => state.credentials.email;
export const getTeamMembers = ( state,    ) => state.team;
export const getKpis        = ( state, siteId ) => getSite(state, siteId).kpis;
export const getKpi         = ( state, siteId, kpiId ) => getKpis(state, siteId).filter(a => a.id === kpiId)[0];
