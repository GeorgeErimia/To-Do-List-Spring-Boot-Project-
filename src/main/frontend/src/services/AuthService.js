import axios from "axios";
import { useNavigate } from "react-router-dom";

const AUTH_REST_API_BASE_URL = "http://localhost:8080/api/auth";

export const registerApiCall = (registerObj) =>
  axios.post(AUTH_REST_API_BASE_URL + "/register", registerObj);

export const loginApiCall = (loginObj) =>
  axios.post(AUTH_REST_API_BASE_URL + "/login", loginObj);

// Methods used to store the authentication token
// in the browser's local storage
export const storeToken = (token) => localStorage.setItem("token", token);

export const getToken = () => localStorage.getItem("token");

// Methods used to store the logged in user
// In the session storage
export const saveLoggedInUser = (username) => {
  sessionStorage.setItem("authenticatedUser", username);
};

export const isUserLoggedIn = () => {
  const username = sessionStorage.getItem("authenticatedUser");
  return username != null;
};

export const getLoggedInUser = () => {
  const username = sessionStorage.getItem("authenticatedUser");
  return username;
};

export const logout = () => {
  localStorage.clear();
  sessionStorage.clear();
};
