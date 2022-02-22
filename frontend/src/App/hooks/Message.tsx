import {useSnackbar} from "notistack";

export const useErrorMessage = (textDefault: string = "Datos no validos") => {
    const {enqueueSnackbar} = useSnackbar();
    return (text: string = textDefault): void => {
        enqueueSnackbar(text, {variant: "error"})
    }
}