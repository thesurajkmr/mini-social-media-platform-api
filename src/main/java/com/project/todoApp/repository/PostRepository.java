package com.project.todoApp.repository;

import com.project.todoApp.models.Posts;
import com.project.todoApp.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends MongoRepository<Posts, Integer> {


    List<Posts> findByUser(User theUser);


    List<Posts> findByUserOrderByTimeStampDesc(User theUser);
}
