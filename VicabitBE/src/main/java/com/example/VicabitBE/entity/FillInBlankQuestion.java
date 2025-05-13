package com.example.VicabitBE.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FillInBlankQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    int unit;
    String sentence;

    @ElementCollection
    @CollectionTable(name = "fill_in_blank_options", joinColumns = @JoinColumn(name = "question_id"))
    List<String> options;  // Mảng các đáp án (A, B, C, D)

    @Column(name = "correct_option")
    String correctOption;
}
