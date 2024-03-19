import axios from "axios";

const BASE_REST_API_URL = "http://localhost:8080/api/v1/todos";

export const getAllTodos = () => axios.get(BASE_REST_API_URL);

export const createTodo = (todo) => axios.post(BASE_REST_API_URL, todo);

export const getTodo = (id) => axios.get(BASE_REST_API_URL + "/" + id);

export const updateTodo = (id, todo) =>
  axios.put(BASE_REST_API_URL + "/" + id, todo);

export const removeTodo = (id) => axios.delete(BASE_REST_API_URL + "/" + id);

export const completeTodo = (id) =>
  axios.patch(BASE_REST_API_URL + "/" + id + "/complete");

export const uncompleteTodo = (id) =>
  axios.patch(BASE_REST_API_URL + "/" + id + "/notcomplete");
