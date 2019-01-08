package com.lxs.graduate.service;

import com.lxs.graduate.entity.User;

public interface UserService {

    public User getUserById(Integer id);

    int addUser(User record);

    int updateUser(User user);

    int deleteUserById(Integer id);

    public User findUserByUsername(String username);

    public User findUserByUserName(String username);

}
