package com.example.VicabitBE.repositories;

import com.example.VicabitBE.dto.response.RankResponse;
import com.example.VicabitBE.entity.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankRepository extends JpaRepository<ExamResult, Long> {

    @Query(value = """
        SELECT u.name AS name, e.score AS score, e.duration_in_seconds AS durationInSeconds
        FROM exam_result e
        JOIN user u ON e.username = u.username
        WHERE e.unit = ?1
        ORDER BY e.score DESC, e.duration_in_seconds ASC
        LIMIT 10
        """, nativeQuery = true)
    List<RankResponse> findTop10ByUnitAndClassLevel(int unit);
}
