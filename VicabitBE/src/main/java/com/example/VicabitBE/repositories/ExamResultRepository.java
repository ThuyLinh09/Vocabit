package com.example.VicabitBE.repositories;


import com.example.VicabitBE.entity.Exam;
import com.example.VicabitBE.entity.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {

}
