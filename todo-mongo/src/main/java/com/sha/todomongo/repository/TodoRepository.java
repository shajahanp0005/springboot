package com.sha.todomongo.repository;

import com.sha.todomongo.domain.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<ToDo, String> {
}
