package com.example.VicabitBE.repositories;


import com.example.VicabitBE.entity.ExtraLetterQuestion;
import com.example.VicabitBE.entity.FillInBlankQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtraLetterQuestionRepository extends JpaRepository<ExtraLetterQuestion, Long> {
    @Query(value = "SELECT * FROM extra_letter_question WHERE unit = ?1 ORDER BY RAND() LIMIT 10", nativeQuery = true)
    List<ExtraLetterQuestion> findRandom10ByUnit(int unit);
}
