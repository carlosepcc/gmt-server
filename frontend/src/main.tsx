import React from 'react'
import ReactDOM from 'react-dom'
import App from './App/App'
import {SnackbarProvider} from "notistack";
import {Provider} from 'react-redux'
import Store from './App/store/Store'

ReactDOM.render(
    <React.StrictMode>
        <Provider store={Store}>
            <SnackbarProvider maxSnack={3}>
                <App/>
            </SnackbarProvider>
        </Provider>
    </React.StrictMode>,
    document.getElementById('root')
)
