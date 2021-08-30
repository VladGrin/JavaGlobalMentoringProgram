package com.vladgrin.todo.server.repository;

import com.vladgrin.todo.server.domain.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends CrudRepository<ToDo, String> {
}
