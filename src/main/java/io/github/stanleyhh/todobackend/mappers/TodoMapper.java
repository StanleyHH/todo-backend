package io.github.stanleyhh.todobackend.mappers;

import io.github.stanleyhh.todobackend.domain.dto.TodoDto;
import io.github.stanleyhh.todobackend.domain.entities.Todo;

public interface TodoMapper {

    Todo fromDto(TodoDto todoDto);

    TodoDto toDto(Todo todo);
}
