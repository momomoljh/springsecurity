package com.example.springsecutiry.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    @PreAuthorize("hasRole('ROLE_CESHI')")
    public String hello(){
        return "SDADAKLDASJDASDASDASDA";
    }
}
