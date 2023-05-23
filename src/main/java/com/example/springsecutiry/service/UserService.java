package com.example.springsecutiry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springsecutiry.entity.ResponseResult;
import com.example.springsecutiry.entity.User;

public interface UserService extends IService<User> {
    ResponseResult login(User user);

    ResponseResult logout();
}
