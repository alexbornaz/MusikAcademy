import Carousel from "../Carousel/Carousel";
import "./Home.css";
import {useEffect, useState} from "react";
import {GetData} from "../../services/FetchDataService";
import CourseCard from "../CourseCard";
import {ToastContainer} from "react-toastify";

function Home() {
    const [courses, setCourses] = useState([])
    useEffect(() => {
        (async () => {
            const data = await GetData("course/topCourses")
            setCourses(data)
        })()
    }, [])
    return (
        <>
            <Carousel/>
            <section className="my-5">
                <div className="container">
                    <h1 className="fw-light">Top Courses</h1>
                    <div className="row row-cols-6 gap-4">
                        {courses && courses.map((course) => (
                            <CourseCard key={course.id} course={course}/>
                        ))}
                    </div>
                </div>
            </section>
            <ToastContainer />
        </>
    );
}

export default Home;
