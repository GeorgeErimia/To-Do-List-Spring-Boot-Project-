import React, { useEffect, useState } from "react";
import {
  completeTodo,
  getAllTodos,
  removeTodo,
  uncompleteTodo,
} from "../services/TodoService";
import { useNavigate } from "react-router-dom";

const ListTodoComponent = () => {
  const [todos, setTodos] = useState([]);

  useEffect(() => {
    listTodos();
  }, []);

  function listTodos() {
    getAllTodos()
      .then((response) => {
        setTodos(response.data);
      })
      .catch((error) => {
        console.error("error");
      });
  }

  const navigator = useNavigate();

  function addNewTodo() {
    navigator("/add-todo");
  }

  function updateTodo(id) {
    console.log("update todo with id: " + id);
    navigator("/update-todo/" + id);
  }

  function deleteTodo(id) {
    if (window.confirm("Are you sure you want to delete this todo?")) {
      removeTodo(id)
        .then((response) => {
          console.log(response);
          listTodos();
        })
        .catch((error) => {
          console.error(error);
        });
    }
  }

  function markOrUnmarkTodoAsCompleted(id, completed) {
    if (completed) {
      uncompleteTodo(id)
        .then((response) => {
          console.log(response);
          listTodos();
        })
        .catch((error) => {
          console.error(error);
        });
    } else {
      completeTodo(id)
        .then((response) => {
          console.log(response);
          listTodos();
        })
        .catch((error) => {
          console.error(error);
        });
    }
  }

  return (
    <div className="container">
      <h1>List Todo</h1>
      <button className="btn btn-primary mb-2" onClick={addNewTodo}>
        Add Todo
      </button>
      <table className="table table-bordered table-striped">
        <thead>
          <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Description</th>
            <th>Completed</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {todos.map((todo) => (
            <tr key={todo.id}>
              <td>{todo.id}</td>
              <td>{todo.title}</td>
              <td>{todo.description}</td>
              <td>{todo.completed ? "Yes" : "No"}</td>
              <td>
                <button
                  className="btn btn-info me-2"
                  onClick={() => updateTodo(todo.id)}
                >
                  Update
                </button>
                <button
                  className="btn btn-danger me-2"
                  onClick={() => deleteTodo(todo.id)}
                >
                  Delete
                </button>

                <button
                  className={`btn ${
                    todo.completed ? "btn-warning" : "btn-success"
                  }`}
                  onClick={() =>
                    markOrUnmarkTodoAsCompleted(todo.id, todo.completed)
                  }
                >
                  {todo.completed ? "Mark Incomplete" : "Mark Complete"}
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ListTodoComponent;
