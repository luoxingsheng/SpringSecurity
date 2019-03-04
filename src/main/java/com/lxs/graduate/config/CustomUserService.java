package com.lxs.graduate.config;

import com.lxs.graduate.entity.Role;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
public class CustomUserService implements UserDetailsService { //自定义UserDetailsService 接口

    @Autowired
    UserServiceImpl userService;

    @Override
    public User loadUserByUsername(String username) { //重写loadUserByUsername 方法获得 userdetails 类型用户

//        User users=userService.getUserById(1);
        User user = userService.findUserByUserName(username);
        if(user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        for(Role role:user.getRoles())
        {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            System.out.println(role.getRoleName());
        }
        user.setGrantedAuthorities(authorities); //用于登录时 @AuthenticationPrincipal 标签取值
        User user1=new User();
        user1.setId(user.getId());
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setGrantedAuthorities(authorities);
        return user1;
    }
}
