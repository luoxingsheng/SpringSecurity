package com.lxs.graduate.controller;

import com.lxs.graduate.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class WsController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/websocket")
    public String webSocket( Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            logger.info("跳转到websocket的页面上");
            model.addAttribute("username", user.getUsername());
            return "websocket";
        } catch (Exception e) {
            logger.info("跳转到websocket的页面上发生异常，异常信息是：" + e.getMessage());
            return "error";
        }

    }
}

