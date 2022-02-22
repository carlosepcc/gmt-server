import {ReactElement} from "react";
import {Typography} from "@mui/material";

export default function Page404(): ReactElement {
    return (
        <div style={{
            width: "100vw",
            height: "100vh",
            backgroundImage: "linear-gradient(to bottom left, #6166B3, #544179)",
            color: "white",
        }}>
            <div style={{
                position:"fixed",
                bottom:"50%",
                left:"50%",
                width: 400,
                transform: "translate( -50%, 0 )"
            }}>
                <Typography variant={"h1"}>Error 404</Typography>
            </div>
        </div>
    )
}