import AlterAuthBtns from "../../authenticationComponents/AlterAuthBtns";
import classes from "../../authenticationComponents/AuthForm.module.css";
import { useFormik } from "formik";
import * as Yup from "yup";

const RegistrationForm = () => {
  const formik = useFormik({
    initialValues: {
      firstName: "",
      lastName: "",
      username: "",
      emailAddress: "",
      password: "",
      passwordRepeat: "",
      accType: "",
    },
    validationSchema: Yup.object({
      firstName: Yup.string()
        .required("Required")
        .min(3, "Minumum 3 chars")
        .max(30, "Maximum 30 chars")
        .matches(/^[a-z]+$/i, "Name can only contain letters."),
      lastName: Yup.string()
        .required("Required")
        .min(3, "Minumum 3 chars")
        .max(30, "Maximum 30 chars")
        .matches(/^[a-z]+$/i, "Name can only contain letters."),
      username: Yup.string()
        .required("Required")
        .min(4, "Minumum 3 chars")
        .max(16, "Maximum 30 chars")
        .matches(/^[a-z0-9]+$/i),
      emailAddress: Yup.string().email("Invalid email").required("Required"),
      password: Yup.string()
        .required("Required")
        .min(8, "Pasword must be 8 or more characters")
        .matches(
          /(?=.*[a-z])(?=.*[A-Z])\w+/,
          "Password ahould contain at least one uppercase and lowercase character"
        )
        .matches(/\d/, "Password should contain at least one number")
        .matches(
          /[`!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?~]/,
          "Password should contain at least one special char"
        ),
      passwordRepeat: Yup.string().oneOf(
        [Yup.ref("password"), null],
        "Passwords must match"
      ),
      accType: Yup.string().required("Required"),
    }),
    onSubmit: (values) => {
      console.log(values);
    },
  });
  return (
    <>
      <h2 className="fw-bold mb-5 text-light">Register</h2>
      <form onSubmit={formik.handleSubmit}>
        <div className="row">
          <div className="col-md-6 mb-4">
            <div className="form-outline">
              <label className="form-label">First name</label>
              <input
                type="text"
                id="firstName"
                name="firstName"
                className="form-control inputStyle"
                value={formik.values.firstName}
                onBlur={formik.handleBlur}
                onChange={formik.handleChange}
              />
              {formik.touched.firstName && formik.errors.firstName ? (
                <p className="errors">{formik.errors.firstName}</p>
              ) : null}
            </div>
          </div>
          <div className="col-md-6 mb-4">
            <div className="form-outline">
              <label className="form-label">Last name</label>
              <input
                type="text"
                id="lastName"
                name="lastName"
                className="form-control inputStyle"
                value={formik.values.lastName}
                onBlur={formik.handleBlur}
                onChange={formik.handleChange}
              />
              {formik.touched.lastName && formik.errors.lastName ? (
                <p className="errors">{formik.errors.lastName}</p>
              ) : null}
            </div>
          </div>
        </div>
        <div className="row">
          <div className="col-md-6 mb-4">
            <label className="form-label">Username</label>
            <input
              type="text"
              id="username"
              name="username"
              className="form-control inputStyle"
              value={formik.values.username}
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
            />
            {formik.touched.username && formik.errors.username ? (
              <p className="errors">{formik.errors.username}</p>
            ) : null}
          </div>
          <div className="col-md-6 mb-4">
            <label className="form-label">Email address</label>
            <input
              type="email"
              id="emailAddress"
              name="emailAddress"
              className="form-control inputStyle"
              value={formik.values.emailAddress}
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
            />
            {formik.touched.emailAddress && formik.errors.emailAddress ? (
              <p className="errors">{formik.errors.emailAddress}</p>
            ) : null}
          </div>
        </div>
        <div className="row">
          <div className="col-md-6 mb-4">
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
          <div className="col-md-6 mb-4">
            <label className="form-label">Repeat Password</label>
            <input
              type="password"
              id="passwordRepeat"
              name="passwordRepeat"
              className="form-control inputStyle"
              value={formik.values.passwordRepeat}
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
            />
            {formik.touched.passwordRepeat && formik.errors.passwordRepeat ? (
              <p className="errors">{formik.errors.passwordRepeat}</p>
            ) : null}
          </div>
        </div>
        <div className="form-outline mb-4 d-flex justify-content-center ">
          <label className="form-label mt-2">Type of Account:</label>
          <select
            id="accType"
            name="accType"
            className={`form-select ${classes.selectOption}`}
            value={formik.values.accType}
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
          >
            <option value="">Select type </option>
            <option value="mentor">Mentor</option>
            <option value="student">Student</option>
          </select>
          {formik.touched.accType && formik.errors.accType ? (
            <p className="errors">{formik.errors.accType}</p>
          ) : null}
        </div>
        <button
          type="submit"
          className={`btn btn-block mb-4 ${classes.submitBtn}`}
        >
          Sign up
        </button>
        <AlterAuthBtns />
      </form>
    </>
  );
};
export default RegistrationForm;
