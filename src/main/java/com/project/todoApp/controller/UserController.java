package com.project.todoApp.controller;

import com.project.todoApp.models.Group;
import com.project.todoApp.models.Posts;
import com.project.todoApp.models.User;
import com.project.todoApp.repository.GroupRepository;
import com.project.todoApp.repository.PostRepository;
import com.project.todoApp.repository.UserRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        userRepository.save(user);
        /*List<Group> groups=user.getGroups();

        if(groups!=null){
            for (Group group : groups
                 ) {
                groupRepository.save(group);
            }
        }*/
        return "User saved with id : " + user.getId();
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/getFeed/{id}")
    public List<Posts> generateFeed(@PathVariable int id){
        Optional<User> theUser=userRepository.findById(id);
        if(theUser.isEmpty()) return Collections.emptyList();
        User user=theUser.get();
        return postRepository.findByUserOrderByTimeStampDesc(user);
    }

}
