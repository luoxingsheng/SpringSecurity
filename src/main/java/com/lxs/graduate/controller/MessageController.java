package com.lxs.graduate.controller;


import com.lxs.graduate.entity.Message;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping("/findList")
    public Map<String,Object> findList(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Message> list=messageService.findList(user.getUsername());
        Map<String,Object> map=new HashMap<>();
        map.put("userlist",list);
        return map;
    }

    @RequestMapping("/findMessageByName")
    public Map<String,Object> findMessageByName(@RequestParam("name")String name){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Message> list=messageService.findMessageByName(user.getUsername(),name);
        Map<String,Object> map=new HashMap<>();
        map.put("messages",list);
        return map;
    }

    @RequestMapping("/saveMessage")
    public void saveMessage(@RequestParam("sendtime") String sendTime,@RequestParam("message")String message,
                            @RequestParam("to")String to,@RequestParam("username")String username){

        String title=sendTime+" "+username;
        Message message1=new Message();
        message1.setmTitle(title);
        message1.setContent(message);
        message1.setFromUser(username);
        message1.setToUser(to);
        message1.setIstransport(1);
        message1.setCreateTime(new Timestamp(System.currentTimeMillis()));
        System.out.println(message1.toString());
        messageService.insertMessage(message1);
    }
}
