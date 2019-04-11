package com.lxs.graduate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

import static org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME;

/**
 * @program: graduate
 * @description: 国际化控制器
 * @author: LuoXingSheng
 * @create: 2019-04-11 09:36
 **/


@Controller
public class i18nController {

//    @GetMapping("/i18n")
//    public String toIndex(@RequestParam String lan) {
//        System.out.println(lan);
//        return "/login";
//    }

    @Autowired
    LocaleResolver localeResolver;

    @RequestMapping("i18n")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session=request.getSession();
        localeResolver.setLocale(request,response, Locale.ENGLISH);
        System.out.println(session.getAttribute(LOCALE_SESSION_ATTRIBUTE_NAME));
        return "/login";
    }
}