import {ReactElement} from "react";
import {Outlet} from 'react-router-dom'
import MyAppBar from "./MyAppBar";

export default function Frame(): ReactElement {

    return (
        <div style={{
            width: "100vw",
            height: "100vh",
            backgroundImage: "linear-gradient(to bottom left, #6166B3, #544179)",
            color: "white",
        }}>
            <MyAppBar/>
            <Outlet/>
        </div>
    )
}