package com.project.todoApp.controller;

import com.project.todoApp.models.Group;
import com.project.todoApp.models.Posts;
import com.project.todoApp.models.User;
import com.project.todoApp.repository.GroupRepository;
import com.project.todoApp.repository.PostRepository;
import com.project.todoApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @GetMapping("/getAllPosts")
    public List<Posts> getAllPosts(){
        List<Posts> posts = postRepository.findAll();
/*        List<Posts> output=new ArrayList<>();
        for (Posts post:posts
        ) {
            if(post.getDescription()==null){
                continue;
            }
            output.add(post);
        }*/
        return posts;
    }




    @PostMapping("/addPosts/{id}")
    public String addPosts(@RequestBody Posts post, @PathVariable int id){
        if(!groupRepository.findById(id).isPresent()){
            return "No such Group found";
        }
        User theUser=post.getUser();
        if(!userRepository.findById(theUser.getId()).isPresent())
        {
            return "No such user found";
        }
        post.setUser(userRepository.findById(theUser.getId()).get());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(LocalDateTime.now());
        post.setTimeStamp(LocalDateTime.now());
        postRepository.save(post);

        return "post saved to user with id " + theUser.getId();

       /* int groupId=post.getGroupId();
        int userId=post.getUserId();

        boolean isGroupIdPresent=false;
        for (Group group:groupRepository.findAll()
             ) {
                if(group.getId()==groupId){
                    isGroupIdPresent=true;
                }
        }
        if(!isGroupIdPresent) return "Group id invalid, Post can't be added";

        boolean isUserIdPresent=false;
        for(User user:userRepository.findAll())
        {
            if(user.getId()==userId){
                isUserIdPresent=true;
            }
        }
        if(!isUserIdPresent) return "User id invalid, User can't be added";

        for(Posts currentPost:postRepository.findAll()){
            if(currentPost.getId()==post.getId()){
                return "Post id is already Present, try again with a new id";
            }
        }
        postRepository.save(post);

        User user=userRepository.findById(post.getUserId()).get();



        System.out.println(user);
        *//*User(id=1, name=Suraj, gender=male, groups=[Group(id=2, title=Dav Kalinga), Group(id=3, title=Kanhan Valley)], posts=null)
        The correct user id is getting fetched from the database, but while operating
        on it gives an error and the earlier data that is the upper one is not
        getting updated with the post value that is added to it.*//*


        user.addPosts(post);
        userRepository.save(user);
        return "post added with id :" + user.getId();*/

    }


}
