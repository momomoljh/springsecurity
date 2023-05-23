package com.example.springsecutiry.controller;

import com.example.springsecutiry.entity.ResponseResult;
import com.example.springsecutiry.entity.User;
import com.example.springsecutiry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        //登录
        return userService.login(user);
    }
    @PostMapping("/logout")
    public ResponseResult logout(){
        return userService.logout();
    }
}
