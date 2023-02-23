import {GetDataAuthenticated, PutDataAuthenticated} from "../../services/FetchDataService";
import {useAtom} from "jotai";
import state from "../../state";
import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import PaymentModal from "../PaymentModal";

const StartCourse = ({username, courseId, coursePrice}) => {
    const [token] = useAtom(state.token)
    const [enrolled, setEnrolled] = useState([])
    const [isReady, setIsReady] = useState(false)
    const [showModal, setShowModal] = useState(false)
    useEffect(() => {
        (async () => {
            const data = await GetDataAuthenticated(`user/${username}/enrolled/ids`, token)
            setEnrolled(data)
            setIsReady(true)
        })();
    }, [token, username]);
    const navigate = useNavigate();

    const addCourse = async () => {
        const req = await PutDataAuthenticated(`user/${username}/addCourse/${courseId}`, token)
        console.log(req)
        alert(req.message)
        navigate(`/user/${username}/courseCurriculum/${courseId}`)
    }

    const handleSubmit = async () => {
        if (coursePrice === 0) {
            await addCourse();
        } else {
            setShowModal(true)
        }
    }

    const handleResume = () => {
        navigate(`/user/${username}/courseCurriculum/${courseId}`)
    }

    return (
        <>
            {isReady && enrolled.includes(courseId) ? (
                    <button type="button" className="btn" style={{marginLeft: "auto"}} onClick={handleResume}>
                        Resume Course
                    </button>) :
                (<button type="button" className="btn" style={{marginLeft: "auto"}} onClick={handleSubmit}>
                    Start Course
                </button>)}
            <PaymentModal showModal={showModal} setShowModal={setShowModal} courseId={courseId} addCourse={addCourse}/>
        </>
    );
};
export default StartCourse
