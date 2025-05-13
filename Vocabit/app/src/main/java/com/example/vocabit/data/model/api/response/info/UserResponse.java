package com.example.vocabit.data.model.api.response.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String avatar;
    private Long classLevel;
    private Set<String> roles;
}