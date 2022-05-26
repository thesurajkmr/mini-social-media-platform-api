package com.project.todoApp.controller;

import com.project.todoApp.entity.TodoItem;
import com.project.todoApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/todo")
public class TodoController {
//    load todolist data
  @Autowired
  private TodoService todoService;

//    @Autowired
//    public TodoController(TodoService todoService) {
//        this.todoService = todoService;
//    }
//  add mapping for list
    @GetMapping("/list")
    public String listTodos(Model theModel){
//        get todos from the database
        List<TodoItem> theTodos=todoService.findAll();

//        add to the spring model
        theModel.addAttribute("todos",theTodos);
        return "list-todos";
    }
//    show form for adding the todoItem
    @GetMapping("/showFormForAdd")
    public String showFormForTodo(Model theModel){
//        create model attribute to bind data
        TodoItem theTodoItem=new TodoItem();
        theTodoItem.setId(0);
        System.out.println(theTodoItem);
        theModel.addAttribute("todo",theTodoItem);
        return "todo-form";
    }

    @PostMapping("/save")
    public String saveTodo(@ModelAttribute("todo") TodoItem theTodo){
//        save the todoItem
        if(Objects.equals(theTodo.getTitle(), "") && theTodo.getDescription()=="") return "redirect:/todo/list";
            todoService.save(theTodo);

//        use a redirect to prevent duplicate todoIten submissions
        return "redirect:/todo/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("todoId") Long theId, Model theModel){
//        get the todoItem from the service
        TodoItem theTodoItem=todoService.findById(theId);
//        set todos as a model attribute to prepopulate the form
        theModel.addAttribute("todo",theTodoItem);
//        send over to our form
        return "todo-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("todoId") Long theId){
//        delete the todoitem
        todoService.deleteById(theId);
//        return to todos/list
        return "redirect:/todo/list";
    }
}
