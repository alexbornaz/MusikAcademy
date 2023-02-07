import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
const Courses = () => {
  const [courses, setCourses] = useState([]);
  useEffect(() => {
    const loader = async () => {
      const req = await fetch("http://localhost:8080/api/course/all");
      const response = await req.json();
      setCourses(response);
      console.log("merge")
    };
    loader();
  }, []);
  return (
    <>
      <h1>Courses</h1>
      <div className="container">
        <div className="row row-cols-4">
          {courses.map((course) => (
            <Link key={course.id} to={`course/${course.id}`}>
              <div className="card mb-5" style={{height:"250px"}}>
                <img
                  src={course.image.substring(12)}
                  className="card-img-top"
                  alt="..."
                />
                <div className="card-body">
                  <h5 className="card-title">{course.title}</h5>
                  <p className="card-text">{course.description}</p>
                </div>
              </div>
            </Link>
          ))}
        </div>
      </div>
    </>
  );
};
export default Courses;
