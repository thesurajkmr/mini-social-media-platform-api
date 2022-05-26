package com.project.todoApp.dao;

import com.project.todoApp.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoItem,Long> {

}
