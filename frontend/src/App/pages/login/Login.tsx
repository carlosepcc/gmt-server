import {FormEvent, ReactElement, useRef, useState} from "react";
import {Button, IconButton, InputAdornment, Paper, TextField, Typography} from "@mui/material";
import {AccountCircle, Key, Visibility, VisibilityOff} from "@mui/icons-material";
import {useSubmit} from "./LoginService";

export default function Login(): ReactElement {
    const form = useRef<HTMLFormElement>(null);
    const [error, setErro] = useState<{ username: boolean, password: boolean }>({username: false, password: false})
    const [showPassword, setShowPassword] = useState<boolean>(false)

    const handleError = (): void => {
        setErro({username: true, password: true})
        setTimeout(() => {
            setErro({username: false, password: false})
        }, 5000)
    }
    const handleClickShowPassword = (): void => {
        setShowPassword(true)
    }
    const handleMouseDownPassword = (): void => {
        setShowPassword(false)
    }

    const submit = useSubmit({cbError: handleError})
    const handleSubmit = async (event: FormEvent<HTMLFormElement>): Promise<void> => {
        event.preventDefault();
        const username: string = form.current?.querySelector<HTMLInputElement>("#username")?.value + "";
        const password: string = form.current?.querySelector<HTMLInputElement>("#password")?.value + "";
        await submit({username: username, password: password})
    }

    return (
        <div style={{
            width: "100vw",
            height: "100vh",
            backgroundImage: "linear-gradient(to bottom left, #6166B3, #544179)",
            color: "white",
        }}>
            <div style={{
                position: "fixed",
                bottom: "10%",
                left: "50%",
                transform: "translate(-50%,-50%)"
            }}>
                <Typography variant={"h2"}>GMT</Typography>
                <Paper sx={{backgroundColor: "#32C1CD", width: 350, height: 250}} elevation={5}>
                    <form onSubmit={handleSubmit} ref={form}>
                        <TextField
                            sx={{paddingLeft: 3, paddingRight: 3, width: "87%", marginTop: 5, color: "white"}}
                            placeholder={"Usuario"}
                            InputProps={{
                                startAdornment: (
                                    <InputAdornment position="start">
                                        <AccountCircle/>
                                    </InputAdornment>
                                ),
                            }}
                            variant={"standard"}
                            error={error.username}
                            id={"username"}
                        />
                        <TextField
                            sx={{paddingLeft: 3, paddingRight: 3, width: "87%", marginTop: 3, color: "white"}}
                            placeholder={"Contraseña"}
                            type={showPassword ? 'text' : 'password'}
                            InputProps={{
                                startAdornment: (
                                    <InputAdornment position={"start"}>
                                        <Key/>
                                    </InputAdornment>
                                ),
                                endAdornment: (
                                    <InputAdornment position={"end"}>
                                        <IconButton
                                            onClick={handleClickShowPassword}
                                            onMouseDown={handleMouseDownPassword}
                                        >
                                            {showPassword ? <VisibilityOff/> : <Visibility/>}
                                        </IconButton>
                                    </InputAdornment>
                                )
                            }}
                            variant={"standard"}
                            error={error.password}
                            id={"password"}
                        />
                        <div style={{display: "grid"}}>
                            <Button sx={{width: 200, marginTop: 5, marginLeft: 9}} variant={"contained"}
                                    type={"submit"}>Entrar</Button>
                        </div>
                    </form>
                </Paper>
            </div>
        </div>
    )
}
