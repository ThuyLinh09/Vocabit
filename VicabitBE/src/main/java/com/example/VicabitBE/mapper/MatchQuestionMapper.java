package com.example.VicabitBE.mapper;

import com.example.VicabitBE.dto.response.ExtraLetterQuestionResponse;
import com.example.VicabitBE.dto.response.MatchQuestionResponse;
import com.example.VicabitBE.entity.ExtraLetterQuestion;
import com.example.VicabitBE.entity.MatchQuestion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MatchQuestionMapper {
    List<MatchQuestionResponse> toMatchQuestionList(List<MatchQuestion> questions);
}
