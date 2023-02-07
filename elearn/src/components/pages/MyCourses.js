import { useParams } from "react-router-dom";

const MyCourses = () => {
  const param = useParams();
  return <h1>{param.username}</h1>;
};
export default MyCourses;
