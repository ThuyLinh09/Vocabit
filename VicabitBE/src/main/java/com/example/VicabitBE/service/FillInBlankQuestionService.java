package com.example.VicabitBE.service;

import com.example.VicabitBE.dto.response.FillInBlankQuestionResponse;
import com.example.VicabitBE.dto.response.ImageToTextQuestionResponse;
import com.example.VicabitBE.mapper.FillInBlankQuestionMapper;
import com.example.VicabitBE.mapper.ImageToTextQuestionMapper;
import com.example.VicabitBE.repositories.FillInBlankQuestionRepository;
import com.example.VicabitBE.repositories.ImageToTextQuestionRepository;
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
public class FillInBlankQuestionService {
    FillInBlankQuestionRepository repository;
    FillInBlankQuestionMapper mapper;

    public List<FillInBlankQuestionResponse> getRandomQuestionsByUnit(int unit) {
        var questions = repository.findRandom10ByUnit(unit);

        // Trộn đáp án mỗi câu
        questions.forEach(q -> Collections.shuffle(q.getOptions()));

        return mapper.toFillInBlankQuestionList(questions);
    }
}
