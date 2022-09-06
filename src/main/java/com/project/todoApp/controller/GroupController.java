package com.project.todoApp.controller;


import com.project.todoApp.models.Group;
import com.project.todoApp.models.User;
import com.project.todoApp.repository.GroupRepository;
import com.project.todoApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.Map.Entry;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/addGroup")
    public String addGroup(@RequestBody Group theGroup){
        Map<User,Boolean> theUserToBeRemoved= new HashMap<>();
        /*for (User theUser:theGroup.getUserList()
             ) {
            if (userRepository.findById(theUser.getId()).isPresent())
            {
                continue;
            }
            else{
                theUserToBeRemoved.put(theUser,true);
            }
        }
        Iterator hmIterator = theUserToBeRemoved.entrySet().iterator();

        while (hmIterator.hasNext()) {

            Entry mapElement
                    = (Entry)hmIterator.next();
            theGroup.getUserList().remove(mapElement.getKey());

        }*/
        groupRepository.save(theGroup);
        return "Group added with id "+theGroup.getId();
    }

    @PutMapping("/addUserToGroup/{id}")
    public String addGroup(@PathVariable int id, @RequestBody User theUser){
        Group grp=groupRepository.findById(id).get();
        grp.addUser(theUser);
        groupRepository.save(grp);
        return "Group saved with id "+ id;
    }

    @GetMapping("/getAllGroups")
    public List<Group> findAllGroups(){
        return groupRepository.findAll();
    }

    @GetMapping("/getGroup/{id}")
    public Group getGroupById(@PathVariable int id){
        Optional<Group> group = groupRepository.findById(id);
        return group.orElse(null);
    }
    /*@GetMapping("/getAll")
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
    }*/


    @PostMapping("/leaveGroup/{groupId}/{userId}")
    public String leaveGroup(@PathVariable int groupId, @PathVariable int userId)
    {
        User theUser=userRepository.findById(userId).get();
        if(!groupRepository.findById(groupId).isPresent()){
            return "No such group exists";
        }
        Group theGroup=groupRepository.findById(groupId).get();
        for (User currentUser:theGroup.getUserList()
             ) {
            if(currentUser.getId()==theUser.getId()){
                theGroup.getUserList().remove(currentUser);
                break;
            }
        }
        deleteGroup(groupId);
        System.out.println(theGroup);
        groupRepository.save(theGroup);
        return "User with User id "+ theUser.getId()+" removed from group with group id "+theGroup.getId();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGroup(@PathVariable int id){
        Group theGroup=groupRepository.findById(id).get();
        groupRepository.delete(theGroup);
    }

}
