import { Link } from "react-router-dom";
import Profile from "../Profile";

const AuthBtns = () => {
  const token = localStorage.getItem("token");
  return token != null ? (
    <Profile />
  ) : (
    <div className="d-flex">
      <Link to="/register" className="btn  btn-block">
        SignUp
      </Link>
      <Link to="/login" className="btn  mx-2 btn-block">
        Login
      </Link>
    </div>
  );
};
export default AuthBtns;
