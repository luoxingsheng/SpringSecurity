package com.lxs.graduate.controller;

import com.lxs.graduate.entity.User;
import com.lxs.graduate.entity.UserRole;
import com.lxs.graduate.service.UserService;
import com.lxs.graduate.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {


    MD5Util md5Util;

    @Autowired
    UserService userService;
    

    @RequestMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model){
        User user=new User();
        user.setUsername(username);
        user.setPassword(md5Util.encode(password));
        user.setCreditScore(100);
        UserRole userRole = new UserRole();
        int insertNum = Integer.parseInt(   userService.addUser(user)+ "");
        Integer id = user.getId();//该对象的自增ID
        userRole.setRoleId(1);
        userRole.setUserId(id);
        userService.addUserRole(userRole);
        return "login";
    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }
}
