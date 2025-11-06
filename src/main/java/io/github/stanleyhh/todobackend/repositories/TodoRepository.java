package io.github.stanleyhh.todobackend.repositories;

import io.github.stanleyhh.todobackend.domain.entities.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {
}
