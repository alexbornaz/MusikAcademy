import {Link} from "react-router-dom";
import classes from "./Footer.module.css";
import {useFormik} from "formik";
import * as Yup from 'yup'
import {PostData} from "../services/FetchDataService";
import InfoModal from "./InfoModal";
import {useState} from "react";

const Footer = () => {
    const [message,setMessage] = useState("")
    const [showModal,setShowModal] = useState(false)
    const formik = useFormik({
        initialValues: {
            email: ""
        },
        validationSchema: Yup.object({
            email: Yup.string().email("Invalid email").required("Required")
        }),
        validateOnChange: false,
        validateOnBlur: false,
        onSubmit: async (values) => {
            const response = await PostData("newsletter/subscribe", JSON.stringify(values))
            if (response.message) {
                console.log(response.message)
                setMessage(response.message)
                setShowModal(true)
            }
            formik.resetForm({values: ""})
        }
    })
    return (

        <>
        <div className={`container-fluid mt-auto ${classes.style}`}>
            <footer className="text-center">
                <div className="container p-4 pb-0">
                    <section className="">
                        <form onSubmit={formik.handleSubmit
                        }>
                            <div className="row d-flex justify-content-center">
                                <div className="col-auto">
                                    <p className="pt-2">
                                        <strong>Sign up for our newsletter</strong>
                                    </p>
                                </div>
                                <div className="col-md-5 col-12">
                                    <div className="form-outline form-white mb-4">
                                        <input
                                            type="email"
                                            id="email"
                                            name="email"
                                            className="form-control inputStyle"
                                            placeholder="Email address ex.john.doe@example.com"
                                            value={formik.values.email}
                                            onChange={formik.handleChange}
                                            onBlur={formik.handleBlur}
                                        />
                                        {formik.touched && formik.errors.email ? (
                                            <p className="errors">{formik.errors.email}</p>
                                        ) : null}
                                    </div>
                                </div>
                                <div className="col-auto">
                                    <button type="submit" className="btn btn-outline-light mb-4">
                                        Subscribe
                                    </button>
                                </div>
                            </div>
                        </form>
                    </section>
                </div>
                <div className="text-center p-2">
                    © 2023 Copyright:
                    <Link to="/">
                        MusikAcademy.com
                    </Link>
                </div>
            </footer>
        </div>
            {message &&<InfoModal show={showModal} onHide={()=> setShowModal(false)} title="Subscription" message={message}/>}
        </>
    );
};
export default Footer;
