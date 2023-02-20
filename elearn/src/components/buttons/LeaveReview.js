import {useState} from "react";
import {useFormik} from "formik";
import * as Yup from "yup";
import {PostDataAuthenticated} from "../../services/FetchDataService";
import {useAtom} from "jotai";
import state from "../../state";

const LeaveReview = ({username,courseId,setReviewed}) => {
    const [token] = useAtom(state.token)
    const [showInput, setShowInput] = useState(false)
    const formik = useFormik({
        initialValues:{
            reviewText : ""
        },
        validationSchema:Yup.object({
            reviewText:Yup.string().required("Required").min(4, " Minimum 4 characters")
                .max(144, "Maximum 144 characters"),
        }),
        onSubmit: async (values) =>{
            const payload = JSON.stringify({reviewText :values.reviewText,username:username,courseId:courseId } )
            const response = await PostDataAuthenticated(payload,token,"reviews/add")
            console.log(await  response)
            setShowInput(false)
            setReviewed(true)
        }

    })
    return (<>
            <button type="button" className="btn mb-3" onClick={()=> setShowInput(!showInput)}>Leave a review</button>
            {showInput && (<form onSubmit={formik.handleSubmit}>
                <input
                    id="reviewText"
                    name="reviewText"
                    type="text"
                    placeholder="Write here your review"
                    className="form-control inputStyle"
                    value={formik.values.reviewText}
                    onBlur={formik.handleBlur}
                    onChange={formik.handleChange}
                />
                {formik.touched.reviewText && formik.errors.reviewText && (
                    <p className="errors">{formik.errors.reviewText}</p>
                )}
                <button type="submit" className="btn my-2">Submit</button>
                </form>)}
        </>)
}
export default LeaveReview