package io.github.stanleyhh.todobackend.services.impl;

import io.github.stanleyhh.todobackend.domain.dto.TodoDto;
import io.github.stanleyhh.todobackend.mappers.TodoMapper;
import io.github.stanleyhh.todobackend.repositories.TodoRepository;
import io.github.stanleyhh.todobackend.services.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public TodoServiceImpl(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    @Override
    public List<TodoDto> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(todoMapper::toDto)
                .toList();
    }
}
