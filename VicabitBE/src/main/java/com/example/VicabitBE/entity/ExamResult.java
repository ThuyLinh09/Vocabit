package com.example.VicabitBE.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    int unit;
    String username;
    int score;
    @Column(name = "duration_in_seconds")
    long durationInSeconds;
    long grade;
    @Column(name = "submitted_at")
    LocalDateTime submittedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    User user;

}
