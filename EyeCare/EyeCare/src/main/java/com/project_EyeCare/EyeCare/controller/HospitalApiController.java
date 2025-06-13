package com.project_EyeCare.EyeCare.controller;

import com.project_EyeCare.EyeCare.entity.HospitalEntity;
import com.project_EyeCare.EyeCare.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hospitals")
public class HospitalApiController {

    private final HospitalService hospitalService;

    @GetMapping("/nearby")
    public List<HospitalEntity> getNearby(@RequestParam double lat, @RequestParam double lng) {
        return hospitalService.findAndSaveNearby(lat, lng);
    }
}
