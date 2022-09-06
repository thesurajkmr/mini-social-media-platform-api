package com.project.todoApp.repository;

import com.project.todoApp.models.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group, Integer> {

}
