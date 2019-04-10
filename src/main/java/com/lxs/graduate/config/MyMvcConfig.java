package com.lxs.graduate.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: graduate
 * @description: 自定义组件
 * @author: LuoXingSheng
 * @create: 2019-04-10 20:42
 **/
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
