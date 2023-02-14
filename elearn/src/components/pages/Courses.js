import { useEffect, useState } from "react";
import CourseCard from "../CourseCard";
import CoursesSearch from "../CoursesSearch";
const Courses = () => {
  const [courses, setCourses] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  useEffect(() => {
    const loader = async () => {
      const req = await fetch("http://localhost:8080/api/course/all",{
        method:"get",
      });
      const response = await req.json();
      setCourses(response);
    };
    loader();
  }, []);
  console.log(courses)
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
              <CourseCard key={course.id} course={course} />
            ))}
          </div>
        </div>
      )}
    </>
  );
};
export default Courses;
