package com.project.todoApp.service;

import com.project.todoApp.dao.TodoRepository;
import com.project.todoApp.entity.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
public class TodoServiceImpl implements TodoService{

    private TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<TodoItem> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public void save(TodoItem theTodo) {
        todoRepository.save(theTodo);
    }

    @Override
    public void deleteById(Long theId) {
        todoRepository.deleteById(theId);
    }

    @Override
    public TodoItem findById(Long theId) {
        Optional<TodoItem> result= todoRepository.findById(theId);
        TodoItem theTodo=null;
        if(result.isPresent()) theTodo=result.get();
        else {
//            we didn't find the employee
            throw new RuntimeException("Did not found todo id : "+theId);
        }
        return theTodo;
    }
}
