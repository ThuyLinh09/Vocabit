package com.example.VicabitBE.dto.request;


import com.example.VicabitBE.Enum.DifficultyLevel;
import com.example.VicabitBE.Enum.PartOfSpeech;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WordRequest {
    private String word;
    private String meaning;
    private String example;
    private String pronunciation;
    private String audioUrl;
    private DifficultyLevel difficultyLevel;
    private PartOfSpeech partOfSpeech;
    private String imageUrl;
}
