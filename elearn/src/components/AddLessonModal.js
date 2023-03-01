import {useFormik} from "formik";
import * as Yup from "yup";
import {PostDataAuthenticated} from "../services/FetchDataService";
import {useAtom} from "jotai";
import state from "../state";
import {toast, ToastContainer} from "react-toastify";
import "react-toastify/dist/ReactToastify.css"

const AddLessonModal = ({courseId}) => {
    const [token] = useAtom(state.token)
    const extractId = (url) => {
        const regex = /(?:\?v=|\/embed\/|\/watch\?v=)([a-zA-Z0-9_-]{11})/;
        const match = url.match(regex)
        return match ? match[1] : null;
    }
    const formik = useFormik({
        initialValues: {
            title: "",
            url: ""
        }, validationSchema: Yup.object({
            title: Yup.string()
                .required("Required")
                .min(4, "Minimum 4 chars")
                .max(32, "Maximum 32 chars"),
            url: Yup.string()
                .required("Required")
                .matches(/^(?:https?:\/\/)?(?:www\.)?(?:youtube\.com\/(?:watch\?v=|embed\/)|youtu\.be\/)([a-zA-Z0-9_-]{11})/,
                    "Not valid format")
        }), onSubmit: async (values) => {
            console.log(values);
            const response = await PostDataAuthenticated(JSON.stringify(values), token, `lessons/add/${courseId}`)
            console.log(response);
            formik.resetForm({values: ""});
            toast.info(response.message,{position: toast.POSITION.TOP_RIGHT})
        }
    })

    return <>
        <div className="modal fade" id="addLessonModal" data-bs-backdrop="static" data-bs-keyboard="false"
             tabIndex="-1"
             aria-labelledby="addLessonModalLabel" aria-hidden="true">
            <div className="modal-dialog modal-dialog-centered">
                <div className="modal-content">
                    <div className="modal-header">
                        <h1 className="modal-title fs-5" id="addLessonModalLabel">Add Lesson</h1>
                        <button type="button" className="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div className="modal-body">
                        <form id="lessonForm" onSubmit={formik.handleSubmit} className="mb-4">
                            <label className="form-label">Title</label>
                            <input type="text" name="title" id="title" className="inputStyle form-control"
                                   value={formik.values.title}
                                   onBlur={formik.handleBlur}
                                   onChange={formik.handleChange}/>
                            {formik.touched.title && formik.errors.title && (
                                <p className="errors">{formik.errors.title}</p>
                            )}
                            <label className="form-label">VideoUrl</label>
                            <input type="text" name="url" id="url" className="inputStyle form-control"
                                   value={formik.values.url}
                                   onBlur={formik.handleBlur}
                                   onChange={formik.handleChange}/>
                            {formik.touched.url && formik.errors.url && (
                                <p className="errors">{formik.errors.url}</p>
                            )}
                        </form>
                        {!formik.errors.url && formik.values.url && (<div className="ratio ratio-21x9">Preview
                            (
                            <iframe src={`https://www.youtube.com/embed/${extractId(formik.values.url)}`}
                                    title="videoPreview" allowFullScreen></iframe>
                            )
                        </div>)}
                    </div>
                    <div className="modal-footer">
                        <button type="button" className="btn" data-bs-dismiss="modal">Close</button>
                        <button type="submit" className="btn" form="lessonForm">Submit</button>
                    </div>
                </div>
            </div>
        </div>
        <ToastContainer />
    </>
}
export default AddLessonModal