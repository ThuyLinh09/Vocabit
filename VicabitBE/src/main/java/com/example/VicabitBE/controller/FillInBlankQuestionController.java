package com.example.VicabitBE.controller;

import com.example.VicabitBE.dto.response.ApiResponse;
import com.example.VicabitBE.dto.response.FillInBlankQuestionResponse;
import com.example.VicabitBE.dto.response.ImageToTextQuestionResponse;
import com.example.VicabitBE.service.FillInBlankQuestionService;
import com.example.VicabitBE.service.ImageToTextQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/questions/fill-in-blank")
public class FillInBlankQuestionController {
    @Autowired
    private FillInBlankQuestionService service;

    @GetMapping
    public ApiResponse<List<FillInBlankQuestionResponse>> getQuestionsByUnit(@RequestParam int unit) {
        return ApiResponse.<List<FillInBlankQuestionResponse>>builder()
                .result(service.getRandomQuestionsByUnit(unit))
                .build();
    }
}
