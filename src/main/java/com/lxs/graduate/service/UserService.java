package com.lxs.graduate.service;

import com.lxs.graduate.entity.Role;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.entity.UserRole;

public interface UserService {

    public User getUserById(Integer id);

    int addUser(User record);

    int addUserRole(UserRole role);

    int updateUser(User user);

    int updateUserImg(User user);

    int deleteUserById(Integer id);

    public User getUserByUsername(String username);

    public User findUserByUserName(String username);


}
