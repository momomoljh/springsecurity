package com.example.springsecutiry;

import com.example.springsecutiry.entity.User;
import com.example.springsecutiry.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class SpringSecutiryApplicationTests {
    @Autowired
    private UserMapper userMapper;
    //$2a$10$F0zEwrfY2Tb6j0Ic/ywPNOPxWk/bdKsdcF0COyjQ8X/7QhPEX6ewS
    @Test
    public void passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean matches = bCryptPasswordEncoder.matches("1234"
                , "$2a$10$F0zEwrfY2Tb6j0Ic/ywPNOPxWk/bdKsdcF0COyjQ8X/7QhPEX6ewS");
        System.out.println(matches);
        /*String encode1 = bCryptPasswordEncoder.encode("1234");
        String encode = bCryptPasswordEncoder.encode("1234");
        System.out.println(encode);
        System.out.println(encode1);*/
    }
        @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

}
