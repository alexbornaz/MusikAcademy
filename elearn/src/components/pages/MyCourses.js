import {useAtom} from "jotai";
import {useEffect, useState} from "react";

import state from "../../state";
import CourseCard from "../CourseCard";
import {GetDataAuthenticated} from "../../services/FetchDataService";

const MyCourses = () => {
    const [userData] = useAtom(state.userData)
    const [token] = useAtom(state.token);
    const [courses, setCourses] = useState([]);
    useEffect(() => {
        (async () => {
            const data = await GetDataAuthenticated(`user/${userData.sub}/enrolled`, token)
            setCourses(data)
        })();
    }, [token, userData.sub]);
    return (
        <>
            {courses && (
                <div className="container">
                    <div className="row row-cols-6 gap-4">
                        {courses.map((course) => (
                            <CourseCard key={course.id} course={course}/>
                        ))}
                    </div>
                </div>
            )}
        </>
    );
};
export default MyCourses;
