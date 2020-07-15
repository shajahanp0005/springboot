package com.sha.todojpa.repository;

import com.sha.todojpa.domain.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<ToDo, String> {
}
