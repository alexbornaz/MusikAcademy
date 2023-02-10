import { Link } from "react-router-dom";

const CourseCard = ({ course }) => {
  return (
    <>
      <Link
        to={`/courses/course/${course.id}`}
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
            <p className="card-text text-truncate">{course.description}</p>
          </div>
        </div>
      </Link>
    </>
  );
};
export default CourseCard;
