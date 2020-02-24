export const getSites     = ( state ) => ({ sites:      state.sites,              });
export const getMasterKey = ( state ) => ({ masterKey:  state.tokens.masterKey,   });
export const getSubKeys   = ( state ) => ({ subKeys:    state.tokens.keys,        });
export const getEmail     = ( state ) => ({ email:      state.credentials.email,  });
