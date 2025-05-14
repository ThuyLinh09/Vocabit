package com.example.VicabitBE.entity;

import com.example.VicabitBE.enums.QuestionType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    int unit;

    @Enumerated(EnumType.STRING)
    QuestionType part1;

    @Enumerated(EnumType.STRING)
    QuestionType part2;

    @Enumerated(EnumType.STRING)
    QuestionType part3;

    @Enumerated(EnumType.STRING)
    QuestionType part4;
}
