package com.example.VicabitBE.controller;

import com.example.VicabitBE.dto.response.ApiResponse;
import com.example.VicabitBE.dto.response.PracticeResponse;
import com.example.VicabitBE.service.PracticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/practices")
public class PracticeController {
    @Autowired
    private PracticeService practiceService;

    @GetMapping
    public ApiResponse<List<PracticeResponse>> getAllPractices() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("User {} is fetching all practices", username);

        List<PracticeResponse> practices = practiceService.getAllPractices();
        return ApiResponse.<List<PracticeResponse>>builder()
                .result(practices)
                .build();
    }
}
