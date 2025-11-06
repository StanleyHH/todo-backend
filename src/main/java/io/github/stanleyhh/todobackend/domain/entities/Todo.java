package io.github.stanleyhh.todobackend.domain.entities;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record Todo(
        String id,
        String description,
        TodoStatus status
) {
}
