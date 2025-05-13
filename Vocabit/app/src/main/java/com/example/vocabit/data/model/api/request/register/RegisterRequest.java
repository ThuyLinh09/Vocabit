package com.example.vocabit.data.model.api.request.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;

    private String password;

    private String name;
    private String email;

    private String avatar;
    private Long classLevel;
}
