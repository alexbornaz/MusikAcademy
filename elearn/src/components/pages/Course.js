const Course = () => {
  return (
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
          <h1 className="display-4 titleWithBtn">Music Learning Course</h1>
          <button type="button" className="btn" style={{ marginLeft: "auto" }}>
            Start Course
          </button>
        </div>
      </div>
      <div className="row">
        <div className="col-md-4">
          <img
            src="https://via.placeholder.com/350x650"
            className="rounded img-fluid"
            alt="Instructor"
          />
        </div>
        <div className="col-8 mt-5">
          <div className="mb-5 d-flex">
            <h5 className="titleWithBtn">Price: <span>Free</span></h5>
            <h5 style={{ marginLeft: "auto" }}>Created by: John Doe</h5>
          </div>
          <h4>Description</h4>
          <p style={{ wordBreak: "break-word" }}>
            Lorem Ipsum is simply dummy text of the printing and typesetting
            industry. Lorem Ipsum has been the industry's standard dummy text
            ever since the 1500s, when an unknown printer took a galley of type
            and scrambled it to make a type specimen
            book.asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdaaaaaaaaaaaaaaasdasdadasdasdadasdasdasdasdasdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaasdddddddddddddddddddddddddddddddddddddddddd
          </p>
          <div className="mt-5">
            <h4>Requirements:</h4>
            <ul>
              <li>Guitar</li>
              <li>Headphones</li>
              <li>Tuner</li>
            </ul>
          </div>
          <div className="mt-5">
            <h4>Course Outline</h4>
            <ul>
              <li>Introduction to music theory</li>
              <li>Scales and chords</li>
              <li>Rhythm and timing</li>
              <li>Playing songs on your instrument</li>
              <li>Music composition and arrangement</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
};
export default Course;
