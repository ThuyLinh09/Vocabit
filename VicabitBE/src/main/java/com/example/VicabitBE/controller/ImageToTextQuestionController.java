package com.example.VicabitBE.controller;

import com.example.VicabitBE.dto.response.ApiResponse;
import com.example.VicabitBE.dto.response.ImageToTextQuestionResponse;
import com.example.VicabitBE.dto.response.PracticeResponse;
import com.example.VicabitBE.service.ImageToTextQuestionService;
import com.example.VicabitBE.service.PracticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/questions/image-to-text")
public class ImageToTextQuestionController {
    @Autowired
    private ImageToTextQuestionService service;

    @GetMapping
    public ApiResponse<List<ImageToTextQuestionResponse>> getQuestionsByUnit(@RequestParam int unit) {
        return ApiResponse.<List<ImageToTextQuestionResponse>>builder()
                .result(service.getRandomQuestionsByUnit(unit))
                .build();
    }
}
