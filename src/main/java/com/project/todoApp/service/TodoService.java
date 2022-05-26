package com.project.todoApp.service;

import com.project.todoApp.entity.TodoItem;

import java.util.List;

public interface TodoService {
    public List<TodoItem> findAll();
    public TodoItem findById(Long theId);
    public void save(TodoItem theTodo);
    public void deleteById(Long theId);
}
