package com.project.todoApp.controller;

import com.project.todoApp.models.Group;
import com.project.todoApp.models.User;
import com.project.todoApp.repository.GroupRepository;
import com.project.todoApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        userRepository.save(user);
        List<Group> groups=user.getGroups();

        if(groups!=null){
            for (Group group : groups
                 ) {
                groupRepository.save(group);
            }
        }
        return "User saved with id : " + user.getId();
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
