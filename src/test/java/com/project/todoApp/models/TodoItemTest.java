package com.project.todoApp.models;

import com.project.todoApp.entity.TodoItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodoItemTest {

    @Test
    public void testEqualsObject(){
        final TodoItem itemA=new TodoItem();
        final TodoItem itemB=new TodoItem((long)1,"Study","Complete by tonight 9 pm",true);
        final TodoItem itemC=new TodoItem((long)1,"Study","Complete by tonight 9 pm",true);
        final Object nonObject=new Object();
        Assertions.assertTrue(itemA.equals(itemA));
        Assertions.assertTrue(itemB.equals(itemB));
        Assertions.assertFalse(itemC.equals(itemB)); //id cannot be same as it is unique
        Assertions.assertFalse(itemA.equals(itemB));
        Assertions.assertFalse(itemA.equals(nonObject));
    }
}
