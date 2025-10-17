package com.project_EyeCare.EyeCare.controller.Account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class AccountController {
    @GetMapping("/register")
    public String register(){
        return "/account/register.html";
    }

    @PostMapping("/register")
    public String addregister(){
        return "/account/register.html";
    }
}
