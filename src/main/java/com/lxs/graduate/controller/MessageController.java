package com.lxs.graduate.controller;


import com.lxs.graduate.entity.Message;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping("/findMessageByName")
    public Map<String,Object> findMessageByName(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Message> list=messageService.findMessageByName(user.getUsername());
        for(Message str:list){
            System.out.println(str.toString());
        }
        Map<String,Object> map=new HashMap<>();
        map.put("userlist",list);
        return map;
    }
}
