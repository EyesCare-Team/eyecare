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
<<<<<<< HEAD
}
=======

    @PostMapping("/member")
    public String addMember(@RequestParam("name") String userName,
                          @RequestParam("email") String email,
                          @RequestParam("password") String password,
                          @RequestParam("phone") String phone,
                          @RequestParam("address") String address,
                          @RequestParam("visionLeft") float visionLeft,
                          @RequestParam("visionRight") float visionRight) {
        
        Member member = new Member();
        member.setUser_name(userName);
        member.setEmail(email);
        member.setPassword(password);
        member.setPhone(phone);
        member.setAddress(address);
        member.setVision_left(visionLeft);
        member.setVision_right(visionRight);

        memberRepository.save(member);
        
        return "redirect:/login";  // 회원가입 후 로그인 페이지로 리다이렉트
    }
}
>>>>>>> parent of f06d52f (체크)
