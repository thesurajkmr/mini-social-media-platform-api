package com.project.todoApp.controller;


import com.project.todoApp.models.Group;
import com.project.todoApp.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @GetMapping("/getAll")
    public List<Group> getAllGroups()
    {
        List<Group> groups = groupRepository.findAll();
        List<Group> output=new ArrayList<>();
        for (Group group:groups
             ) {
            if(group.getTitle()==null){
                continue;
            }
            output.add(group);
        }
        return output;
    }
}
