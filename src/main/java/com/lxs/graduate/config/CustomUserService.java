package com.lxs.graduate.config;

import com.lxs.graduate.entity.Role;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

//@Service
public class CustomUserService implements UserDetailsService { //自定义UserDetailsService 接口

    @Autowired
    UserServiceImpl userService;

    @Override
    public User loadUserByUsername(String username) { //Rewrite the loadUserByUsername method to get the userdetails type user

        User user = userService.findUserByUserName(username);
        if(user==null){
            throw new UsernameNotFoundException("Username does not exist");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //Permission to add users. Just add user permissions to the authorities and everything will be fine.
        for(Role role:user.getRoles())
        {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            System.out.println(role.getRoleName());
        }
        user.setGrantedAuthorities(authorities); //@AuthenticationPrincipal tag value when used for login
        User user1=new User();
        user1.setId(user.getId());
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setGrantedAuthorities(authorities);
        return user1;
    }
}
