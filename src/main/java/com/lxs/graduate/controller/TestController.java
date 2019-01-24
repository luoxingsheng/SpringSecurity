package com.lxs.graduate.controller;


import com.lxs.graduate.entity.User;
import com.lxs.graduate.service.UserService;
import com.lxs.graduate.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.annotation.Secured;
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
//        User users = new User();
//        System.out.println(id);
//        return userService.getUserById(id);
//    }

    @Secured("ROLE_ADMIN")//此方法只允许 ROLE_ADMIN 角色访问
    @GetMapping("/getUser/{username}")
    @ResponseBody
    public User getUsers(@PathVariable(name = "username")String username) {
        User user = new User();
        System.out.println(username);
        return userService.findUserByUserName(username);
    }
}
