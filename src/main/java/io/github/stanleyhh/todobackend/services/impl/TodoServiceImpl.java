package io.github.stanleyhh.todobackend.services.impl;

import io.github.stanleyhh.todobackend.domain.dto.TodoDto;
import io.github.stanleyhh.todobackend.domain.entities.Todo;
import io.github.stanleyhh.todobackend.mappers.TodoMapper;
import io.github.stanleyhh.todobackend.repositories.TodoRepository;
import io.github.stanleyhh.todobackend.services.IdService;
import io.github.stanleyhh.todobackend.services.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final IdService idService;

    public TodoServiceImpl(TodoRepository todoRepository, TodoMapper todoMapper, IdService idService) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
        this.idService = idService;
    }

    @Override
    public List<TodoDto> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(todoMapper::toDto)
                .toList();
    }

    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        String id = idService.randomId();
        Todo todo = todoMapper.fromDto(todoDto.withId(id));

        Todo savedTodo = todoRepository.save(todo);

        return todoMapper.toDto(savedTodo);
    }
}
