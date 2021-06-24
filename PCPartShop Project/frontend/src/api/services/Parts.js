import authHeader, { BASE_URL, HTTP } from "../Http";

const getAll = async (url) => {
  return HTTP.get(BASE_URL + url, { headers: authHeader() }).then(
    (response) => {
      return response.data;
    }
  );
};

const getAllBy = async (url, query) => {
  return HTTP.get(BASE_URL + url + "/search/" + query, {
    headers: authHeader(),
  }).then((response) => {
    return response.data;
  });
};

const getPart = async (url, id) => {
  return HTTP.get(BASE_URL + url + "/" + id, {
    headers: authHeader(),
  }).then((response) => {
    return response.data;
  });
};

const addPart = async (url, data) => {
  return HTTP.post(BASE_URL + url, data, {
    headers: authHeader(),
  }).then((response) => {
    return response.data;
  });
};

const updateQuantity = async (url, id, data) => {
  return HTTP.patch(BASE_URL + url + "/" + id, data, {
    headers: authHeader(),
  }).then((response) => {
    return response.data;
  });
};

const order = async (url, id, data) => {
  return HTTP.patch(BASE_URL + url + "/" + id + "/order", data, {
    headers: authHeader(),
  }).then((response) => {
    return response.data;
  });
};

const updatePart = async (url, id, data) => {
  return HTTP.put(BASE_URL + url + "/" + id, data, {
    headers: authHeader(),
  }).then((response) => {
    return response.data;
  });
};

const deletePart = async (url, id) => {
  return HTTP.delete(BASE_URL + url + "/" + id, {
    headers: authHeader(),
  }).then((response) => {
    return response.data;
  });
};

export const PartsService = {
  getAll,
  getAllBy,
  getPart,
  addPart,
  updateQuantity,
  order,
  updatePart,
  deletePart,
};
