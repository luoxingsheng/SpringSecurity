package com.lxs.graduate.controller;


import com.lxs.graduate.entity.User;
import com.lxs.graduate.service.UserService;
import com.lxs.graduate.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    UserService userService;

//    @GetMapping("/getUser/{id}")
//    @ResponseBody
//    public User getUser(@PathVariable(name = "id")Integer id) {
//        User user = new User();
//        System.out.println(id);
//        return userService.getUserById(id);
//    }

    @GetMapping("/getUser/{username}")
    @ResponseBody
    public User getUsers(@PathVariable(name = "username")String username) {
        User user = new User();
        System.out.println(username);
        return userService.findUserByUserName(username);
    }
}
