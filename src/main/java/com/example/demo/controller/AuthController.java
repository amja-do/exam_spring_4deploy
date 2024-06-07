package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("authController")
public class AuthController {

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }
}
