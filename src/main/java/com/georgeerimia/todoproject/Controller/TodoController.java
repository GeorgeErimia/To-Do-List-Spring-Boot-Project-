package com.georgeerimia.todoproject.Controller;

import com.georgeerimia.todoproject.Service.TodoService;
import com.georgeerimia.todoproject.dtos.TodoDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    private final TodoService todoService;

    // Create a REST API POST endpoint that will add a new To-do item to the database
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDTO> addTodo(@RequestBody TodoDTO todoDTO) {
        TodoDTO savedTodo = todoService.addTodo(todoDTO);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    // Create a REST API GET endpoint that will return a To-do item from the database
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable Long id) {
        TodoDTO todoDTO = todoService.getTodoById(id);
        return new ResponseEntity<>(todoDTO, HttpStatus.OK);
    }

    // Create a REST API GET endpoint that will return all To-do items from the database
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        List<TodoDTO> todoDTOS = todoService.getAllTodos();
        return new ResponseEntity<>(todoDTOS, HttpStatus.OK);
    }

    // Create a REST API PUT endpoint that will update a To-do item in the database
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> updateTodoById(@PathVariable Long id, @RequestBody TodoDTO todoDTO) {
        TodoDTO updatedTodo = todoService.updateTodoById(id, todoDTO);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    // Create a REST API DELETE endpoint that will delete a To-do item from the database
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodoById(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return new ResponseEntity<>("Todo deleted successfully!", HttpStatus.OK);
    }

    // Create a REST API PATCH endpoint that will mark a To-do item as completed
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoDTO> markTodoAsCompleted(@PathVariable Long id) {
        TodoDTO todoDTO = todoService.markTodoAsCompleted(id);
        return new ResponseEntity<>(todoDTO, HttpStatus.OK);
    }

    // Create a REST API PATCH endpoint that will mark a To-do item as not completed
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PatchMapping("/{id}/notcomplete")
    public ResponseEntity<TodoDTO> markTodoAsNotCompleted(@PathVariable Long id) {
        TodoDTO todoDTO = todoService.markTodoAsNotCompleted(id);
        return new ResponseEntity<>(todoDTO, HttpStatus.OK);
    }



}
