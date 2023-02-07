import { Link, useNavigate } from "react-router-dom";
import classes from "../../authenticationComponents/AuthForm.module.css";
import { useFormik } from "formik";
import * as Yup from "yup";
import AlterAuthBtns from "../../authenticationComponents/AlterAuthBtns";
import postAuth from "../../../services/AuthService";
import { useState } from "react";
import { useAtom } from "jotai";
import state from "../../../state";
import jwtDecode from "jwt-decode";
const LoginForm = () => {
  const [,setUserData] = useAtom(state.userData)
  const [,setToken] = useAtom(state.token)
  const navigate = useNavigate();
  const [error, setError] = useState("");
  const formik = useFormik({
    initialValues: {
      username: "",
      password: "",
    },
    validationSchema: Yup.object({
      username: Yup.string().required("Required"),
      password: Yup.string()
        .required("Required")
        .min(4, "Password is too short - minimum 4 characters")
        .max(32, "Password is too long maximum 32 characters"),
    }),
    onSubmit: async (values) => {
      const response = await postAuth(JSON.stringify(values), "/login");
      if (response.headers.get("Authorization")) {
        localStorage.setItem("token", response.headers.get("Authorization"));
        setToken(response.headers.get("Authorization"))
        setUserData(jwtDecode(response.headers.get("Authorization")))
        // window.location.href = "/";
        navigate("/");
      } else {
        setError(
          await response.json().then((data) => {
            return data.message;
          })
        );
      }
      formik.resetForm({ values: "" });
    },
  });

  return (
    <>
      <h2 className="fw-bold mb-5 text-light">Welcome Back!</h2>
      <form onSubmit={formik.handleSubmit}>
        <div className="form-outline mb-4">
          <label className="form-label">Username</label>
          <input
            id="username"
            name="username"
            type="text"
            className="form-control inputStyle"
            value={formik.values.username}
            onBlur={formik.handleBlur}
            onChange={formik.handleChange}
          />
          {formik.touched.username && formik.errors.username && (
            <p className="errors">{formik.errors.username}</p>
          )}
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
          {formik.touched.password && formik.errors.lastName && (
            <p className="errors">{formik.errors.lastName}</p>
          )}
        </div>
        {error && <p className="errors text-center">{error}</p>}
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
