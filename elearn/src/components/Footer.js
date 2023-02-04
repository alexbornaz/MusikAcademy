import { Link } from "react-router-dom";
import classes from "./Footer.module.css";
import { useFormik } from "formik";
import * as Yup from 'yup'

const Footer = () => {
  const formik= useFormik({
    initialValues:{
      emailAddress:""
    },
    validationSchema:Yup.object({
      emailAddress:Yup.string().email("Invalid email").required("Required")
    }),
    validateOnChange: false,
    validateOnBlur:false,
    onSubmit: (values) => {
      console.log(values)
      formik.resetForm({values: ""})
    }
  })
  return (
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
                      id="emailAddress"
                      name="emailAddress"
                      className="form-control inputStyle"
                      placeholder="Email address ex.john.doe@example.com"
                      value={formik.values.emailAddress}
                      onChange={formik.handleChange}
                      onBlur={formik.handleBlur}
                    />
                    {formik.touched && formik.errors.emailAddress ? (
              <p className="errors">{formik.errors.emailAddress}</p>
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
          <Link className="text-white" to="/">
            MusikAcademy.com
          </Link>
        </div>
      </footer>
    </div>
  );
};
export default Footer;
