package com.lxs.graduate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan("com.lxs.graduate.dao")
@SpringBootApplication
public class GraduateApplication {


    public static void main(String[] args) {
        SpringApplication.run(GraduateApplication.class, args);
    }


}

