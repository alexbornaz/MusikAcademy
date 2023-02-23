import {Navigate} from "react-router-dom";
import {useEffect, useState} from "react";
import {useAtom} from "jotai/index";
import state from "../../state";
import jwtDecode from "jwt-decode";

const Oauth2RedirectHandler = () => {
    const [validToken, setIsValidToken] = useState(false)
    const [, setUserData] = useAtom(state.userData);
    const [, setToken] = useAtom(state.token);
    const par = new URLSearchParams(window.location.search)
    const token = par.get("token")
    useEffect(() => {
        if (token != null) {
            localStorage.setItem("token", token)
            setToken(token)
            setUserData(jwtDecode(token))
            setIsValidToken(true)
        }
    }, [token, setUserData, setIsValidToken, setToken])

    return (
        <>
            {validToken ? <Navigate to="/"/> : <Navigate to="/login"/>}
        </>
    )
}
export default Oauth2RedirectHandler