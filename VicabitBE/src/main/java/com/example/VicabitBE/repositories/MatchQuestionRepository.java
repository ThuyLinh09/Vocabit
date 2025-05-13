package com.example.VicabitBE.repositories;


import com.example.VicabitBE.entity.ExtraLetterQuestion;
import com.example.VicabitBE.entity.MatchQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchQuestionRepository extends JpaRepository<MatchQuestion, Long> {
    @Query(value = "SELECT * FROM match_question WHERE unit = ?1 ORDER BY RAND() LIMIT 10", nativeQuery = true)
    List<MatchQuestion> findRandom10ByUnit(int unit);
}
