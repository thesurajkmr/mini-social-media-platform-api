package com.project.todoApp.repository;

import com.project.todoApp.models.Posts;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Posts, Integer> {

}
