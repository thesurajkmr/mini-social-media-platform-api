package com.project.todoApp.service;

import com.project.todoApp.dao.TodoRepository;
import com.project.todoApp.entity.TodoItem;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebAppConfiguration
class TodoServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository=mock(TodoRepository.class);
    @Autowired
    private TodoService todoService=mock(TodoService.class);
    @Test
    void findAll() {
        TodoItem first=new TodoItem((long)1,"Study","Complete by evening",false);
        TodoItem second=new TodoItem((long)2,"Play","Complete by evening",false);
        when(todoRepository.findAll()).thenReturn(Arrays.asList(first,second));
        List<TodoItem> actualOutput=todoRepository.findAll();
        assertEquals(actualOutput,Arrays.asList(first,second));
    }

    @Test
    void save() throws Exception {
        TodoItem first=new TodoItem((long)1,"Study","Complete by evening",false);
        when(todoRepository.save(first)).thenReturn(first);


    }

    @Test
    void deleteById() {
        todoService.deleteById(1L);
        todoService.deleteById(1L);
        verify(todoService,times(2)).deleteById(1L);

    }

    @Test
    void findById() {
        TodoItem first=new TodoItem(1L,"Study","Complete by evening",false);
        when(todoRepository.findById(1L)).thenReturn(Optional.of(first));
        Optional<TodoItem> actualOutput=todoRepository.findById(1L);
        assertThat(actualOutput).isNotNull();
  }

}