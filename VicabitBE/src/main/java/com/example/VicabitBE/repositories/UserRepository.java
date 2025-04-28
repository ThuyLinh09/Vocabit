package com.example.VicabitBE.repositories;


import com.example.VicabitBE.dto.response.UserResponse;
import com.example.VicabitBE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    UserResponse findById(long id);

}
