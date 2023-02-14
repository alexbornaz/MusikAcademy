import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {GetDataAuthenticated} from "../../services/FetchDataService";
import {useAtom} from "jotai";
import state from "../../state";
import classes from "./CourseCurriculum.module.css";

const CourseCurriculum = () => {
    const param = useParams();
    const [token] = useAtom(state.token)
    const [lessons, setLessons] = useState([])
    const [currentUrl, setCurrentUrl] = useState()
    const [currentTitle,setCurrentTitle] = useState()

    const extractId = (url) => {
        const regex = /(?:\?v=|\/embed\/|\/watch\?v=)([a-zA-Z0-9_-]{11})/;
        const match = url.match(regex)
        return match ? match[1] : null;
    }
    const handleChange = (e) => {
        const url = e.target.dataset.url
        const id = extractId(url)
        const title = e.target.dataset.title
        setCurrentTitle(title)
        setCurrentUrl(id)
    }
    useEffect(() => {
        (async () => {
            const data = await GetDataAuthenticated(`lessons/${param.courseId}`, token)
            setLessons(data)
            console.log(data)
        })();
    }, [param.courseId, token])
    return (
        <>
            {lessons.length > 0 && (
                <div className="d-flex flex-row mt-5">
                    <div className="flex-column me-5" style={{ maxWidth: "300px", borderRight : "solid 1px black"}}>
                        {(lessons.map((lesson,index) => (
                            <div key={lesson.id} className="mb-3">
                                <button className="btn" type="button" data-url={lesson.url} data-title={lesson.title}
                                        onClick={handleChange}>Lesson {index+1}</button>
                            </div>
                        )))}
                    </div>
                    <div className="col">
                        <h3>{currentTitle != null ? currentTitle : lessons[0].title}</h3>
                        <div className={`ratio ${classes.aspect}`}>
                            <iframe src={`https://www.youtube.com/embed/${currentUrl != null ? currentUrl : extractId(lessons[0].url)}`} title="lesson"
                                    allowFullScreen></iframe>
                        </div>
                    </div>
                </div>
            )
            }
        </>
    )
}
export default CourseCurriculum