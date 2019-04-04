package com.lxs.graduate.service;

import com.lxs.graduate.dao.UserMapper;
import com.lxs.graduate.dao.UserRoleMapper;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addUser(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int addUserRole(UserRole role) {
        return userRoleMapper.insert(role);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int updateUserImg(User user) {
        return userMapper.updateUserImg(user);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public User findUserByUserName(String username) {
        return userMapper.findUserByUserName(username);
    }
}
