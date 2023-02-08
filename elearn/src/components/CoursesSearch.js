import { useState } from "react";
import classes from "./CourseSearch.module.css";
const CoursesSearch = ({ setSearchTerm }) => {
  const [searchTerm, setSearchTermState] = useState("");
  const handleChange = (event) => {
    setSearchTermState(event.target.value);
    setSearchTerm(event.target.value);
  };
  return (
    <div
      className={`container-fluid d-flex align-items-center flex-column ${classes.wrapper}`}
    >
      <div className="col">
        <h1 className={`text-center text-light ${classes.title}`}>Courses</h1>
      </div>
      <div className="col">
        <input
          className="form-control inputStyle"
          style={{ width: "auto" }}
          type="text"
          name="searchBar"
          id="search"
          placeholder="Search for course,keywords"
          value={searchTerm}
          onChange={handleChange}
        />
      </div>
    </div>
  );
};
export default CoursesSearch;
