import logo from "../images/MusikAcademyLogo.png";
import {useFormik} from "formik";
import * as Yup from "yup";
import {useAtom} from "jotai";
import state from "../../state";
import {PostDataAuthenticated} from "../../services/FetchDataService";
import {useNavigate} from "react-router-dom";
import {toast} from "react-toastify";

const BecomeMentor = () => {
    const [userData] = useAtom(state.userData)
    const [token] = useAtom(state.token)
    const navigate = useNavigate()
    const formik = useFormik({
        initialValues: {
            application: ""
        },
        validationSchema: Yup.object({
            application: Yup.string().required("Required").min(24, "Minimum 24 chars").max(300, "Maximum 300 chars")
        }),
        onSubmit: async (values) => {
            const payload = JSON.stringify({application: values.application, username: userData.sub})
            const response = await PostDataAuthenticated(payload, token, "user/applyMentor")
            toast.info(response.message,{
                position:toast.POSITION.TOP_RIGHT
            })
            navigate("/")
        }
    })
    return (

        <div className="container my-4">
            <div className="row" style={{boxShadow: "0 0 20px #976734"}}>
                <div className="col-sm-6 px-0 d-none d-sm-block">
                    <img src="https://images.news18.com/ibnlive/uploads/2021/08/1628486381_musiclearning.png"
                         alt="poster" className="w-100 vh-100"
                         style={{objectFit: "cover", objectPosition: "left"}}/>
                </div>
                <div className="col-sm-6 text-black">

                    <div className="px-5 ms-xl-4 text-center">
                        <img
                            src={logo}
                            className="d-inline-block align-text-center"
                            alt="logo"
                            width="150"
                        />
                        <span className="h3">MusikAcademy</span>
                    </div>

                    <div className="h-custom-2 mt-5 py-4 mx-3">
                        <form onSubmit={formik.handleSubmit}>

                            <h3 className=" fw-normal mb-3 pb-3"
                                style={{position: "relative", letterSpacing: "1px"}}>Mentor
                                Application</h3>

                            <div className="row form-outline mb-4">
                                <label className="form-label" aria-labelledby="#application">A few words about
                                    yourself:</label>
                                <textarea id="application" className="form-control inputStyle" rows="10"
                                          value={formik.values.application}
                                          onChange={formik.handleChange}
                                          onBlur={formik.handleBlur}/>
                            </div>
                            <div className="row my-1">
                                {formik.touched.application && formik.errors.application && (
                                    <p className="errors"
                                       style={{position: 'absolute', width: "400px"}}>{formik.errors.application}</p>
                                )}
                            </div>
                            <div className="row justify-content-end my-5">
                                <button type="submit" className="btn">Apply</button>
                            </div>
                        </form>

                    </div>

                </div>
            </div>
        </div>)
};
export default BecomeMentor;
