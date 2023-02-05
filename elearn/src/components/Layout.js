import { useState } from "react";
import { Outlet, Link } from "react-router-dom";
import Footer from "./Footer";
import logo from "./images/MusikAcademyLogo.png";

const Layout = ({ children }) => {
  const [isDark, setDark] = useState(true);
  const toggleDark = () => {
    setDark(!isDark);
    isDark
      ? (document.body.dataset.bsTheme = "dark")
      : (document.body.dataset.bsTheme = "light");
  };
  return (
    <>
      <nav className="navbar navbar-expand-lg">
        <div className="container-fluid">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li>
              <Link className="navbar-brand" to="/">
                <img
                  src={logo}
                  className="d-inline-block align-text-center"
                  alt="logo"
                  width="50"
                />
                MusikAcademy
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/courses">
                All Courses
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/about">
                About
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/report">
                Report an issue
              </Link>
            </li>
          </ul>
       </div>
       {localStorage.getItem("token") != null ? (<button className="btn" type="button">Logout</button>) :
        (<div className="d-flex"><Link to="/register" className="btn  btn-block">
          SignUp
        </Link>
        <Link to="/login" className="btn  mx-2 btn-block">
          Login
        </Link></div>)}
        <div>
          <button type="button" onClick={toggleDark}>
            {isDark ? (
              <i className="fas fa-moon"></i>
            ) : (
              <i className="fas fa-lightbulb"></i>
            )}
          </button>
        </div>
      </nav>
      <main>{children}</main>
      <Outlet />
      <Footer />
    </>
  );
};

export default Layout;
