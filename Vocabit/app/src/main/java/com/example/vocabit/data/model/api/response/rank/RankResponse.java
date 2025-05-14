package com.example.vocabit.data.model.api.response.rank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankResponse {
    String name;
    int score;
    long durationInSeconds;
}
