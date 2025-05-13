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
public class ImageToTextQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    int unit;

    @Column(name = "image_url")
    String imageUrl;

    @ElementCollection
    @CollectionTable(name = "image_to_text_options", joinColumns = @JoinColumn(name = "question_id"))
    List<String> options;  // Mảng các đáp án (A, B, C, D)

    @Column(name = "correct_option")
    String correctOption;
}
