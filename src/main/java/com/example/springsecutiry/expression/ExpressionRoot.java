package com.example.springsecutiry.expression;

import com.example.springsecutiry.entity.LoginUser;
import com.example.springsecutiry.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("ex")
public class ExpressionRoot {
    public boolean hasAuthority(String authority){
        //获取当前用户权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permission = loginUser.getPermission();
        //判断当前用户权限集合是否包含authority
        return permission.contains(authority);
    }
}
