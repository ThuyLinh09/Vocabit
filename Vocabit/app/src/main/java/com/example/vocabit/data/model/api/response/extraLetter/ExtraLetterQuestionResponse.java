package com.example.vocabit.data.model.api.response.extraLetter;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraLetterQuestionResponse {
    String incorrectWord;
    String correctWord;
}
