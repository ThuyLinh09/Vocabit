package com.example.VicabitBE.mapper;

import com.example.VicabitBE.dto.request.ExamResultRequest;
import com.example.VicabitBE.dto.response.ExamResponse;
import com.example.VicabitBE.entity.Exam;
import com.example.VicabitBE.entity.ExamResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExamResultMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "grade", ignore = true) // 👈 bỏ qua grade
    @Mapping(target = "submittedAt", expression = "java(java.time.LocalDateTime.now())")
    ExamResult toEntity(ExamResultRequest request);
}
