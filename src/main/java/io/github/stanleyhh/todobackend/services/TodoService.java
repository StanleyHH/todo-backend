package io.github.stanleyhh.todobackend.services;

import io.github.stanleyhh.todobackend.domain.dto.TodoDto;

import java.util.List;

public interface TodoService {
    List<TodoDto> getAllTodos();
}
