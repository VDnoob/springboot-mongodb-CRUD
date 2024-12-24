package com.jeel.demo_springboot_mongodb.service;

import com.jeel.demo_springboot_mongodb.exception.TodoCollectionException;
import com.jeel.demo_springboot_mongodb.model.TodoDTO;
import com.jeel.demo_springboot_mongodb.repository.TodoRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todoRepo;

    @Override
    public void createTodo(TodoDTO todo) throws ConstraintViolationException, TodoCollectionException {
        Optional<TodoDTO> todoDTOOptional = todoRepo.findByTodo(todo.getTodo());

        if(todoDTOOptional.isPresent()){
            throw new TodoCollectionException("A Todo with the given name already exists!!");
        }
        else{
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todo);
        }
    }

    @Override
    public List<TodoDTO> getAllTodos(){
        List<TodoDTO> todos = todoRepo.findAll();

        if(todos.size() > 0){
            return todos;
        }
        else{
            return new ArrayList<>();
        }
    }

    @Override
    public TodoDTO getSingleTodo(String id) throws TodoCollectionException{
        Optional<TodoDTO> todo = todoRepo.findById(id);

        if(todo.isPresent()){
            return todo.get();
        }
        else{
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }

    }

    @Override
    public void updateTodo(String id,TodoDTO todo) throws TodoCollectionException{
        Optional<TodoDTO> todoDTOOptional = todoRepo.findById(id);
        Optional<TodoDTO> todoDTOWithSameName = todoRepo.findByTodo(todo.getTodo());

        if(todoDTOOptional.isPresent()){

            if(todoDTOWithSameName.isPresent() && !todoDTOWithSameName.get().getId().equals(id)){
                throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
            }

            TodoDTO todoToUpdate = todoDTOOptional.get();
            todoToUpdate.setTodo(todo.getTodo());
            todoToUpdate.setDescription(todo.getDescription());
            todoToUpdate.setCompleted(todo.getCompleted());
            todoToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todoToUpdate);
        }
        else{
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteTodo(String id) throws TodoCollectionException{
        Optional<TodoDTO> todo = todoRepo.findById(id);

        if(todo.isPresent()){
            todoRepo.deleteById(id);
        }
        else{
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
    }
}
