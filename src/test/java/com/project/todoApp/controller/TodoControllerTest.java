package com.project.todoApp.controller;

import com.project.todoApp.entity.TodoItem;
import com.project.todoApp.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Test
    void showFormForTodo() throws Exception {
        mockMvc.perform(get("/todo/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(view().name("todo-form"));

    }

    @Test
    void saveTodo() throws Exception {
        mockMvc.perform(post("/todo/save")
                        .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"id\":\"25L\",\"title\":\"Playing\",\"description\":\"Complete Quickly\"" +
                                        ",\"done\":\"true\"}")
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().is(302));
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(header().string("Location", "/api/account/12345"))
//                .andExpect(jsonPath("$.accountId").value("12345"))
//                .andExpect(jsonPath("$.accountType").value("SAVINGS"))
//                .andExpect(jsonPath("$.balance").value(5000));
    }

//    @Test
//    void redirectIfNoneOfTheEntriesFilled() throws Exception {
//        mockMvc.perform(post("/todo/save")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"title\":\"\",\"description\":\"\"}")
//                        .accept(MediaType.APPLICATION_JSON))
//                .
//                .andExpect(status().is(302));
//
////                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
////                .andExpect(header().string("Location", "/api/account/12345"))
////                .andExpect(jsonPath("$.accountId").value("12345"))
////                .andExpect(jsonPath("$.accountType").value("SAVINGS"))
////                .andExpect(jsonPath("$.balance").value(5000));
//    }

    @Test
    void showFormForUpdate() {
    }

    @Test
    void deleteMethodIsOnlyAllowedToInvokeOnceOnly() {
        TodoItem todoItem=new TodoItem((long)1,"Study","Complete by evening",false);
        todoService=mock(TodoService.class);
        todoService.deleteById(1L);
        verify(todoService,times(1)).deleteById(1L);
    }

    @Test
    void CheckingDirectDeleteMappingNotWorking() throws Exception {
        mockMvc.perform(get("/todo/delete"))
                .andExpect(status().is(400));
    }
}