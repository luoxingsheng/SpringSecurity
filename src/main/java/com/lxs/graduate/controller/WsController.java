package com.lxs.graduate.controller;

import com.lxs.graduate.entity.User;
import com.lxs.graduate.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ws")
public class WsController {

    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/websocket")
    public String webSocket( Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            logger.info("跳转到websocket的页面上");
            model.addAttribute("username", user.getUsername());
            return "websockets";
        } catch (Exception e) {
            logger.info("跳转到websocket的页面上发生异常，异常信息是：" + e.getMessage());
            return "error";
        }

    }

    @RequestMapping("/chat")
    public String chat( @RequestParam Integer sellId, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String hisName = userService.getUserById(sellId).getUsername();
        try {
            logger.info("跳转到聊天页面上");
            model.addAttribute("hisName",hisName);
            model.addAttribute("myName", user.getUsername());
            return "chat";
        } catch (Exception e) {
            logger.info("跳转到websocket的页面上发生异常，异常信息是：" + e.getMessage());
            return "error";
        }

    }

    @RequestMapping("/toChat")
    public String toChat(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("myName",user.getUsername());
        return "chat";
    }
}

