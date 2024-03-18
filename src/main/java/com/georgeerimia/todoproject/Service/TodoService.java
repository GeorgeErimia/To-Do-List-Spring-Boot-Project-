package com.georgeerimia.todoproject.Service;

import com.georgeerimia.todoproject.dtos.TodoDTO;

import java.util.List;

public interface TodoService {

    // Method that will add a new To-do item to the database
    TodoDTO addTodo(TodoDTO todoDTO);

    // Method that will return a To-do item from the database
    TodoDTO getTodoById(Long id);

    // Method that will return all To-do items from the database
    List<TodoDTO> getAllTodos();

    // Method that will update a To-do item in the database
    TodoDTO updateTodoById(Long id, TodoDTO todoDTO);

    // Method that will delete a To-do item from the database
    void deleteTodoById(Long id);

    // Method that will mark a To-do item as completed
    TodoDTO markTodoAsCompleted(Long id);

    // Method that will mark a To-do item as not completed
    TodoDTO markTodoAsNotCompleted(Long id);


}
