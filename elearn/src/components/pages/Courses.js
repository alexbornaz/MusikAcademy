import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import CoursesSearch from "../CoursesSearch";
const Courses = () => {
  const [courses, setCourses] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  useEffect(() => {
    const loader = async () => {
      const req = await fetch("http://localhost:8080/api/course/all");
      const response = await req.json();
      setCourses(response);
    };
    loader();
  }, []);

  const filteredCourses = courses.filter((course) => {
    return (
      course.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
      course.description.toLowerCase().includes(searchTerm.toLowerCase())
    );
  });

  return (
    <>
      <CoursesSearch setSearchTerm={setSearchTerm} />
      {filteredCourses && (
        <div className="container">
          <div className="row row-cols-6 gap-4">
            {filteredCourses.map((course) => (
              <Link
                key={course.id}
                to={`course/${course.id}`}
                className="text-reset px-0 mx-1 my-3"
              >
                <div className="card h-100">
                  <img
                    src={`/images/${course.image.substring(12)}`}
                    className="card-img-top h-100"
                    alt="..."
                  />
                  <div className="card-body ">
                    <h5 className="card-title">{course.title}</h5>
                    <p className="card-text text-truncate">
                      {course.description}
                    </p>
                  </div>
                </div>
              </Link>
            ))}
          </div>
        </div>
      )}
    </>
  );
};
export default Courses;
