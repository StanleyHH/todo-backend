package io.github.stanleyhh.todobackend.domain.entities;

public record Todo(
        String id,
        String description,
        TodoStatus status
) {
}
