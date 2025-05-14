package com.example.VicabitBE.controller;

import com.example.VicabitBE.dto.response.ApiResponse;
import com.example.VicabitBE.dto.response.ExtraLetterQuestionResponse;
import com.example.VicabitBE.dto.response.MatchQuestionResponse;
import com.example.VicabitBE.service.ExtraLetterQuestionService;
import com.example.VicabitBE.service.MatchQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/questions/match")
public class MatchQuestionController {
    @Autowired
    private MatchQuestionService service;

    @GetMapping
    public ApiResponse<MatchQuestionResponse> getRandomQuestion(@RequestParam int unit) {
        return ApiResponse.<MatchQuestionResponse>builder()
                .result(service.getRandomQuestionByUnit(unit))
                .build();
    }

}
