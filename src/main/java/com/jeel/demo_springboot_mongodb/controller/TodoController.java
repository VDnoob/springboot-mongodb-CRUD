package com.jeel.demo_springboot_mongodb.controller;

import com.jeel.demo_springboot_mongodb.exception.TodoCollectionException;
import com.jeel.demo_springboot_mongodb.model.TodoDTO;
import com.jeel.demo_springboot_mongodb.repository.TodoRepository;
import com.jeel.demo_springboot_mongodb.service.TodoService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepo;

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos() {
        List<TodoDTO> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, todos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo){
        try{
            todoService.createTodo(todo);
            return new ResponseEntity<TodoDTO>(todo,HttpStatus.OK);
        }
        catch(ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch(TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> findSingleTodo(@PathVariable("id") String id){
        try{
            return new ResponseEntity<>(todoService.getSingleTodo(id),HttpStatus.OK);
        }
        catch(TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable("id") String id,@RequestBody TodoDTO todo){
        try{
            todoService.updateTodo(id,todo);
            return new ResponseEntity<>("Updated todo with id " + id, HttpStatus.OK);
        }
        catch(ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch(TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") String id){
        try{
            todoService.deleteTodo(id);
            return new ResponseEntity<>("Deleted the todo successfully with id " + id,HttpStatus.OK);
        }
        catch (TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
