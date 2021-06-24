import authHeader, { BASE_URL, HTTP } from "../Http";

const getAll = async () => {
  return HTTP.get(BASE_URL + "/configurations", { headers: authHeader() }).then(
    (response) => {
      return response.data;
    }
  );
};

const getConfiguration = async (id) => {
  return HTTP.get(BASE_URL + "/configurations/" + id, {
    headers: authHeader(),
  }).then((response) => {
    return response.data;
  });
};

const addConfiguration = async (data) => {
  return HTTP.post(BASE_URL + "/configurations", data, {
    headers: authHeader(),
  }).then((response) => {
    return response.data;
  });
};

const deleteConfiguration = async (id) => {
  return HTTP.delete(BASE_URL + "/configurations/" + id, {
    headers: authHeader(),
  }).then((response) => {
    return response.data;
  });
};

const report = async (id) => {
  return HTTP.get(BASE_URL + "/configurations/export/" + id, {
    headers: authHeader(),
    responseType: "blob",
  }).then((response) => {
    return response.data;
  });
};

export const ConfigurationsService = {
  getAll,
  getConfiguration,
  addConfiguration,
  deleteConfiguration,
  report,
};
