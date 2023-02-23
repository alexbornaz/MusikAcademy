import {Navigate, Outlet} from "react-router-dom";

export const PrivateRoutes = () => {
    let token = localStorage.getItem("token") == null ? false : true;
    return (
        <>
            {token ? <Outlet/> : <Navigate to="/login"/>}
        </>

    )

}