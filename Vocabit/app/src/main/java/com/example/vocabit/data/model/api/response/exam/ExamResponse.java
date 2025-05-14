package com.example.vocabit.data.model.api.response.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResponse {
    int unit;
    String part1;
    String part2;
    String part3;
    String part4;
}
