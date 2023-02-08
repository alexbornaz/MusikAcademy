import { Navigate, Outlet } from "react-router-dom";

export const PrivateRoutes = () => {
    let  user = localStorage.getItem("token") == null ? false : true;
    return (
        <>
            {user ? <Outlet  /> : <Navigate to="/login" />}
        </>

    )

}