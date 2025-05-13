package com.example.VicabitBE.service;

import com.example.VicabitBE.dto.response.ExtraLetterQuestionResponse;
import com.example.VicabitBE.dto.response.MatchQuestionResponse;
import com.example.VicabitBE.entity.MatchQuestion;
import com.example.VicabitBE.mapper.ExtraLetterQuestionMapper;
import com.example.VicabitBE.mapper.MatchQuestionMapper;
import com.example.VicabitBE.repositories.ExtraLetterQuestionRepository;
import com.example.VicabitBE.repositories.MatchQuestionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MatchQuestionService {
    MatchQuestionRepository repository;

    public List<MatchQuestionResponse> getRandomQuestionsByUnit(int unit) {
        List<MatchQuestion> questions = repository.findRandom10ByUnit(unit);

        return questions.stream().map(q -> {
            List<String> shuffledEn = new ArrayList<>(q.getChoices_en());
            List<String> shuffledVn = new ArrayList<>(q.getChoices_vn());
            Collections.shuffle(shuffledEn);
            Collections.shuffle(shuffledVn);

            return MatchQuestionResponse.builder()
                    .choices_en(shuffledEn)
                    .choices_vn(shuffledVn)
                    .correctMatches(q.getCorrectMatches()) // Có thể ẩn nếu không muốn gửi đáp án
                    .build();
        }).toList();
    }
}
