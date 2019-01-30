package com.lxs.graduate.controller;


import com.lxs.graduate.entity.Msg;
import com.lxs.graduate.entity.Product;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.service.ProductService;
import com.lxs.graduate.service.ProductServiceImpl;
import com.lxs.graduate.service.UserServiceImpl;
import com.lxs.graduate.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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
import java.util.List;

@Controller
@RequestMapping("/user")
@Configuration
public class UserController {


    @Value("${img.location}")
    private  String location;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/getName")
    @ResponseBody
    public Object name(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        System.out.println(user);
        return user;
    }

    @GetMapping("/toUpload")
    public String toUpload(){
        return "uploadProduct";
    }

    @GetMapping("/toMyProduct")
    public String toMyProduct(ModelMap model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Product> lists= productService.findProductByUserId(user.getId());
        model.addAttribute("productLists",lists);
        for( int i = 0 ; i < lists.size() ; i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
            System.out.println(lists.get(i));
        }
        return "users/myProduct";
    }


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
    public String uploadImg(@RequestParam("file") MultipartFile file)throws FileNotFoundException, IOException {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=userDetails.getUsername();
        User u=userService.getUserByUsername(username);
        String contentType = file.getContentType();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = sdf.format(d)+"-"+username+".jpg";
        String path="/img/userImg/"+fileName;
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
