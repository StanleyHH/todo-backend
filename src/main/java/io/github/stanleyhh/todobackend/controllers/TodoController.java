package io.github.stanleyhh.todobackend.controllers;

import io.github.stanleyhh.todobackend.domain.dto.TodoDto;
import io.github.stanleyhh.todobackend.services.TodoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoDto> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("{id}")
    public TodoDto getTodoById(@PathVariable String id) {
        return todoService.getTodoById(id);
    }

    @PostMapping
    public TodoDto createTodo(@RequestBody TodoDto todoDto) {
        return todoService.createTodo(todoDto);
    }

    @PutMapping("{id}")
    public TodoDto updateTodo(@RequestBody TodoDto todoDto, @PathVariable String id) {
        return todoService.updateTodo(todoDto, id);
    }

    @DeleteMapping("{id}")
    public void deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
    }
}
