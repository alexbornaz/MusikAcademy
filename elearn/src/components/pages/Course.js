import { useAtom } from "jotai";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import state from "../../state";
import AddLesson from "../buttons/AddLesson";
import StartCourse from "../buttons/StartCourse";

const Course = () => {
  const param = useParams();
  const id = param.courseId;
  const [userData] = useAtom(state.userData)
  const [course, setCourse] = useState();
  useEffect(() => {
    const loader = async () => {
      const req = await fetch(`http://localhost:8080/api/course/${id}`);
      const response = await req.json();
      setCourse(response);
    };
    loader();
  }, [id]);

  return (
    <>
      {course && (
        <div className="container">
          <div className="row">
            <div
              className="col d-flex"
              style={{
                borderBottom: "solid 1px black",
                marginBottom: "25px",
                alignItems: "baseline",
              }}
            >
              <h1 className="display-4 titleWithBtn">{course.title}</h1>
              {userData && userData.sub === course.creator.username ? <AddLesson /> : <StartCourse />}
            </div>
          </div>
          <div className="row">
            <div className="col-md-4">
              <img
                src={`/images/${course.image.substring(12)}`}
                className="rounded img-fluid"
                style={{ width: "350px", height: "650px" }}
                alt="course poster"
              />
            </div>
            <div className="col-8 mt-5">
              <div className="mb-5 d-flex">
                <h5 className="titleWithBtn mt-1">
                  Price:{" "}
                  <span>
                    {course.defaultPrice === 0 ? "Free" : `${course.defaultPrice} $`}
                  </span>
                </h5>
                <h5 style={{ marginLeft: "auto" }}>
                  Created by: {course.creator.firstName}{" "}
                  {course.creator.lastName}
                </h5>
              </div>
              <h4>Description</h4>
              <p style={{ wordBreak: "break-word" }}>{course.description}</p>
              <div className="mt-5">
                <h4>Requirements:</h4>
                {course.requirements}
              </div>
              <div className="mt-5">
                <h4>Course Outline</h4>
                {course.outline}
              </div>
            </div>
          </div>
        </div>
      )}
      ;
    </>
  );
};
export default Course;
