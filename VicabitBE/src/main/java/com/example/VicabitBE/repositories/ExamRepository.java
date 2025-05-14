package com.example.VicabitBE.repositories;


import com.example.VicabitBE.entity.Exam;
import com.example.VicabitBE.entity.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

}
