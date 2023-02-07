import { atom } from "jotai"
import jwtDecode from "jwt-decode"

const state = {
    userData:atom(localStorage.getItem("token") != null ? jwtDecode(localStorage.getItem("token")):null),
    token:atom(localStorage.getItem("token"))
}

export default state