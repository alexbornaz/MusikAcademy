import { atom } from "jotai"
import jwtDecode from "jwt-decode"

const state = {
    userData:atom(localStorage.getItem("token") != null ? jwtDecode(localStorage.getItem("token")):null),
    token:atom(localStorage.getItem("token")),
    theme:atom(sessionStorage.getItem("theme") === "true" ? true : false)
    
}

export default state