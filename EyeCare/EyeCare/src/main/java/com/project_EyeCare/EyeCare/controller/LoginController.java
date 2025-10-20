package com.project_EyeCare.EyeCare.controller;

import com.project_EyeCare.EyeCare.controller.Account.MemberRepository;
import com.project_EyeCare.EyeCare.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final MemberRepository memberRepository;

    @Autowired
    public LoginController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "/account/login.html";
    }

    @GetMapping("/register")
    public String register() {
        return "/account/register.html";
    }

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
        
        return "redirect:/login";
    }
}