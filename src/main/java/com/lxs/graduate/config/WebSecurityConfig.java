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
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;

/**
 * Created by sang on 16-12-22.
 */
//@Configuration
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
                .antMatchers("/logins/**","/ ","/css/**","/js/**","/img/**","/redis/**","/index/**")
                .permitAll()
                .antMatchers("/user/**","/order/**","/cart/**","/product/**","/ws/**").hasRole("USER")
              .anyRequest()
               .authenticated()
               .and()
              //开启默认登录页面
              .formLogin()
              .loginPage("/login")
               //默认登录页面
                .failureUrl("/login?error")
               //默认登录成功跳转页面
              .defaultSuccessUrl("/")
                .permitAll()
              .and()
              //设置注销
               .logout()
              .logoutSuccessUrl("/")
             .permitAll()
               .and()
              .rememberMe()
              .and()
              .csrf().disable();
    }

//   //4在内存中配置两个用户 wyf 和 wisely ,密码和用户名一致,角色是 USER
//   @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//       auth
//              .inMemoryAuthentication()
//            .withUser("admin").password("admin ").roles("USER")
//               .and()
//                .withUser("lxs").password("lxs").roles("USER");
//   }


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
           }}); //users Details Service verification
    }

   @Override
    public void configure(WebSecurity web) throws Exception {
        //Solve the problem of static resources being intercepted
        web.ignoring().antMatchers("/css/**","/js/**");
   }

}


//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/","/login").permitAll()//根路径和/login路径不拦截
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login") //2登陆页面路径为/login
//                .defaultSuccessUrl("/chat") //3登陆成功转向chat页面
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }
//
//    //4在内存中配置两个用户 wyf 和 wisely ,密码和用户名一致,角色是 USER
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.
//                inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("lxs").password(new BCryptPasswordEncoder().encode("lxs")).roles("USER")
//                .and()
//                .withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("USER");
//
//
//    }
//    //5忽略静态资源的拦截
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/css/**","/js/**");
//    }
//
//}
//
