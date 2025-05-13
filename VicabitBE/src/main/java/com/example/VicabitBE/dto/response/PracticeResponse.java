package com.example.VicabitBE.dto.response;

import com.example.VicabitBE.enums.QuestionType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PracticeResponse {
    String title;
    int unit;
    QuestionType questionType;
}
