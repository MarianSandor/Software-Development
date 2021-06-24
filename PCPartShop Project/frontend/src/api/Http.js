import axios from "axios";

export const BASE_URL = "http://localhost:8088/api";
export const URLS = {
  CPU: "/cpu",
  GPU: "/gpu",
  Memory: "/memory",
  Motherboard: "/motherboard",
  "PC Case": "/pcCase",
  PSU: "/psu",
  Storage: "/storage",
};

export const HTTP = axios.create({
  baseURL: BASE_URL,
});

export default function authHeader() {
  let user = JSON.parse(localStorage.getItem("user"));
  if (user && user.token) {
    return {
      Authorization: "Bearer " + user.token,
      "Content-Type": "application/json",
    };
  } else {
    return {};
  }
}
