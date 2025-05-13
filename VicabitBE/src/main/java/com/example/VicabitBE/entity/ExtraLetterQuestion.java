package com.example.VicabitBE.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExtraLetterQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    int unit;

    @Column(name = "incorrect_word")
    String incorrectWord;

    @Column(name = "correct_word")
    String correctWord;


}
