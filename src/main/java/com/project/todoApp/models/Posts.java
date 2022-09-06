package com.project.todoApp.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="post_db")
public class Posts {

    @Id
    private int id;

    private String description;

    User user;

    LocalDateTime timeStamp;
}
