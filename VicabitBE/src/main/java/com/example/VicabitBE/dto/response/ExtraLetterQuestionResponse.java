package com.example.VicabitBE.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExtraLetterQuestionResponse {
    String incorrectWord;
    String correctWord;
}
