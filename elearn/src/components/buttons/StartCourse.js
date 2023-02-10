const StartCourse = ({username,courseId}) => {
  const handleSubmit = async () => {
      const req = await fetch(`http://localhost:8080/api/user/${username}/addCourse/${courseId}`,{
        method:"put"
      });
      const response = await req.json();
      console.log(response)
  }
  return (
    <>
      <button type="button" className="btn" style={{ marginLeft: "auto" }} onClick={handleSubmit}>
        Start Course
      </button>
    </>
  );
};
export default StartCourse
