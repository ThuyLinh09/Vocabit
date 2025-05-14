package com.example.VicabitBE.service;

import com.example.VicabitBE.dto.response.PracticeResponse;
import com.example.VicabitBE.entity.Practice;
import com.example.VicabitBE.mapper.PracticeMapper;
import com.example.VicabitBE.repositories.PracticeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PracticeService {
    PracticeRepository practiceRepository;
    PracticeMapper practiceMapper;

    public List<PracticeResponse> getAllPractices(String role) {
        log.info("Getting practice questions for role: {}", role);

        List<Practice> practices;

        if ("guest".equalsIgnoreCase(role)) {
            // Lấy ví dụ 5 câu cho khách
            practices = practiceRepository.findTop2ByOrderByIdAsc();
        } else {
            // Lấy toàn bộ dữ liệu cho user
            practices = practiceRepository.findAll();
        }

        return practiceMapper.toPracticeList(practices);
    }
}
