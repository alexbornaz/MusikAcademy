import { useState } from "react";
import { Outlet, Link } from "react-router-dom";
import AuthBtns from "./authenticationComponents/AuthBtns";
import Footer from "./Footer";
import logo from "./images/MusikAcademyLogo.png";

const Layout = ({ children }) => {
  const [isDark, setDark] = useState("dark");
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
        <AuthBtns />
        <div>
          <button type="button" className="darkLightBtn" onClick={toggleDark}>
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
