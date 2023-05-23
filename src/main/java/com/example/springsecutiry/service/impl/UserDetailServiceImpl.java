package com.example.springsecutiry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springsecutiry.entity.LoginUser;
import com.example.springsecutiry.entity.User;
import com.example.springsecutiry.mapper.MenuMapper;
import com.example.springsecutiry.mapper.UserMapper;
import com.example.springsecutiry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        //如果没有查询到用户就抛出异常
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误!");
        }
        //  查询对应权限信息 添加到LoginUser中
        //List<String> list = new ArrayList<>(Arrays.asList("test","admin"));
        List<String> list = menuMapper.selectPermsByUserid(user.getId());
        //把数据封装成UserDetails返回
        return new LoginUser(user,list);
    }
}
