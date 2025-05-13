package com.example.VicabitBE.repositories;


import com.example.VicabitBE.entity.FillInBlankQuestion;
import com.example.VicabitBE.entity.ImageToTextQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FillInBlankQuestionRepository extends JpaRepository<FillInBlankQuestion, Long> {
    @Query(value = "SELECT * FROM fill_in_blank_question WHERE unit = ?1 ORDER BY RAND() LIMIT 10", nativeQuery = true)
    List<FillInBlankQuestion> findRandom10ByUnit(int unit);
}
