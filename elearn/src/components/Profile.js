import { useAtom } from "jotai";
import {useState } from "react";
import { Link } from "react-router-dom";
import state from "../state";
import classes from "./Profile.module.css";
const Profile = () => {
  const [userData] = useAtom(state.userData)
  const role = userData.rol[0]
  const [isOpen, setIsOpen] = useState(false);
  const handleLogout = () => {
    localStorage.removeItem("token");
    window.location.href = "/";
  };
  return (
    userData && (<div className="nav-item dropdown mx-3 ">
      <button
        id="profileDropdown"
        className="dropdown-toggle dropdown-toggle-split rounded-circle border d-flex justify-content-center align-items-center"
        style={{ width: "40px", height: "40px" }}
        type="button"
        data-bs-toggle="dropdown"
        aria-expanded={isOpen}
        onClick={() => setIsOpen(!isOpen)}
      >
        <i className="fas fa-user" style={{ color: "rgb(75, 44, 3)" }}></i>
      </button>
      <ul
        className={`${classes.drop} dropdown-menu ${isOpen ? "show" : ""}`}
        aria-labelledby="profileDropdown"
      >
        <li className={`dropdown-item ${classes.menuItem}`}><span className="ms-5">{userData.sub}</span></li>
        <li><hr className="dropdown-divider" /></li>
        <li>
          <Link
            className={`dropdown-item ${classes.menuItem}`}
            to={`user/${userData.sub}/enrolledCourses`}
            onClick={() => setIsOpen(!isOpen)}
          >
            My Courses
          </Link>
        </li>
        {role !== "mentor" ? (
          <li>
            <Link
              className={`dropdown-item ${classes.menuItem}`}
              onClick={() => setIsOpen(!isOpen)}
              to={`user/${userData.sub}/become-mentor`}
            >
              Become a Mentor
            </Link>
          </li>
        ) : (
          <li>
            <Link
              className={`dropdown-item ${classes.menuItem}`}
              onClick={() => setIsOpen(!isOpen)}
              to={`user/${userData.sub}/create-course`}
            >Create Course</Link>
          </li>
        )}
        <li>
          <button
            className={`dropdown-item ${classes.menuItem}`}
            onClick={handleLogout}
            type="button"
          >
            Logout
          </button>
        </li>
      </ul>
    </div>)
  );
};
export default Profile;
