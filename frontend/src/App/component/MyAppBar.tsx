import {ReactElement} from "react";
import {AppBar, Avatar, IconButton, ListItemIcon, MenuItem, Toolbar, Tooltip, Typography} from "@mui/material";
import MyMenu from "./MyMenu";
import {deepPurple} from "@mui/material/colors";
import {close, selectUsername} from "../pages/login/SessionSlice";
import {Logout} from "@mui/icons-material";
import {useAppDispatch, useAppSelector} from "../hooks/Global";

export default function MyAppBar(): ReactElement {
    const username = useAppSelector(selectUsername)
    const dispatch = useAppDispatch()

    return (
        <AppBar position={"static"}>
            <Toolbar variant={"dense"}>
                <Typography variant={"h6"} color={"inherit"} component={"div"}>
                    GMT
                </Typography>
                <div style={{flexGrow: 1}}/>
                <MyMenu button={(handleClick) =>
                    <Tooltip title="Cuenta">
                        <IconButton
                            onClick={handleClick}
                            size="small"
                            sx={{ml: 2}}
                        >
                            <Avatar sx={{backgroundColor: deepPurple[500]}}>
                                {username && username[0].toUpperCase()}
                            </Avatar>
                        </IconButton>
                    </Tooltip>
                }
                        children={(handleClose) => [
                            <MenuItem onClick={handleClose(() => {
                                dispatch(close())
                            })}>
                                <ListItemIcon>
                                    <Logout fontSize="small"/>
                                </ListItemIcon>
                                Salir
                            </MenuItem>
                        ]}/>
            </Toolbar>
        </AppBar>
    )
}