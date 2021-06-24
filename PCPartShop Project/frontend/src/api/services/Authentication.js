import { BASE_URL, HTTP } from "../Http";

const login = async (data) => {
  return HTTP.post(BASE_URL + "/auth/sign-in", data).then((response) => {
    if (response.data.token) {
      localStorage.setItem("user", JSON.stringify(response.data));
    }

    return response.data;
  });
};

const register = async (data) => {
  return HTTP.post(BASE_URL + "/auth/sign-up", data);
};

const logout = async () => {
  localStorage.removeItem("user");
};

export const AuthenticationService = {
  login,
  logout,
  register,
};
