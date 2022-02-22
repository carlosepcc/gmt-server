import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import isJwtTokenExpired, {decode} from "jwt-check-expiry";
import {RootState} from "../../store/Store";

interface SessionState {
    open: boolean | undefined,
    username: string | undefined;
    roles: string[] | undefined;
}

const initialState: SessionState = {
    open: undefined,
    username: undefined,
    roles: undefined,
}

export const sessionSlice = createSlice({
    name: "session",
    initialState,
    reducers: {
        init: (state, action: PayloadAction<{ jwt: string }>): void => {
            const {jwt} = action.payload
            try {
                if (!isJwtTokenExpired(jwt)) {
                    let jwtDecode: any = decode(jwt).payload
                    state.open = true
                    state.username = jwtDecode.sub
                    state.roles = jwtDecode.roles
                } else {
                    state.open = false
                    state.username = undefined
                    state.roles = undefined
                }
            } catch (e) {
                state.open = false
                state.username = undefined
                state.roles = undefined
            }
        },
        initUP: (state, action: PayloadAction<{ jwt: string }>): void => {
            const {jwt} = action.payload
            try {
                if (!isJwtTokenExpired(jwt)) {
                    let jwtDecode: any = decode(jwt).payload
                    state.open = true
                    state.username = jwtDecode.sub
                    state.roles = jwtDecode.roles
                } else {
                    state.open = false
                    state.username = undefined
                    state.roles = undefined
                }
            } catch (e) {
                state.open = false
                state.username = undefined
                state.roles = undefined
            }
        },
        close: (state): void => {
            localStorage.clear()
            state.open = false
            state.username = undefined
            state.roles = undefined
        }
    }
})

export default sessionSlice.reducer
export const {init, close} = sessionSlice.actions
export const selectOpen = (state: RootState) => state.session.open
export const selectUsername = (state: RootState) => state.session.username
