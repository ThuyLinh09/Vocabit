package com.example.VicabitBE.service;

import com.example.VicabitBE.dto.response.ExamResponse;
import com.example.VicabitBE.dto.response.PracticeResponse;
import com.example.VicabitBE.entity.Exam;
import com.example.VicabitBE.entity.Practice;
import com.example.VicabitBE.mapper.ExamMapper;
import com.example.VicabitBE.mapper.PracticeMapper;
import com.example.VicabitBE.repositories.ExamRepository;
import com.example.VicabitBE.repositories.PracticeRepository;
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
public class ExamService {
    ExamRepository examRepository;
    ExamMapper examMapper;

    public List<ExamResponse> getAllExams() {
        log.info("Getting all practice questions");

        List<Exam> exams = examRepository.findAll();

        return examMapper.toExamList(exams);
    }
}
