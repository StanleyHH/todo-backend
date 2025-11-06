package io.github.stanleyhh.todobackend.services.impl;

import io.github.stanleyhh.todobackend.domain.dto.TodoDto;
import io.github.stanleyhh.todobackend.domain.entities.Todo;
import io.github.stanleyhh.todobackend.domain.entities.TodoStatus;
import io.github.stanleyhh.todobackend.mappers.TodoMapper;
import io.github.stanleyhh.todobackend.mappers.impl.TodoMapperImpl;
import io.github.stanleyhh.todobackend.repositories.TodoRepository;
import io.github.stanleyhh.todobackend.services.IdService;
import io.github.stanleyhh.todobackend.services.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TodoServiceImplTest {
    TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
    TodoMapper todoMapper = new TodoMapperImpl();
    IdService idService = Mockito.mock(IdService.class);
    TodoService todoService = new TodoServiceImpl(todoRepository, todoMapper, idService);

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

    @Test
    void createTodo() {
        TodoDto todoDto = TodoDto.builder()
                .description("desc1")
                .status(TodoStatus.OPEN)
                .build();
        Todo todo = todoMapper.fromDto(todoDto.withId("1"));

        when(idService.randomId()).thenReturn("1");
        when(todoRepository.save(todo)).thenReturn(todo);

        TodoDto actual = todoService.createTodo(todoDto);

        verify(todoRepository).save(todo);

        assertEquals(todoDto.withId("1"), actual);
    }
}