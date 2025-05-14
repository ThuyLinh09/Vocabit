package com.example.VicabitBE.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamResultRequest {
    String username;
    int unit;
    int score;
    long durationInSeconds;

    // Getters and setters
}

