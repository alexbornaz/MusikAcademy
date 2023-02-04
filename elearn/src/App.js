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

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="courses">
            <Route index element={<Courses />} />
            <Route path="course/:id" element={<Course />} />
          </Route>
          <Route exact path="about" element={<About />} />
          <Route exact path="report" element={<Report />} />
        </Route>
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Registration />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
