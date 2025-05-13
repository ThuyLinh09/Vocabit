package com.example.VicabitBE.controller;

import com.example.VicabitBE.dto.response.ApiResponse;
import com.example.VicabitBE.dto.response.ExtraLetterQuestionResponse;
import com.example.VicabitBE.dto.response.FillInBlankQuestionResponse;
import com.example.VicabitBE.service.ExtraLetterQuestionService;
import com.example.VicabitBE.service.FillInBlankQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/questions/extra-letter")
public class ExtraLetterQuestionController {
    @Autowired
    private ExtraLetterQuestionService service;

    @GetMapping
    public ApiResponse<List<ExtraLetterQuestionResponse>> getQuestionsByUnit(@RequestParam int unit) {
        return ApiResponse.<List<ExtraLetterQuestionResponse>>builder()
                .result(service.getRandomQuestionsByUnit(unit))
                .build();
    }
}
