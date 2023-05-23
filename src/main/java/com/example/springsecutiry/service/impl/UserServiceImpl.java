package com.example.springsecutiry.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springsecutiry.entity.LoginUser;
import com.example.springsecutiry.entity.ResponseResult;
import com.example.springsecutiry.entity.User;
import com.example.springsecutiry.mapper.UserMapper;
import com.example.springsecutiry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import util.JwtUtil;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public ResponseResult login(User user) {
        //AuthenticationManager authenticate认证用户
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        //如果认证通过，使用userid生成一个jwt jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        String strUser = JSON.toJSONString(loginUser);
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        HashMap<String, String> map = new HashMap<>();
        map.put("token",jwt);
        //把完整的用户信息存入redis userid作为key
        stringRedisTemplate.opsForValue().set("login:"+userid,strUser,30, TimeUnit.MINUTES);
        return new ResponseResult<>(200,"登录成功",map);
    }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        //删除redis中的值
        stringRedisTemplate.delete("login:"+userid);
        return new ResponseResult<>(200,"注销成功");
    }
}
