import {Navigate, Outlet} from "react-router-dom";

export const PrivateRoute = () => {
    let isAuthenticated = localStorage.getItem("token") == null ? false : true;
    return (
        <>
            {isAuthenticated ? <Navigate to="/"/> : <Outlet/>}
        </>

    )

}