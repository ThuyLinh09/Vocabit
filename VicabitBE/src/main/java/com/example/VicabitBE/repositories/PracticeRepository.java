package com.example.VicabitBE.repositories;


import com.example.VicabitBE.entity.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PracticeRepository extends JpaRepository<Practice, Long> {
    List<Practice> findTop2ByOrderByIdAsc();
}
