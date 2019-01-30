package com.lxs.graduate.controller;


import com.lxs.graduate.entity.Msg;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.redis.CartPrefix;
import com.lxs.graduate.redis.RedisService;
import com.lxs.graduate.redis.RedisServices;
import com.lxs.graduate.service.UserService;
import com.lxs.graduate.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {

    @Autowired
    UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisServices redisServices;

//    @ResponseBody
//    @RequestMapping(value = "/test",method = RequestMethod.GET)
//    public Object demoTest(){
//        redisService.set("nn","lxs");
//        Object t=redisService.get("nn");
//       return t;
//
//    }


    @ResponseBody
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public Object demoTest(){
        System.out.println(redisServices.set(CartPrefix.getCartList,"name","lxs"));
        Object t=redisServices.get(CartPrefix.getCartList,"name");
        return t;

    }


    @Secured("ROLE_ADMIN")//此方法只允许 ROLE_ADMIN 角色访问
    @GetMapping("/getUser/{username}")
    @ResponseBody
    public User getUsers(@PathVariable(name = "username")String username) {
        User user = new User();
        System.out.println(username);
        return userService.findUserByUserName(username);
    }

    @GetMapping("/msg")
    public String textMsg(ModelMap model){
        Msg msg=new Msg("购物车信息","添加购物车成功",null);
        model.addAttribute("message",msg);
        return "notices";
    }
}
