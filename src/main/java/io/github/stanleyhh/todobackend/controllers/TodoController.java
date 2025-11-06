package io.github.stanleyhh.todobackend.controllers;

import io.github.stanleyhh.todobackend.domain.dto.TodoDto;
import io.github.stanleyhh.todobackend.services.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
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
    List<TodoDto> getAllTodos() {
        return todoService.getAllTodos();
    }
}
