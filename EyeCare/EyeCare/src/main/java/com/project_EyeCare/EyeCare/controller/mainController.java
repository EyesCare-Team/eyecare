package com.project_EyeCare.EyeCare.controller;

import com.project_EyeCare.EyeCare.Repository.PdRepository;
import com.project_EyeCare.EyeCare.entity.PD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.PageRequest; // 추가
import org.springframework.data.domain.Pageable;    // 추가

import java.util.List;

@Controller
public class mainController {

    private final PdRepository pdRepository;

    @Autowired
    public mainController(PdRepository pdRepository) {
        this.pdRepository = pdRepository;
    }

    //메인페이지
    @GetMapping("/index")
    public String mainPd(Model model) {
        // 0~7 (8개) 상품만 조회
        Pageable pageable = PageRequest.of(0, 8);
        List<PD> samplePd = pdRepository.findAll(pageable).getContent();
        model.addAttribute("products", samplePd);
        return "/main";
    }

    //병원 찾기
    @GetMapping("/hospital")
    public String hospital() {
        return "/hospital";
    }
}