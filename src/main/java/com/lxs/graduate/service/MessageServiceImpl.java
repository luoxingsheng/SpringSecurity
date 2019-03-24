package com.lxs.graduate.service;

import com.lxs.graduate.dao.MessageMapper;
import com.lxs.graduate.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    MessageMapper messageMapper;

    @Override
    public int insertMessage(Message message) {
        return messageMapper.insert(message);
    }

    @Override
    public int deleteMessage(Integer id) {
        return messageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateMessage(Message message) {
        return messageMapper.updateByPrimaryKey(message);
    }

    @Override
    public List<Message> findMessageByName(String name) {
        return messageMapper.findMessageByName(name);
    }
}
