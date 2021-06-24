import authHeader, { BASE_URL, HTTP } from "../Http";

const getAll = async () => {
  return HTTP.get(BASE_URL + "/users", {
    headers: authHeader(),
  }).then((response) => {
    return response.data;
  });
};

const getUser = async (id) => {
  return HTTP.get(BASE_URL + "/users/" + id, {
    headers: authHeader(),
  }).then((response) => {
    return response.data;
  });
};

const addUser = async (data) => {
  return HTTP.post(BASE_URL + "/users", data, {
    headers: authHeader(),
  }).then((response) => {
    return response.data;
  });
};

const searchUser = async (data) => {
  return HTTP.get(BASE_URL + "/users/search/" + data, {
    headers: authHeader(),
  }).then((response) => {
    return response.data;
  });
};

const deleteUser = async (data) => {
  return HTTP.delete(BASE_URL + "/users/" + data, {
    headers: authHeader(),
  }).then((response) => {
    return response.data;
  });
};

const updateUser = async (id, data) => {
  return HTTP.put(BASE_URL + "/users/" + id, data, {
    headers: authHeader(),
  }).then((response) => {
    return response.data;
  });
};

const getUserConfigurations = async (id) => {
  return HTTP.get(BASE_URL + "/users/" + id + "/configurations", {
    headers: authHeader(),
  }).then((response) => {
    return response.data;
  });
};

export const UsersService = {
  getAll,
  getUser,
  addUser,
  searchUser,
  deleteUser,
  updateUser,
  getUserConfigurations,
};
