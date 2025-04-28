package com.example.VicabitBE.controller;


import com.example.VicabitBE.dto.request.ApiResponse;
import com.example.VicabitBE.dto.response.UserResponse;
import com.example.VicabitBE.entity.User;
import com.example.VicabitBE.dto.request.UserCreationRequest;
import com.example.VicabitBE.dto.request.UserUpdateRequest;
import com.example.VicabitBE.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse <User> createUser(@RequestBody @Valid UserCreationRequest user) {
        ApiResponse<User>userApiResponse = new ApiResponse<>();
        userApiResponse.setResult(userService.createUser(user));

        return userApiResponse;
    }

    @GetMapping
    List<User> getUsers() {
        return userService.findAll();
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
}
