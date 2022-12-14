import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import * as serviceWorker from './serviceWorker';
import { Provider } from 'react-redux';
import { PersistGate } from 'redux-persist/integration/react';
import storeFunc from './redux/store.js';

function Loading(){
  return (
    <h1>Loading...</h1>
  );
}

const store = storeFunc();

ReactDOM.render(
  <Provider store={store.store}>
    <PersistGate loading={<Loading/>} persistor={store.persistor}>
      <App />
    </PersistGate>
  </Provider>
  , document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
