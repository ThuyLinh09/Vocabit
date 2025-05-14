package com.example.VicabitBE.service;

import com.example.VicabitBE.dto.response.RankResponse;
import com.example.VicabitBE.mapper.RankMapper;
import com.example.VicabitBE.repositories.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankService {

    private final RankRepository examResultRepository;
    private final RankMapper leaderboardMapper;

    public List<RankResponse> getTop10ByUnitAndClassLevel(int unit, long classLevel) {
        var projections = examResultRepository.findTop10ByUnitAndClassLevel(unit, classLevel);
        return leaderboardMapper.toResponseList(projections);
    }
}
