package com.project.todoApp.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="group_db")
public class Group {

    @Id
    private int id;

    private String title;

    List<User> userList;

    public void addUser(User theUser) {
        userList.add(theUser);
    }
}
