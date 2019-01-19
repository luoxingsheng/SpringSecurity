package com.lxs.graduate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/nav")
public String toNavbar(){
    return "common/navbar";
}
}
