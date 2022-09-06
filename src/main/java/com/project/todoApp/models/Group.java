package com.project.todoApp.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Document(collection="group_db")
public class Group {

    @Id
    private int id;

    private String title;

    List<User> userList;

    public Group(int id, String title, List<User> userList) {
        this.id = id;
        this.title = title;
        this.userList=userList;
    }

    public Group() {
    }


    public void addUser(User theUser) {
        if(userList==null)
            userList=new ArrayList<>();
        userList.add(theUser);
    }
}
