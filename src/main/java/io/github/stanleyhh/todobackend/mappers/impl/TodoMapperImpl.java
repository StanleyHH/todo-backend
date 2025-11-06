package io.github.stanleyhh.todobackend.mappers.impl;

import io.github.stanleyhh.todobackend.domain.dto.TodoDto;
import io.github.stanleyhh.todobackend.domain.entities.Todo;
import io.github.stanleyhh.todobackend.mappers.TodoMapper;
import org.springframework.stereotype.Component;

@Component
public class TodoMapperImpl implements TodoMapper {
    @Override
    public Todo fromDto(TodoDto todoDto) {
        return new Todo(todoDto.id(), todoDto.description(), todoDto.status());
    }

    @Override
    public TodoDto toDto(Todo todo) {
        return new TodoDto(todo.id(), todo.description(), todo.status());
    }
}
