import { Link } from "react-router-dom";
import classes from "../../authenticationComponents/AuthForm.module.css"
import { useFormik } from "formik";
import * as Yup from "yup";
import AlterAuthBtns from "../../authenticationComponents/AlterAuthBtns";
const LoginForm = () => {
  const formik = useFormik({
    initialValues: {
      emailAddress: "",
      password: "",
    },
    validationSchema: Yup.object({
      emailAddress: Yup.string().email("Invalid email").required("Required"),
      password: Yup.string()
        .required("Required")
        .min(4, "Password is too short - minimum 4 characters")
        .max(32, "Password is too long maximum 32 characters"),
    }),
    onSubmit: (values) => {
      console.log(values);
    },
  });

  return (
    <>
      <h2 className="fw-bold mb-5 text-light">Welcome Back!</h2>
      <form onSubmit={formik.handleSubmit}>
        <div className="form-outline mb-4">
          <label className="form-label">Email address</label>
          <input
            id="emailAddress"
            name="emailAddress"
            type="email"
            className="form-control inputStyle"
            placeholder="john.doe@example.com"
            value={formik.values.emailAddress}
            onBlur={formik.handleBlur}
            onChange={formik.handleChange}
          />
          {formik.touched.emailAddress && formik.errors.emailAddress ? (
            <p className="errors">{formik.errors.emailAddress}</p>
          ) : null}
        </div>
        <div className="form-outline mb-4">
          <label className="form-label">Password</label>
          <input
            type="password"
            id="password"
            name="password"
            className="form-control inputStyle"
            value={formik.values.password}
            onBlur={formik.handleBlur}
            onChange={formik.handleChange}
          />
          {formik.touched.password && formik.errors.password ? (
            <p className="errors">{formik.errors.password}</p>
          ) : null}
        </div>
        <button
          type="submit"
          className={`btn btn-block mb-4 ${classes.submitBtn}`}
        >
          Log in
        </button>
        <AlterAuthBtns />
          <div className={classes.linkStyle}>
            <Link to="/register"> Don't have an account already?</Link>
          </div>
      </form>
    </>
  );
};
export default LoginForm;
