import {configureStore} from '@reduxjs/toolkit'
import sessionReducer from "../pages/login/SessionSlice";

const Store = configureStore({
    reducer: {
        session: sessionReducer
    },
})

export default Store
export type RootState = ReturnType<typeof Store.getState>
export type AppDispatch = typeof Store.dispatch