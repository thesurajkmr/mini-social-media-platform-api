package com.project.todoApp;

import com.project.todoApp.dao.TodoRepository;
import com.project.todoApp.entity.TodoItem;
import com.project.todoApp.service.TodoService;
import com.project.todoApp.service.TodoServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TodoRepositoryTests {
    @Autowired
    private TodoRepository todoRepository=mock(TodoRepository.class);
//    @Autowired
//    TodoRepository repository;

    @Test
    @Rollback(value = false)
    @Order(1)
    public void testCreateTodoItem(){
        TodoItem itemB=new TodoItem((long)1,"Study","Complete by tonight 9 pm",true);
        TodoItem savedTodoItem=todoRepository.save(itemB);
        assertNotNull(savedTodoItem);
    }

//    @Test
//    @Order(2)
//    public void testFindProductByNameExist(){
//        String drugName = "metacin255";
//        Drug drug = drugRepository.findByDrugName(drugName);
//        assertThat(drug.getDrugName()).isEqualTo(drugName);
//    }
//
//    @Test
//    @Order(3)
//    public void testFindProductByNameNotExist(){
//        String drugName = "covishield";
//        Drug drug = drugRepository.findByDrugName(drugName);
//        assertNull(drug);
//    }
//
//    @Test
//    @Order(4)
//    public void testUpdateDrug(){
//        TodoItem itemB=new TodoItem((long)5,"Study","Complete by tonight 9 pm",true);
//        itemB.setTitle("New");
//        todoRepository.save(itemB);
//        when(todoRepository.findById((long)5)).thenReturn(Optional.of(itemB));
//        Optional<TodoItem> updatedTodoItem = todoRepository.findById((long)5);
//        System.out.println(updatedTodoItem);
////        assertThat(updatedTodoItem.get().getTitle()).isEqualTo("New");
//
//    }



}
