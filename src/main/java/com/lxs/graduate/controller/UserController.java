package com.lxs.graduate.controller;


import com.lxs.graduate.entity.Msg;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.service.UserServiceImpl;
import com.lxs.graduate.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/user")
@Configuration
public class UserController {


    Msg msg;

    @Value("${img.location}")
    private  String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Autowired
    UserServiceImpl userService;


    @RequestMapping("/toUser")
    public String toUser(ModelMap model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user1 = userService.findUserByUserName(currentPrincipalName);
        User user=userService.getUserById(user1.getId());
        System.out.println(user.toString());
        model.addAttribute("id",user.getId());
        model.addAttribute("password",user.getPassword());
        model.addAttribute("icon",user.getIcon());
        model.addAttribute("username",user.getUsername());
        model.addAttribute("nickname",user.getNickname());
        model.addAttribute("tel",user.getTel());
        model.addAttribute("email",user.getEmail());
        model.addAttribute("address",user.getAddress());
        model.addAttribute("creditScore",user.getCreditScore());
        return "users/user";
    }

    @RequestMapping("/toUserImg")
    public String toUserImg(){
        return "users/userImg";
    }

    //处理文件上传
    @RequestMapping(value="/uploadImg", method = RequestMethod.POST)
    public String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request)throws FileNotFoundException, IOException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=userDetails.getUsername();
        System.out.println(username);
        User u=userService.getUserByUsername(username);
        System.out.println(u);
        String contentType = file.getContentType();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = sdf.format(d)+"-"+username+".jpg";
        String path="/img/userImg/"+fileName;
        System.out.println(fileName);
        System.out.println(path);
        u.setIcon(path);
        userService.updateUserImg(u);
        try {
            FileUtil.uploadFile(file.getBytes(), location, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        //返回json
        return "index";
        //返回json
    }

    @RequestMapping("/updateUserInfo")
    public String update(User user){
        System.out.println(user);
        userService.updateUser(user);
        return "index";
    }




}
