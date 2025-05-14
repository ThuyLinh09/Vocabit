package com.example.VicabitBE.mapper;

import com.example.VicabitBE.dto.response.RankResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RankMapper {
    List<RankResponse> toResponseList(List<RankResponse> projections);
}