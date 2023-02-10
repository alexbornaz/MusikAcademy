import { useAtom } from "jotai";
import { useEffect, useState } from "react";

import state from "../../state";
import CourseCard from "../CourseCard";

const MyCourses = () => {
  const[userData] = useAtom(state.userData)
  const [courses, setCourses] = useState([]);
  useEffect(() => {
    const loader = async () => {
      const req = await fetch(`http://localhost:8080/api/user/${userData.sub}/enrolled`);
      const response = await req.json();
      setCourses(response);
      console.log(response)
    };
    loader();
  }, [userData.sub]);
  return (
    <>
      {courses && (
        <div className="container">
          <div className="row row-cols-6 gap-4">
            {courses.map((course) => (
              <CourseCard key={course.id} course={course} />
            ))}
          </div>
        </div>
      )}
    </>
  );
};
export default MyCourses;
