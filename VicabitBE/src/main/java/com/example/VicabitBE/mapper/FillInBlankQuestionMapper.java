package com.example.VicabitBE.mapper;

import com.example.VicabitBE.dto.response.FillInBlankQuestionResponse;
import com.example.VicabitBE.dto.response.ImageToTextQuestionResponse;
import com.example.VicabitBE.entity.FillInBlankQuestion;
import com.example.VicabitBE.entity.ImageToTextQuestion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FillInBlankQuestionMapper {
    List<FillInBlankQuestionResponse> toFillInBlankQuestionList(List<FillInBlankQuestion> questions);
}
