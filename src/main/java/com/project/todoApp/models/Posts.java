package com.project.todoApp.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="social_db")
public class Posts {

    @Id
    private int id;

    private String description;

    private int groupId;

    private int userId;
}
