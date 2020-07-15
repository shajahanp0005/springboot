package com.sha.todorest.repository;

import com.sha.todorest.domain.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<ToDo, String> {
}
