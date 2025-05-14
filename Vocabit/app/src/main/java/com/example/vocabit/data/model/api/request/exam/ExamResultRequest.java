package com.example.vocabit.data.model.api.request.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResultRequest {
    String username;
    int unit;
    int score;
    long durationInSeconds;
}
