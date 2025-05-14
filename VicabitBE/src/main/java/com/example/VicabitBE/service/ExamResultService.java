package com.example.VicabitBE.service;

import com.example.VicabitBE.dto.request.ExamResultRequest;
import com.example.VicabitBE.dto.response.ExamResponse;
import com.example.VicabitBE.entity.Exam;
import com.example.VicabitBE.entity.ExamResult;
import com.example.VicabitBE.entity.User;
import com.example.VicabitBE.mapper.ExamMapper;
import com.example.VicabitBE.mapper.ExamResultMapper;
import com.example.VicabitBE.repositories.ExamRepository;
import com.example.VicabitBE.repositories.ExamResultRepository;
import com.example.VicabitBE.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamResultService {
    ExamResultRepository examResultRepository;
    ExamResultMapper examResultMapper;
    UserRepository userRepository;

    public void saveExamResult(ExamResultRequest request) {
        log.info("Saving exam result for user {}", request.getUsername());

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        ExamResult result = examResultMapper.toEntity(request);
        result.setGrade(user.getClassLevel());

        examResultRepository.save(result);
    }
}
