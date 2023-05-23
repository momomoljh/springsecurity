package com.example.springsecutiry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springsecutiry.mapper")
public class SpringSecutiryApplication {

    public static void main(String[] args) {
       SpringApplication.run(SpringSecutiryApplication.class, args);
    }

}
