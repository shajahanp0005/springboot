package com.sha.todojpa.controller;

import com.sha.todojpa.domain.ToDo;
import com.sha.todojpa.domain.ToDoBuilder;
import com.sha.todojpa.repository.TodoRepository;
import com.sha.todojpa.validation.ToDoValidationError;

import com.sha.todojpa.validation.ToDoValidationErrorBuilder;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ToDoController {

    private final TodoRepository todoRepository;

    public ToDoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }





    @GetMapping("/todo")
    public ResponseEntity<Iterable<ToDo>> getToDos(){
        return ResponseEntity.ok(todoRepository.findAll());
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable String id){
        Optional<ToDo> toDo;
        toDo = todoRepository.findById(id);
        if(toDo.isPresent())
            return ResponseEntity.ok(toDo.get());
        return ResponseEntity.notFound().build();

    }

    @PatchMapping("/todo/{id}")
    public ResponseEntity<ToDo> setCompleted(@PathVariable String id){
        Optional<ToDo> toDo = todoRepository.findById(id); if(!toDo.isPresent())
            return ResponseEntity.notFound().build();
        ToDo result = toDo.get(); result.setCompleted(true); todoRepository.save(result);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest() .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.ok().header("Location",location.toString()).
            build(); }

    @RequestMapping(value="/todo", method = {RequestMethod. POST,RequestMethod.PUT})
    public ResponseEntity<?> createToDo(@Valid @RequestBody ToDo toDo, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest(). body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
        }
        ToDo result = todoRepository.save(toDo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest(). path("/{id}")
            .buildAndExpand(result.getId()).toUri(); return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<ToDo> deleteToDo(@PathVariable String id){
        todoRepository.delete(ToDoBuilder.create().withId(id).build());
        return ResponseEntity.noContent().build(); }
    @DeleteMapping("/todo")
    public ResponseEntity<ToDo> deleteToDo(@RequestBody ToDo toDo){
        todoRepository.delete(toDo);
        return ResponseEntity.noContent().build(); }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ToDoValidationError handleException(Exception exception) {
        return new ToDoValidationError(exception.getMessage());
    }


}
