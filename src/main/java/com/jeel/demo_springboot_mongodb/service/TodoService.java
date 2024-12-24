package com.jeel.demo_springboot_mongodb.service;

import com.jeel.demo_springboot_mongodb.exception.TodoCollectionException;
import com.jeel.demo_springboot_mongodb.model.TodoDTO;
import jakarta.validation.ConstraintViolationException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface TodoService {

    public void createTodo(TodoDTO todo) throws ConstraintViolationException, TodoCollectionException;

    public List<TodoDTO> getAllTodos();

    public TodoDTO getSingleTodo(String id) throws TodoCollectionException;

    public void updateTodo(String id,TodoDTO todo) throws TodoCollectionException;

    public void deleteTodo(String id) throws TodoCollectionException;
}
