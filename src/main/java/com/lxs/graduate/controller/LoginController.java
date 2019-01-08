package com.lxs.graduate.controller;

import com.lxs.graduate.entity.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {



    @GetMapping("/login")
    public String toLogin(){
        return "login";
    }

    @GetMapping("/index")
    public String toIndex(){
        return "index";
    }

//    @RequestMapping("/")
//    public String index(Model model){
//        Msg msg =  new Msg("测试标题","测试内容","额外信息，只对管理员显示");
//        model.addAttribute("msg", msg);
//        return "home";
//    }

}
