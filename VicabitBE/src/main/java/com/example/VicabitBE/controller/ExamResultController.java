package com.example.VicabitBE.controller;


import com.example.VicabitBE.dto.request.ExamResultRequest;
import com.example.VicabitBE.dto.response.ApiResponse;
import com.example.VicabitBE.dto.response.PracticeResponse;
import com.example.VicabitBE.dto.response.RankResponse;
import com.example.VicabitBE.service.ExamResultService;
import com.example.VicabitBE.service.RankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/exam-results")
@RequiredArgsConstructor
public class ExamResultController {

    private final ExamResultService examResultService;
    private final RankService rankService;

    @GetMapping("/rank/{unit}/{classLevel}")
    public ApiResponse<List<RankResponse>> getLeaderboard(
            @PathVariable int unit,
            @PathVariable long classLevel) {
        List<RankResponse> ranks = rankService.getTop10ByUnitAndClassLevel(unit, classLevel);
        return ApiResponse.<List<RankResponse>>builder()
                .result(ranks)
                .build();
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> submitExamResult(@RequestBody ExamResultRequest request) {
        try {
            examResultService.saveExamResult(request);

            // Tạo phản hồi thành công
            ApiResponse<Void> response = ApiResponse.<Void>builder()
                    .code(1000)
                    .message("Exam result saved successfully.")
                    .build();

            return ResponseEntity.ok(response); // Trả về mã trạng thái 200 OK cùng với thông tin
        } catch (Exception e) {
            // Xử lý lỗi, trả về thông tin lỗi
            ApiResponse<Void> response = ApiResponse.<Void>builder()
                    .code(500)
                    .message("Failed to save exam result.")
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // Trả về mã lỗi 500 cùng với thông báo lỗi
        }
    }

}

