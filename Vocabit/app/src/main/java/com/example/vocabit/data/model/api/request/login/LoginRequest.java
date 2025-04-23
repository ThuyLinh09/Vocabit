package com.example.vocabit.data.model.api.request.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String username ;
    private String password ;
    private String grant_type ;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
        this.grant_type= "password";
    }
}

