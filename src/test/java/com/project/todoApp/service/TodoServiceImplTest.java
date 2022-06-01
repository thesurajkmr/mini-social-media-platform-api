package com.project.todoApp.service;

import com.project.todoApp.dao.TodoRepository;
import com.project.todoApp.entity.TodoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.Mock;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebAppConfiguration
class TodoServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TodoRepository todoRepository;
    @InjectMocks
    private TodoServiceImpl todoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock

    }


    @Test
    void findAll() {
        TodoItem first=new TodoItem((long)1,"Study","Complete by evening",false);
        TodoItem second=new TodoItem((long)2,"Play","Complete by evening",false);
//        when(todoRepository.findAll()).thenReturn(Arrays.asList(first,second));
        doReturn(Arrays.asList(first,second)).when(todoRepository).findAll();
        List<TodoItem> actualOutput=todoService.findAll();
        assertEquals(actualOutput,Arrays.asList(first,second));
    }

    @Test
    void save() throws Exception {
        TodoItem first=new TodoItem((long)1,"Study","Complete by evening",false);
//        when(todoRepository.save(first)).thenReturn(first);
        doReturn(first).when(todoRepository).save(first);
        todoService.save(first);
        verify(todoRepository,times(1)).save(first);

    }

    @Test
    void deleteById() {
        todoRepository.deleteById(1L);
        todoRepository.deleteById(1L);
        verify(todoRepository,times(2)).deleteById(1L);

    }

    @Test
    void findById() {
        TodoItem first=new TodoItem(1L,"Study","Complete by evening",false);
        when(todoRepository.findById(1L)).thenReturn(Optional.of(first));
//        doReturn()
        Optional<TodoItem> actualOutput=todoRepository.findById(1L);
        assertThat(actualOutput).isNotNull();
  }

}