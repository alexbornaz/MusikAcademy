import AddLessonModal from "../AddLessonModal";

const AddLesson = ({courseId}) => {
    return (
        <>
            <button type="button" className="btn" style={{marginLeft: "auto"}} data-bs-toggle="modal"
                    data-bs-target="#addLessonModal">
                + Add Lesson
            </button>
            <AddLessonModal courseId={courseId}/>
        </>
    );
};
export default AddLesson