package com.example.VicabitBE.mapper;

import com.example.VicabitBE.dto.response.ExamResponse;
import com.example.VicabitBE.dto.response.PracticeResponse;
import com.example.VicabitBE.entity.Exam;
import com.example.VicabitBE.entity.Practice;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExamMapper {
    List<ExamResponse> toExamList(List<Exam> exams);
}
