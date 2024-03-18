package com.georgeerimia.todoproject.Service.impl;

import com.georgeerimia.todoproject.Exception.ResourceNotFoundException;
import com.georgeerimia.todoproject.Model.Todo;
import com.georgeerimia.todoproject.Repository.TodoRepository;
import com.georgeerimia.todoproject.Service.TodoService;
import com.georgeerimia.todoproject.dtos.TodoDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    TodoRepository todoRepository;

    private ModelMapper modelMapper;

    // Method that will add a new To-do item to the database
    @Override
    public TodoDTO addTodo(TodoDTO todoDTO) {

        // Convert DTO to Entity using ModelMapper
        Todo todo = modelMapper.map(todoDTO, Todo.class);

        // Save Entity to DB
        Todo savedTodo = todoRepository.save(todo);

        // Convert Entity to DTO using ModelMapper
        TodoDTO savedTodoDTO = modelMapper.map(savedTodo, TodoDTO.class);

        return savedTodoDTO;
    }

    // Method that will return a To-do item from the database
    @Override
    public TodoDTO getTodoById(Long id) {
        // Get Entity from DB
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo with ID: " + id + " not found!"));

        // Convert Entity to DTO using ModelMapper
        TodoDTO todoDTO = modelMapper.map(todo, TodoDTO.class);

        return todoDTO;
    }

    // Method that will return all To-do items from the database
    @Override
    public List<TodoDTO> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        List<TodoDTO> todoDTOS = todos
                                .stream()
                                .map(todo -> modelMapper.map(todo, TodoDTO.class))
                                .collect(Collectors.toList());
        return todoDTOS;
    }

    // Method that will update a To-do item in the database
    @Override
    public TodoDTO updateTodoById(Long id, TodoDTO todoDTO) {
        // Get Entity from DB
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo with ID: " + id + " not found!"));

        // Update Entity
        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setCompleted(todoDTO.isCompleted());

        // Save Entity to DB
        Todo updatedTodo = todoRepository.save(todo);

        // Convert Entity to DTO using ModelMapper
        TodoDTO updatedTodoDTO = modelMapper.map(updatedTodo, TodoDTO.class);

        return updatedTodoDTO;
    }

    // Method that will delete a To-do item from the database
    @Override
    public void deleteTodoById(Long id) {
        // Get Entity from DB
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo with ID: " + id + " not found!"));

        // Delete Entity from DB
        todoRepository.delete(todo);
    }

    // Method that will mark a To-do item as completed
    @Override
    public TodoDTO markTodoAsCompleted(Long id) {
        // Get Entity from DB
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo with ID: " + id + " not found!"));

        // Update Entity
        todo.setCompleted(true);

        // Save Entity to DB
        Todo updatedTodo = todoRepository.save(todo);

        // Convert Entity to DTO using ModelMapper
        TodoDTO updatedTodoDTO = modelMapper.map(updatedTodo, TodoDTO.class);

        return updatedTodoDTO;
    }

    @Override
    public TodoDTO markTodoAsNotCompleted(Long id) {
        // Get Entity from DB
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo with ID: " + id + " not found!"));

        // Update Entity
        todo.setCompleted(false);

        // Save Entity to DB
        Todo updatedTodo = todoRepository.save(todo);

        // Convert Entity to DTO using ModelMapper
        TodoDTO updatedTodoDTO = modelMapper.map(updatedTodo, TodoDTO.class);

        return updatedTodoDTO;
    }
}
