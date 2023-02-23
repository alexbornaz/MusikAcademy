const BASE_URL = "http://localhost:8080/api/";
export const PostDataAuthenticated = async (payload, token, route) => {
    const response = await fetch(BASE_URL + route, {
        method: "post",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
        },
        body: payload,
    });
    return await response.json();
};

export const GetDataAuthenticated = async (route, token) => {
    const response = await fetch(BASE_URL + route, {
        method: "get",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
        },
    });
    const data = await response.json();
    return await data;
};

export const PutDataAuthenticated = async (route, token) => {
    const response = await fetch(BASE_URL + route, {
        method: "put",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
        },
    })
    const data = await response.json();
    return await data;
}

export const PostData = async (route, payload) => {
    const response = await fetch(BASE_URL + route, {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: payload,
    })
    const data = await response.json();
    return await data;
}
export const GetData = async (route) => {
    const response = await fetch(BASE_URL + route, {
        method: "get",
        headers: {
            "Content-Type": "application/json",
        },
    });
    const data = await response.json();
    return await data;
};