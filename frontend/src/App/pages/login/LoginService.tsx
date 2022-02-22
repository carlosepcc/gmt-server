import {useAppDispatch, useProcessError} from "../../hooks/Global";
import {useNavigate} from "react-router-dom";
import {useValidationPassword, useValidationUsername} from "../../hooks/Validation";
import {useErrorMessage} from "../../hooks/Message";
import axios from "axios";
import {init} from "./SessionSlice";

export const useSubmit = ({cbError}: { cbError?: () => void }) => {
    const dispatch = useAppDispatch()
    const navegate = useNavigate()
    const validationUsername = useValidationUsername()
    const validationPassword = useValidationPassword()
    const errorProcess = useProcessError()
    const errorMessage = useErrorMessage()

    return async ({username, password}: { username: string, password: string }): Promise<void> => {
        try {
            validationUsername({username})
            validationPassword({password})
            const response = await axios.post("/login", {
                username: username,
                password: password
            }, {
                headers: {
                    "Content-Type": "application/json"
                }
            })
            dispatch(init({jwt: response.data}))
            axios.defaults.headers.common["Authorization"] = response.data
            localStorage.setItem("jwt", response.data)
            navegate("/inicio")
        } catch (e: any) {
            let error = errorProcess({error: e})
            errorMessage(error.message)
            cbError?.()
        }
    }
}