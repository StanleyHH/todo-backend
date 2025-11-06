package io.github.stanleyhh.todobackend.domain.dto;

import io.github.stanleyhh.todobackend.domain.entities.TodoStatus;
import lombok.Builder;
import lombok.With;

@Builder
@With
public record TodoDto(
        String id,
        String description,
        TodoStatus status
) {
}
