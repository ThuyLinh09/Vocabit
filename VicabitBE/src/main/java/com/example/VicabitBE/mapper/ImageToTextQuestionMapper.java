package com.example.VicabitBE.mapper;

import com.example.VicabitBE.dto.response.ImageToTextQuestionResponse;
import com.example.VicabitBE.dto.response.PracticeResponse;
import com.example.VicabitBE.entity.ImageToTextQuestion;
import com.example.VicabitBE.entity.Practice;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageToTextQuestionMapper {
    List<ImageToTextQuestionResponse> toImageToTextQuestionList(List<ImageToTextQuestion> questions);
}
