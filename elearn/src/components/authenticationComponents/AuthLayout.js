import classes from "./AuthLayout.module.css";
import HomeBtn from "./HomeBtn";
import logo from "../images/MusikAcademyLogo.png";
const AuthLayout = ({ children }) => {
  return (
    <div className="text-center">
      <div className={`p-5 bg-image ${classes.bgImage} fs-1 text-dark`}>
      <img
                    src={logo}
                    className="d-inline-block align-text-center"
                    alt="logo"
                    width="100"
                  />
                  MusikAcademy
      </div>
      <div className={`card mx-4 mx-md-5 shadow-5-strong ${classes.bgPage}`}>
        <HomeBtn />
        <div className="card-body py-5 px-md-5">
          <div className="row d-flex justify-content-center">
            <div className="col-lg-6">{children}</div>
          </div>
        </div>
      </div>
    </div>
  );
};
export default AuthLayout;
