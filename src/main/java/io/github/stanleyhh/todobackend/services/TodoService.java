package io.github.stanleyhh.todobackend.services;

import io.github.stanleyhh.todobackend.domain.dto.TodoDto;

import java.util.List;

public interface TodoService {
    List<TodoDto> getAllTodos();

    TodoDto getTodoById(String id);

    TodoDto createTodo(TodoDto todoDto);

    TodoDto updateTodo(TodoDto todoDto, String id);
}
