package com.sha.todoredis.repository;

import com.sha.todoredis.domain.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<ToDo, String> {
}
