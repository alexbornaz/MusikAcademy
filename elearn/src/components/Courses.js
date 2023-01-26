import React, {useState,useEffect} from "react";
import CourseCard from "./CourseCard";
function Courses(){
    const [courses,setCourses] = useState([])
    const fetchData = () =>{fetch('http://localhost:8080/api/courses')
    .then(response => response.json()).then(data=>setCourses(data))
    }
      useEffect(()=>{
        fetchData();
      },[])
      console.log(courses)
    return (courses.map((course)=> <CourseCard key={course.id}  course={course} />))
}
export default Courses