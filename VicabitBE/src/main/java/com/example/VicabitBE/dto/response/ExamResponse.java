package com.example.VicabitBE.dto.response;

import com.example.VicabitBE.enums.QuestionType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamResponse {
    int unit;
    QuestionType part1;
    QuestionType part2;
    QuestionType part3;
    QuestionType part4;
}
