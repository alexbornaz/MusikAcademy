import { useFormik } from "formik";
import { useAtom } from "jotai";
import { useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";
import * as Yup from "yup";
import { PostDataAuthenticated } from "../../services/FetchDataService";
import state from "../../state";

const CreateCourse = () => {
  const navigate = useNavigate();
  const [paid, setPaid] = useState(false);
  const [userData] = useAtom(state.userData);
  const handlePay = (e) => {
    if (e.target.value === "1") {
      setPaid(true);
    } else {
      setPaid(false);
    }
  };
  const formik = useFormik({
    initialValues: {
      title: "",
      description: "",
      requirements: "",
      outline: "",
      price: 0,
      creatorUsername: userData.sub,
      image: "",
    },
    validationSchema: Yup.object({}),
    onSubmit: async (values) => {
      console.log(values);
      const jsoned = JSON.stringify(values);
      const response = await PostDataAuthenticated(
        jsoned,
        localStorage.getItem("token"),
        "course/create"
      );
      if (response) {
        console.log(response)
        navigate(`/courses/course/${response.id}`);
      }
    },
  });
  return (
    <>
      {userData.rol.includes("mentor") ? (
        <div className="container">
          <div className="row">
            <div
              className="col d-flex"
              style={{
                borderBottom: "solid 1px black",
                marginBottom: "25px",
                alignItems: "baseline",
              }}
            >
              <h1 className="display-4 titleWithBtn">Create Your Course</h1>
            </div>
          </div>
          <div className="row">
            <form onSubmit={formik.handleSubmit}>
              <div className="form-outline mb-4">
                <label className="form-label">Title</label>
                <input
                  id="title"
                  name="title"
                  type="text"
                  className="form-control inputStyle"
                  value={formik.values.title}
                  onChange={formik.handleChange}
                  onBlur={formik.handleBlur}
                />
                <div className="form-outline mb-4">
                  <label className="form-label">Description:</label>
                  <textarea
                    id="description"
                    name="description"
                    type="textarea"
                    rows="3"
                    className="form-control inputStyle"
                    value={formik.values.description}
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                  />
                </div>
                <div className="form-outline mb-4">
                  <label className="form-label">Requirements:</label>
                  <textarea
                    id="requirements"
                    name="requirements"
                    type="textarea"
                    rows="2"
                    className="form-control inputStyle"
                    value={formik.values.requirements}
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                  />
                </div>
                <div className="form-outline mb-4">
                  <label className="form-label">
                    What will the student learn:
                  </label>
                  <textarea
                    id="outline"
                    name="outline"
                    type="textarea"
                    rows="2"
                    className="form-control inputStyle"
                    value={formik.values.outline}
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                  />
                </div>
                <div className="form-outline mb-4">
                  <label className="form-label">Image Poster</label>
                  <input
                    id="image"
                    name="image"
                    type="file"
                    className="form-control inputStyle"
                    value={formik.values.image}
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                  />
                </div>
                <div className="form-outline mb-4">
                  <label className="form-label">Price:</label>
                  <select className="form-select" onChange={handlePay}>
                    <option defaultValue="0">Free</option>
                    <option value="1">Paid</option>
                  </select>
                </div>
                {paid && (
                  <div className="form-outline mb-4">
                    <label className="form-label"></label>
                    <input
                      id="price"
                      name="price"
                      type="number"
                      className="form-control inputStyle"
                      value={formik.values.price}
                      onChange={formik.handleChange}
                      onBlur={formik.handleBlur}
                    />
                    $
                  </div>
                )}
              </div>
              <div>
                <button type="submit" className={`btn btn-block mb-4`}>
                  Create Course
                </button>
              </div>
            </form>
          </div>
        </div>
      ) : (
        <Navigate to={`/user/${userData.sub}/become-mentor`} />
      )}
    </>
  );
};
export default CreateCourse;
