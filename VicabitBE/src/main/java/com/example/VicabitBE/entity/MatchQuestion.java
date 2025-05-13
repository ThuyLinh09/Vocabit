package com.example.VicabitBE.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    int unit;

    @ElementCollection
    @CollectionTable(name = "match_question_choices_en", joinColumns = @JoinColumn(name = "question_id"))
    List<String> choices_en;

    // Lựa chọn bằng tiếng Việt
    @ElementCollection
    @CollectionTable(name = "match_question_choices_vn", joinColumns = @JoinColumn(name = "question_id"))
    List<String> choices_vn;

    // Các cặp đúng của câu hỏi (match đúng giữa các lựa chọn tiếng Anh và tiếng Việt)
    @ElementCollection
    @CollectionTable(name = "match_question_correct_matches", joinColumns = @JoinColumn(name = "question_id"))
    @MapKeyColumn(name = "choice_en")  // Lựa chọn tiếng Anh làm khóa chính
    @Column(name = "choice_vn")
    Map<String, String> correctMatches;
}
