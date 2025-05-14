package com.example.VicabitBE.controller;

import com.example.VicabitBE.dto.response.ApiResponse;
import com.example.VicabitBE.dto.response.PracticeResponse;
import com.example.VicabitBE.service.PracticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/practices")
public class PracticeController {
    @Autowired
    private PracticeService practiceService;

    @GetMapping
    public ApiResponse<List<PracticeResponse>> getAllPractices() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        String role;

        // Nếu không có authentication hoặc authentication chưa xác thực thì coi là guest
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            role = "guest";
        } else {
            role = auth.getAuthorities().stream()
                    .map(grantedAuthority -> grantedAuthority.getAuthority())
                    .findFirst()
                    .orElse("guest");
        }

        log.info("Role: {}", role);

        List<PracticeResponse> practices = practiceService.getAllPractices(role);
        return ApiResponse.<List<PracticeResponse>>builder()
                .result(practices)
                .build();
    }


}
