package io.github.stanleyhh.todobackend.services;

import io.github.stanleyhh.todobackend.domain.dto.TodoDto;
import io.github.stanleyhh.todobackend.domain.entities.Todo;
import io.github.stanleyhh.todobackend.domain.entities.TodoStatus;
import io.github.stanleyhh.todobackend.mappers.TodoMapper;
import io.github.stanleyhh.todobackend.mappers.impl.TodoMapperImpl;
import io.github.stanleyhh.todobackend.repositories.TodoRepository;
import io.github.stanleyhh.todobackend.services.impl.TodoServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TodoServiceTest {
    TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
    TodoMapper todoMapper = new TodoMapperImpl();
    TodoServiceImpl todoService = new TodoServiceImpl(todoRepository, todoMapper);

    @Test
    void getAllTodos() {
        Todo todo1 = new Todo("1", "desc1", TodoStatus.OPEN);
        Todo todo2 = new Todo("2", "desc2", TodoStatus.IN_PROGRESS);
        List<Todo> todos = List.of(todo1, todo2);
        List<TodoDto> expected = List.of(todoMapper.toDto(todo1), todoMapper.toDto(todo2));

        when(todoRepository.findAll()).thenReturn(todos);

        List<TodoDto> actual = todoService.getAllTodos();

        verify(todoRepository).findAll();
        assertEquals(expected, actual);
    }
}