export const getSite        = ( state, id ) => state.sites.filter(a => a.id === id)[0];
export const getSites       = ( state,    ) => state.sites;
export const getMasterKey   = ( state,    ) => state.tokens.masterKey;
export const getAPIKeys     = ( state,    ) => state.tokens.keys;
export const getEmail       = ( state,    ) => state.credentials.email;
export const getTeamMembers = ( state,    ) => state.team.members;

export const getLoginLoading = ( state,   ) => state.credentials.loading;
export const getLoginFailure = ( state,   ) => state.credentials.failure;

export const getRegisterLoading = ( state, ) => state.credentials.regLoading;
export const getRegisterSuccess = ( state, ) => state.credentials.regSuccess;
export const getRegisterFailure = ( state, ) => state.credentials.regFailure;

export const getOrg = ( state, ) => state.org;

export const getLidar        = ( state, id ) => state.lidars.lidars.filter(a => a.id === id)[0];
export const getLidars       = ( state,    ) => state.lidars.lidars;

export const getKpis        = ( state, ) => state.kpis;
export const getKpi         = ( state, kpiId ) => state.kpis.kpis.filter(a => a.id === kpiId)[0];

