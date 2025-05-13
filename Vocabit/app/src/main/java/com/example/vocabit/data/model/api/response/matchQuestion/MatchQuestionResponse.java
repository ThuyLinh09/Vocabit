package com.example.vocabit.data.model.api.response.matchQuestion;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchQuestionResponse {
    List<String> choices_en;
    List<String> choices_vn;
    Map<String, String> correctMatches;
}
