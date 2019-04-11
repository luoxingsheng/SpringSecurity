package com.lxs.graduate.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * @program: graduate
 * @description: 自定义组件
 * @author: LuoXingSheng
 * @create: 2019-04-10 20:42
 **/
@Configuration
public class MyMvcConfig extends WebMvcConfigurationSupport {


//    @Bean
//    public LocaleResolver localeResolver(){
//        return new MyLocaleResolver();
//    }

    @Bean(name="localeResolver")
    public LocaleResolver localeResolverBean() {

        System.out.println("国际化");
        return new SessionLocaleResolver();
    }

}
