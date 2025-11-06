package io.github.stanleyhh.todobackend.domain.dto;

import io.github.stanleyhh.todobackend.domain.entities.TodoStatus;

public record TodoDto(
        String id,
        String description,
        TodoStatus status
) {
}
