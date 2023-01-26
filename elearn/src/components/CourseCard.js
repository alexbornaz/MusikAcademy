
function CourseCard(props) {
  const cardSize= {
    width:'18rem'
  }
  return (
    <div className="card" style={cardSize} >
      <img className="card-img-top" src="..." alt="img" />
      <div className="card-body">
        <h3 className="card-title ">{props.course.title}</h3>
        <p className="card-text">{props.course.description}</p>
        <h5>
          Mentor:{props.course.creator.firstName}
          {props.course.creator.lastName}
        </h5>
        <button className="btn btn-primary"></button>
      </div>
    </div>
  );
}
export default CourseCard;
