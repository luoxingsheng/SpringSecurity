package com.lxs.graduate.controller;


import com.lxs.graduate.entity.Msg;
import com.lxs.graduate.entity.Order;
import com.lxs.graduate.entity.Product;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.service.*;
import com.lxs.graduate.util.FtpFileUtil;
import com.lxs.graduate.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
@Configuration
public class UserController {


    MD5Util md5Util = new MD5Util();

    @Value("${img.location}")
    private  String location;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @RequestMapping("/getName")
    @ResponseBody
    public Object name(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return user;
    }

    @GetMapping("/toUpload")
    public String toUpload(){
        return "products/uploadProduct";
    }

    @GetMapping("/toMyProduct")
    public String toMyProduct(ModelMap model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Product> lists= productService.findProductByUserId(user.getId());
        model.addAttribute("productLists",lists);
        return "users/myProduct";
    }

    @RequestMapping("/toUser")
    public String toUser(ModelMap model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user1 = userService.findUserByUserName(currentPrincipalName);
        User user=userService.getUserById(user1.getId());
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
        String[] names = file.getOriginalFilename().split("\\.");
        String fileName = sdf.format(d)+"-"+username+names[1];
        String path="http://47.107.133.187:8080/img/"+fileName;
        InputStream inputStream=file.getInputStream();
        u.setIcon(path);
        userService.updateUserImg(u);
        try {
            FtpFileUtil.uploadFile(fileName,inputStream);
        } catch (Exception e) {
            // TODO: handle exception
        }
        //返回json
        return "/";
        //返回json
    }

    @RequestMapping("/updateUserInfo")
    public String update(User user, ModelMap model){
        userService.updateUser(user);
        Msg msg=new Msg("用户信息修改","用户信息成功",null);
        model.addAttribute("message",msg);
        return "notices";
    }

    @RequestMapping("/getOrderByBuyId")
    public String getOrderByBuyId(ModelMap map){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> list=orderService.findOrderByBuyId(user.getId());
        map.put("buyOrders",list);
        return "/users/buyOrder";
    }

    @RequestMapping("/getOrderBySellId")
    public String getOrderBySellId(ModelMap map){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> list=orderService.findOrderBySellId(user.getId());
        map.put("sellOrders",list);
        return "/users/sellOrder";
    }

    @RequestMapping("/getUnfinishedOrders")
    public String getUnfinishedOrders(ModelMap map){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> list=orderService.findUnfinishedOrders(user.getId());
        map.put("unfinishedOrders",list);
        return "/users/unfinishedOrder";
    }



    @GetMapping("/hisProduct")
    public String hisProduct(@RequestParam Integer sell_id, ModelMap model){
        User user = userService.getUserById(sell_id);
        List<Product> lists= productService.findProductByUserId(sell_id);
        model.addAttribute("seller",user);
        model.addAttribute("sellId",sell_id);
        model.addAttribute("productLists",lists);
        return "users/hisProduct";
    }

    @RequestMapping("/toUpdatePassword")
    public String toUpdatePassword(){
        return "users/updatePassword";
    }

    @ResponseBody
    @RequestMapping("/validate")
    public Map<String,Object> validatePassword(@RequestParam("username")String username, @RequestParam("password")String password){
        User user = userService.getUserByUsername(username);
        Map<String,Object> map = new HashMap<>();
        if(user.getPassword().equals(md5Util.encode(password))){
            Msg msg=new Msg("1","密码输入正确",null);
            map.put("msg",msg);
        }else{
            Msg msg=new Msg("0","密码输入错误",null);
            map.put("msg",msg);
        }
        return map;
    }


    @ResponseBody
    @RequestMapping("/updatePassword")
    public Map<String,Object> udatePassword(@RequestParam("username")String username, @RequestParam("password")String password){
        User user = userService.getUserByUsername(username);
        user.setPassword(md5Util.encode(password));
        userService.updateUser(user);
        Map<String,Object> map = new HashMap<>();
        Msg msg=new Msg(null,"密码修改成功",null);
        map.put("msg",msg);
        return map;
    }

}
