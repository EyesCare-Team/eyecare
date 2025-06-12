package com.project_EyeCare.EyeCare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "/account/login.html";
    }

    @GetMapping("/register")
    public String register(){
        return "/account/register.html";
    }
}
