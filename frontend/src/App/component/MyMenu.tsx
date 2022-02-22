import {cloneElement, MouseEvent, ReactElement, useState} from "react";
import {Menu} from "@mui/material";

interface MyMenuProps {
    button: (handleClick: (event: MouseEvent) => void) => ReactElement,
    children: (handleClose: (cb?: () => void) => () => void) => Array<ReactElement>
}

export default function MyMenu({button, children}: MyMenuProps): ReactElement {
    const [anchorEl, setAnchorEl] = useState<null | Element>(null);
    const open = Boolean(anchorEl);
    const handleClick = (event: MouseEvent) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = (cb?: () => void) => () => {
        cb?.()
        setAnchorEl(null);
    };
    return (
        <div>
            {button(handleClick)}
            <Menu
                anchorEl={anchorEl}
                id="account-menu"
                open={open}
                onClose={handleClose()}
                onClick={handleClose()}
                PaperProps={{
                    elevation: 0,
                    sx: {
                        overflow: 'visible',
                        filter: 'drop-shadow(0px 2px 8px rgba(0,0,0,0.32))',
                        mt: 1.5,
                        '& .MuiAvatar-root': {
                            width: 32,
                            height: 32,
                            ml: -0.5,
                            mr: 1,
                        },
                        '&:before': {
                            content: '""',
                            display: 'block',
                            position: 'absolute',
                            top: 0,
                            right: 14,
                            width: 10,
                            height: 10,
                            backgroundColor: 'background.paper',
                            transform: 'translateY(-50%) rotate(45deg)',
                            zIndex: 0,
                        },
                    },
                }}
                transformOrigin={{horizontal: 'right', vertical: 'top'}}
                anchorOrigin={{horizontal: 'right', vertical: 'bottom'}}
            >
                {children(handleClose).map((child, index) => cloneElement(child, {key: index}))}
            </Menu>
        </div>
    )
}