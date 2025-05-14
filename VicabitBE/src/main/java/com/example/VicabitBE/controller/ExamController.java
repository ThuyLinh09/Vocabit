package com.example.VicabitBE.controller;

import com.example.VicabitBE.dto.response.ApiResponse;
import com.example.VicabitBE.dto.response.ExamResponse;
import com.example.VicabitBE.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/exams")
public class ExamController {
    @Autowired
    private ExamService examService;

    @GetMapping
    public ApiResponse<List<ExamResponse>> getAllExams() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("User {} is fetching all practices", username);

        List<ExamResponse> exams = examService.getAllExams();
        return ApiResponse.<List<ExamResponse>>builder()
                .result(exams)
                .build();
    }
}
