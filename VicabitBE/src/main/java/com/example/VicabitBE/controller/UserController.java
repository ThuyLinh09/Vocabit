package com.example.VicabitBE.controller;


import com.example.VicabitBE.dto.response.ApiResponse;
import com.example.VicabitBE.dto.response.UserResponse;
import com.example.VicabitBE.entity.User;
import com.example.VicabitBE.dto.request.UserCreationRequest;
import com.example.VicabitBE.dto.request.UserUpdateRequest;
import com.example.VicabitBE.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse <UserResponse> createUser(@RequestBody @Valid UserCreationRequest user) {
        ApiResponse<UserResponse>userApiResponse = new ApiResponse<>();
        userApiResponse.setResult(userService.createUser(user));

        return userApiResponse;
    }

    @GetMapping()
    ApiResponse<List<UserResponse>> getAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.findAll())
                .build();
    }

    @GetMapping("/{id}")
    UserResponse findUserById(@PathVariable("id") Long id) {

        return userService.findById(id);
    }
    @PutMapping("/{id}")
    UserResponse updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateRequest user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "success";
    }

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }
}
