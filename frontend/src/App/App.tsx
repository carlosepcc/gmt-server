import {ReactElement, useEffect} from "react";
import axios from "axios";
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import {init, selectOpen} from "./pages/login/SessionSlice";
import Login from "./pages/login/Login";
import Frame from "./component/Frame";
import {useAppDispatch, useAppSelector} from "./hooks/Global";
import Page404 from "./pages/Page404";

export default function App(): ReactElement {
    axios.defaults.baseURL = 'http://localhost:8080';
    const open = useAppSelector(selectOpen)
    const dispatch = useAppDispatch()

    useEffect((): void => {
        let jwt = localStorage.getItem("jwt")
        dispatch(init({jwt: jwt + ""}))
    }, [])
    return (
        <BrowserRouter>
            {
                open === undefined ? null : !open ?
                    <Routes>
                        <Route path={"/login"} element={<Login/>}/>
                        <Route path={"*"} element={<Navigate to={"/login"}/>}/>
                    </Routes>
                    :
                    <Routes>
                        <Route element={<Frame/>}>
                            <Route path={"/inicio"} element={<h1>Inicio</h1>}/>
                        </Route>
                        <Route path={"*"} element={<Page404/>}/>
                    </Routes>
            }
        </BrowserRouter>
    )
}