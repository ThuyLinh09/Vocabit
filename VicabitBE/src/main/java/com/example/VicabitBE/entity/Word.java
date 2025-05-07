package com.example.VicabitBE.entity;

import com.example.VicabitBE.enums.DifficultyLevel;
import com.example.VicabitBE.enums.PartOfSpeech;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String word;
    String meaning;
    String example;
    String pronunciation;
    String audioUrl;
    DifficultyLevel difficultyLevel;
    PartOfSpeech partOfSpeech;
    String imageUrl;
}
