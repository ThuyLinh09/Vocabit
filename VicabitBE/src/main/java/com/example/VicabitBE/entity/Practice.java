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
public class Practice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String title;
    int unit;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type")
    QuestionType questionType;


}
