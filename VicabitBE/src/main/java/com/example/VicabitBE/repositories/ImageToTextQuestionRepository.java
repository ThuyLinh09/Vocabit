package com.example.VicabitBE.repositories;


import com.example.VicabitBE.entity.ImageToTextQuestion;
import com.example.VicabitBE.entity.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageToTextQuestionRepository extends JpaRepository<ImageToTextQuestion, Long> {
    @Query(value = "SELECT * FROM image_to_text_question WHERE unit = ?1 ORDER BY RAND() LIMIT 10", nativeQuery = true)
    List<ImageToTextQuestion> findRandom10ByUnit(int unit);
}
