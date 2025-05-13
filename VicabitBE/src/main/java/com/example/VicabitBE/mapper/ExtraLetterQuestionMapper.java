package com.example.VicabitBE.mapper;

import com.example.VicabitBE.dto.response.ExtraLetterQuestionResponse;
import com.example.VicabitBE.dto.response.FillInBlankQuestionResponse;
import com.example.VicabitBE.entity.ExtraLetterQuestion;
import com.example.VicabitBE.entity.FillInBlankQuestion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExtraLetterQuestionMapper {
    List<ExtraLetterQuestionResponse> toExtraLetterQuestionList(List<ExtraLetterQuestion> questions);
}
