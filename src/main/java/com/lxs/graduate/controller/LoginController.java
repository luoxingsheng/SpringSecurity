package com.lxs.graduate.controller;

import com.lxs.graduate.entity.Msg;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.entity.UserRole;
import com.lxs.graduate.service.UserService;
import com.lxs.graduate.util.DateUtil;
import com.lxs.graduate.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/logins")
public class LoginController {

    MD5Util md5Util = new MD5Util();

    @Autowired
    UserService userService;

    DateUtil dateUtil=new DateUtil();

    @RequestMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password,
                           @RequestParam String question,@RequestParam String answer){
        User user=new User();
        user.setQuestion(question);
        user.setAnswer(answer);
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


    @ResponseBody
    @RequestMapping("/resetPassword")
    public Map<String,Object> resetPassword(@RequestParam("username")String username,@RequestParam("answer")String answer){
        User user = userService.getUserByUsername(username);
        Msg message = new Msg(null,null,null);
        if(answer.equals(user.getAnswer())){
            user.setPassword(md5Util.encode("123456"));
            userService.updateUser(user);
            message.setContent("密保问题答案正确，密码已被重置为123456，请及时登录修改密码！！！");
        }else{
            message.setContent("密保问题答案错误，如忘记密保问题，联系管理员QQ:893309066,及时处理!");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("msg",message);
        return map;
    }

    @ResponseBody
    @RequestMapping("/getUser")
    public Map<String,Object> getUser(@RequestParam("username")String username){
        User user = userService.getUserByUsername(username);
        Map<String,Object> map = new HashMap<>();
        map.put("users",user);
        return map;
    }

    @ResponseBody
    @RequestMapping("/getUsername")
    public Map<String,Object> getUsername(@RequestParam("username")String username){
        User user = userService.getUserByUsername(username);
        Map<String,Object> map = new HashMap<>();
        map.put("users",user);
        return map;
    }









}
