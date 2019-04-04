package com.lxs.graduate.controller;

import com.lxs.graduate.entity.User;
import com.lxs.graduate.entity.UserRole;
import com.lxs.graduate.service.UserService;
import com.lxs.graduate.util.DateUtil;
import com.lxs.graduate.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/logins")
public class LoginController {

    MD5Util md5Util;

    @Autowired
    UserService userService;

    DateUtil dateUtil=new DateUtil();

    @RequestMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password){
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

    @RequestMapping("/toUpdatePassword")
    public String toUpdatePassword(){
        return "updatePassowrd";
    }

    @RequestMapping("/toValidate")
    public String toValidate(){
        return "validateQuestion";
    }


    @RequestMapping("/updatePassword")
    public void updatePassword(){

    }

    @RequestMapping("/getUser")
    public Map<String,String> getUser(@RequestParam("username")String username){
        String question = userService.getUserByUsername(username).getQuestion();
        Map<String,String> map = new HashMap<>();
        map.put("question",question);
        return map;
    }







}
