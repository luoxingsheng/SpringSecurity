package com.lxs.graduate.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @program: graduate
 * @description: 国际化配置文件
 * @author: LuoXingSheng
 * @create: 2019-04-10 20:40
 **/

public class MyLocaleResolver implements LocaleResolver {


        @Override
    public Locale resolveLocale(HttpServletRequest request) {
            System.out.println(request);
        String lan = "en_US";
        System.out.println("当前语言："+lan);
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(lan)){
            String[] split = lan.split("_");
            locale = new Locale(split[0],split[1]);
        }
        System.out.println("locale"+locale);
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}