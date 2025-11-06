package io.github.stanleyhh.todobackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stanleyhh.todobackend.domain.dto.TodoDto;
import io.github.stanleyhh.todobackend.domain.entities.Todo;
import io.github.stanleyhh.todobackend.domain.entities.TodoStatus;
import io.github.stanleyhh.todobackend.mappers.TodoMapper;
import io.github.stanleyhh.todobackend.repositories.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TodoControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TodoRepository todoRepository;
    @Autowired
    private TodoMapper todoMapper;

    @Test
    void getAllTodos() throws Exception {
        Todo todo = new Todo("1", "desc1", TodoStatus.OPEN);
        todoRepository.save(todo);

        mockMvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(todo.id()));
    }

    @Test
    void createTodo() throws Exception {
        TodoDto todoDto = TodoDto.builder()
                .description("desc1")
                .status(TodoStatus.OPEN)
                .build();
        String todoJson = objectMapper.writeValueAsString(todoDto);

        mockMvc.perform(post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.description").value("desc1"))
                .andExpect(jsonPath("$.status").value("OPEN"));
    }

    @Test
    void getTodoById_shouldReturnValidJsonAndStatus200_whenValidId() throws Exception {
        TodoDto todoDto = TodoDto.builder()
                .description("desc1")
                .status(TodoStatus.OPEN)
                .build();
        Todo todo = todoMapper.fromDto(todoDto.withId("1"));
        String todoJson = objectMapper.writeValueAsString(todo);
        todoRepository.save(todo);

        mockMvc.perform(get("/api/todo/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(todoJson));
    }

    @Test
    void getTodoById_shouldReturnStatus404_whenInvalidId() throws Exception {
        mockMvc.perform(get("/api/todo/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateTodo() throws Exception {
        Todo todo = Todo.builder()
                .id("1")
                .description("desc1")
                .status(TodoStatus.OPEN)
                .build();
        todoRepository.save(todo);

        TodoDto todoDto = TodoDto.builder()
                .description("desc2")
                .status(TodoStatus.IN_PROGRESS)
                .build();

        String todoDtoJson = objectMapper.writeValueAsString(todoDto);
        String todoJson = objectMapper.writeValueAsString(todo.withDescription("desc2").withStatus(TodoStatus.IN_PROGRESS));

        mockMvc.perform(put("/api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoDtoJson))
                .andExpect(status().isOk())
                .andExpect(content().json(todoJson));
    }
}