package com.example.vocabit.data.model.api.response.practice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticeResponse {
    int unit;
    String questionType;
}
