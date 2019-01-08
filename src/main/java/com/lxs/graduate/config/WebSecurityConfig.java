package com.lxs.graduate.config;

import com.lxs.graduate.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by sang on 16-12-22.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService(){ //注册UserDetailsService 的bean
        return new CustomUserService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //设置拦截规则
                .antMatchers("/index", "/css/**","/getUser/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //开启默认登录页面
                .formLogin()
                //默认登录页面
                .loginPage("/login")
                .failureUrl("/login?error")
                //默认登录成功跳转页面
                .defaultSuccessUrl("/index")
                .permitAll()
                .and()
                //设置注销
                .logout()
                .permitAll()
                .and()
                .rememberMe()
                .and()
                .csrf().disable();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("USER");
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder(){

            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String)rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(MD5Util.encode((String)rawPassword));
            }}); //user Details Service验证
    }


}
