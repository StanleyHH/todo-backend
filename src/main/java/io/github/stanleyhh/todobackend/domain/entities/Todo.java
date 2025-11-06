package io.github.stanleyhh.todobackend.domain.entities;

import lombok.Builder;

@Builder
public record Todo(
        String id,
        String description,
        TodoStatus status
) {
}
