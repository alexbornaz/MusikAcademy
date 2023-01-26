import { Outlet, Link } from "react-router-dom";
function Layout({ children }) {
  return (
    <>
      <nav className="navbar navbar-expand-lg bg-body-tertiary ">
        <div className="container-fluid">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <Link className="nav-link" to="/">
                Home
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/courses">
                Courses
              </Link>
            </li>
          </ul>
        </div>
      </nav>
      <main>{children}</main>
      <Outlet />
    </>
  );
}

export default Layout;
