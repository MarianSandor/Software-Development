import authHeader, { BASE_URL, HTTP } from "../Http";

const getAll = async (user) => {
  return HTTP.get(BASE_URL + "/books", { headers: authHeader(user) }).then(
    (response) => {
      return response.data;
    }
  );
};

const addBook = async (user, data) => {
  return HTTP.post(BASE_URL + "/books", data, {
    headers: authHeader(user),
  }).then((response) => {
    return response.data;
  });
};

const updateQuantity = async (user, id, data) => {
  return HTTP.patch(BASE_URL + "/books/" + id, data, {
    headers: authHeader(user),
  }).then((response) => {
    return response.data;
  });
};

const searchBook = async (user, data) => {
  return HTTP.get(BASE_URL + "/books/search/" + data, {
    headers: authHeader(user),
  }).then((response) => {
    return response.data;
  });
};

const deleteBook = async (user, data) => {
  return HTTP.delete(BASE_URL + "/books/" + data, {
    headers: authHeader(user),
  }).then((response) => {
    return response.data;
  });
};

const updateBook = async (user, id, data) => {
  return HTTP.put(BASE_URL + "/books/" + id, data, {
    headers: authHeader(user),
  }).then((response) => {
    return response.data;
  });
};

const report = async (user, data) => {
  return HTTP.get(BASE_URL + "/books/export/" + data, {
    headers: authHeader(user),
    responseType: "blob",
  }).then((response) => {
    return response.data;
  });
};

export const BooksService = {
  getAll,
  addBook,
  updateQuantity,
  searchBook,
  deleteBook,
  updateBook,
  report,
};
