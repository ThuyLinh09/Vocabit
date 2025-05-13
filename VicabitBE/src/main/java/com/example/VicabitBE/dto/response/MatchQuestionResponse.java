package com.example.VicabitBE.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchQuestionResponse {
    List<String> choices_en;
    List<String> choices_vn;
    Map<String, String> correctMatches;
}
