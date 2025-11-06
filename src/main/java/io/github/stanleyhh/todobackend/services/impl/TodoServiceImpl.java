package io.github.stanleyhh.todobackend.services.impl;

import io.github.stanleyhh.todobackend.domain.dto.TodoDto;
import io.github.stanleyhh.todobackend.domain.entities.Todo;
import io.github.stanleyhh.todobackend.exceptions.TodoNotFoundException;
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
    public TodoDto getTodoById(String id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("Todo " + id + " not found"));
        return todoMapper.toDto(todo);
    }

    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        String id = idService.randomId();
        Todo todo = todoMapper.fromDto(todoDto.withId(id));

        Todo savedTodo = todoRepository.save(todo);

        return todoMapper.toDto(savedTodo);
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, String id) {
        Todo todo = todoMapper.fromDto(todoDto);
        Todo savedTodo = todoRepository.save(todo.withId(id));
        return todoMapper.toDto(savedTodo);
    }
}
