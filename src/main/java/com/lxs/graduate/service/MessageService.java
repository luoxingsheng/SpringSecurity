package com.lxs.graduate.service;

import com.lxs.graduate.entity.Message;

import java.util.List;

public interface MessageService {

    public int insertMessage(Message message);

    public int deleteMessage(Integer id);

    public int updateMessage(Message message);

    public List<Message> findMessageByName(String name);
}
