const BASE_URL = "http://localhost:8080/api/auth";

export const postAuth = async (payload,route) => {
  const response = await fetch(BASE_URL + route, {
    method: "post",
    headers: {
      "Content-Type": "application/json",
    },
    body: payload,
  });
  return await response;
};



export default postAuth 
