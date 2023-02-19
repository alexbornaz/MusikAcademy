// import {useEffect} from "react";
// import postAuth from "../../services/AuthService";
// import jwtDecode from "jwt-decode";
// import {useAtom} from "jotai";
// import state from "../../state";
// import {useNavigate} from "react-router-dom";


const GoogleAuthBtn = () => {
    // const [, setUserData] = useAtom(state.userData);
    // const [, setToken] = useAtom(state.token);
    // const navigate = useNavigate();
    // function handleCallbackResponse(response) {
    //     const payload = JSON.stringify({"token": response.credential})
    //     console.log(response)
    //     postAuth(payload, "/google/login").then((data) => {
    //         if (data.headers.get("Authorization")){
    //             localStorage.setItem("token", data.headers.get("Authorization"));
    //             setToken(data.headers.get("Authorization"));
    //             setUserData(jwtDecode(data.headers.get("Authorization")));
    //             navigate("/");
    //         }else {
    //             console.log(data)
    //         }
    //             }).catch((error)=>{
    //                 console.log(error)
    //     })
    // }

    // useEffect(() => {
    //     /* global google */
    //     google.accounts.id.initialize({
    //         client_id: process.env.REACT_APP_GOOGLE_ID,
    //         callback: handleCallbackResponse
    //     });
    //     google.accounts.id.renderButton(
    //         document.getElementById("googleLogin"), {
    //             theme: "outline", size: "large"
    //         }
    //     )
    // })
    return (
        <div>
            <p>or sign in with:</p>
            {/*<div id="googleLogin" style={{display: "flex", justifyContent: "center"}}></div>*/}
            <a href="http://localhost:8080/oauth2/authorize/google" className="btn btn-floating mx-2">
                <i className="fab fa-google"></i>
            </a>
        </div>
    );
};
export default GoogleAuthBtn;
