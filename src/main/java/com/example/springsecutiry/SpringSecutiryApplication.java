package com.example.springsecutiry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@MapperScan("com.example.springsecutiry.mapper")
public class SpringSecutiryApplication {

    public static void main(String[] args) {
       SpringApplication.run(SpringSecutiryApplication.class, args);
    }

}
