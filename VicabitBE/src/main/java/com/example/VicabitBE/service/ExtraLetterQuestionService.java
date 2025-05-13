package com.example.VicabitBE.service;

import com.example.VicabitBE.dto.response.ExtraLetterQuestionResponse;
import com.example.VicabitBE.dto.response.FillInBlankQuestionResponse;
import com.example.VicabitBE.mapper.ExtraLetterQuestionMapper;
import com.example.VicabitBE.mapper.FillInBlankQuestionMapper;
import com.example.VicabitBE.repositories.ExtraLetterQuestionRepository;
import com.example.VicabitBE.repositories.FillInBlankQuestionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExtraLetterQuestionService {
    ExtraLetterQuestionRepository repository;
    ExtraLetterQuestionMapper mapper;

    public List<ExtraLetterQuestionResponse> getRandomQuestionsByUnit(int unit) {
        var questions = repository.findRandom10ByUnit(unit);
        return mapper.toExtraLetterQuestionList(questions);
    }
}
