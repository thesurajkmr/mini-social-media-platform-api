package com.project.todoApp.rest;

import com.project.todoApp.entity.TodoItem;
import com.project.todoApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoRestController {
    private TodoService todoService;

    @Autowired
    public TodoRestController(TodoService todoService) {
        this.todoService = todoService;
    }

    //    expose "/todos" and return list of todos
    @GetMapping("/list")
    public List<TodoItem> findAll(){
        return todoService.findAll();
    }

//    add mapping for get /list/{todoid}
    @GetMapping("/list/{todoId}")
    public TodoItem getTodo(@PathVariable Long todoId){
        TodoItem theTodo=todoService.findById(todoId);
        if(theTodo==null){
            throw new RuntimeException("ToDo id not found -"+todoId);
        }
        return theTodo;
    }

//    add mapping for POST /list - add new toDoItem
    @PostMapping("/list")
    public TodoItem addTodo(@RequestBody TodoItem theTodo){
        theTodo.setId(0);
        todoService.save(theTodo);
        return theTodo;
    }

//    add mapping for PUT/list - update an existing TodoItem
    @PutMapping("/list")
    public TodoItem updateTodo(@RequestBody TodoItem theTodo){
        todoService.save(theTodo);
        return theTodo;
    }

//    add mapping for DELETE /list/{todoId} -delete todoItem
    @DeleteMapping("/list/{todoId}")
    public String deleteTodo(@PathVariable Long todoId){
        TodoItem theTodo=todoService.findById(todoId);
//        throw exception if null
        if(theTodo==null){
            throw new RuntimeException("Todo Id not found - "+todoId);
        }
        todoService.deleteById(todoId);
        return "Deleted Todo with id :- "+todoId;
    }

}
