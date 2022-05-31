package com.project.todoApp;

import com.mysql.cj.util.TestUtils;
import com.project.todoApp.controller.TodoController;
import com.project.todoApp.entity.TodoItem;
import com.project.todoApp.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {TestContext.class, WebApplicationContext.class})
//@WebAppConfiguration
@SpringBootTest
//@Component
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TodoAppApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TodoController controller;
//    @Autowired
    @MockBean
    private TodoService todoServiceMock;

    static final long MOCK_ID = 1;
    static final String MOCK_TITLE = "Mock Item";
    static final String MOCK_DESC = "Description of mock item";
    final TodoItem mockItemA = new TodoItem(MOCK_ID, MOCK_TITLE + "-A", MOCK_DESC + "-A",false);
    final TodoItem mockItemB = new TodoItem(MOCK_ID, MOCK_TITLE + "-B", MOCK_DESC + "-B",false);

    @Test
    public void findAll_ShouldAddToDoEntriesToTheModelAndRenderToDoListView() throws Exception{
        TodoItem first=new TodoItem((long)1,"Study","Complete by evening",false);
        TodoItem second=new TodoItem((long)2,"Play","Complete by evening",false);

        when(todoServiceMock.findAll()).thenReturn(Arrays.asList(first,second));

        mockMvc.perform(get("/todo/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("list-todos"))
//                .andExpect(forwardedUrl("templates/list-todos.html"))
                .andExpect(model().attribute("todos",hasSize(2)))
                .andExpect(model().attribute("todos",hasItem(
                        allOf(
                                hasProperty("id",is((long)1)),
                                hasProperty("title",is("Study")),
                                hasProperty("description",is("Complete by evening"))
                        )
                )))
                .andExpect(model().attribute("todos",hasItem(
                        allOf(
                                hasProperty("id",is((long)2)),
                                hasProperty("title",is("Play")),
                                hasProperty("description",is("Complete by evening"))
                        )
                )));
        verify(todoServiceMock,times(1)).findAll();
        verifyNoMoreInteractions(todoServiceMock);
    }

    @Test
    public void clickingUpdateButtonGivesTheCorrespondingToDoItem() throws Exception{
        TodoItem found=new TodoItem((long)1,"Study","Complete by evening",false);
        when(todoServiceMock.findById((long)1)).thenReturn(found);

        mockMvc.perform(get("/todo/showFormForUpdate?todoId={todoId}",1))
                .andExpect(status().isOk())
                .andExpect(view().name("todo-form"))
//                .andExpect(model().attribute("todo",hasSize(1)))
                .andExpect(model().attribute("todo",hasProperty("id",is((long)1))))
                .andExpect(model().attribute("todo",hasProperty("title",is("Study"))))
                .andExpect(model().attribute("todo",hasProperty("description",is("Complete by evening"))));
        verify(todoServiceMock,times(1)).findById((long)1);
        verifyNoMoreInteractions(todoServiceMock);
    }

//    @Test
//    public void clickingTheAddButtonToAddNotes_andCheckingWhetherTheFormGetsFilled() throws Exception {
//        String title = String.valueOf("Play");
//        String description = "Complete by Evening";
//
//        mockMvc.perform(post("/todo/showFormForAdd")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("title",title)
//                .param("description",description)
//                .sessionAttr("todo",new TodoItem())
//        )
//                .andExpect(status().isOk());
//
//    }
        @Test
        public void shouldRenderDefaultTemplate() throws Exception {
            mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(forwardedUrl("index.html"));
        }
//    @Test
//    public void canGetTodoItem() throws Exception {
//        mockMvc.perform(get(String.format("/todo/list", mockItemA.getId()))).andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().json(String.format("{\"id\":\"%s\",\"description\":\"%s\",\"title\":\"%s\"}",
//                        mockItemA.getId(), mockItemA.getDescription(), mockItemA.getTitle())));
//    }


}
