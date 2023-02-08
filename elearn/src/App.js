import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./components/Layout";
import Home from "./components/pages/Home";
import Courses from "./components/pages/Courses";
import About from "./components/pages/About";
import Report from "./components/pages/Report";
import Login from "./components/pages/login/Login";
import Registration from "./components/pages/register/Registration";
import Course from "./components/pages/Course";
import UserPage from "./components/pages/UserPage";
import MyCourses from "./components/pages/MyCourses";
import CreateCourse from "./components/pages/CreateCourse";
import BecomeMentor from "./components/pages/BecomeMentor";
import state from "./state";
import { useEffect } from "react";
import jwtDecode from "jwt-decode";
import { useAtom } from "jotai";
import { PrivateRoutes } from "./PrivateRoutes";

function App() {
  const [,setUserData] = useAtom(state.userData);
  useEffect(() => {
      const token = localStorage.getItem("token")
      if (token != null ){
        setUserData(jwtDecode(token))
      };
    },[setUserData]);
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="courses">
            <Route index element={<Courses />} />
            <Route path="course/:courseId" element={<Course />} />
          </Route>
          <Route path="about" element={<About />} />
          <Route path="report" element={<Report />} />
          <Route path="user/:username" exact element={<PrivateRoutes />}>
            <Route index element={<UserPage />} />
            <Route exact path="enrolledCourses" element={<MyCourses />} />
            <Route exact path="create-course" element={<CreateCourse />} />
            <Route exact path="become-mentor" element={<BecomeMentor />} />
          </Route>
        </Route>
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Registration />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
