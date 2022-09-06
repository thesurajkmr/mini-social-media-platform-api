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
@Document(collection="social_db")
public class User {

    @Id
    private int id;

    private String name;

    private String gender;

    private List<Group> groups;

    private List<Posts> posts;


    public void addPosts(Posts post) {
        posts.add(post);
    }
}
