import {PutDataAuthenticated} from "../../services/FetchDataService";
import {useAtom} from "jotai";
import state from "../../state";
import {useNavigate} from "react-router-dom";

const StartCourse = ({username,courseId}) => {
    const [token] = useAtom(state.token)
    const navigate = useNavigate();
  const handleSubmit = async () => {
      const req = await PutDataAuthenticated(`user/${username}/addCourse/${courseId}`,token)
      console.log(req)
      navigate(`/user/${username}/courseCurriculum/${courseId}`)
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
