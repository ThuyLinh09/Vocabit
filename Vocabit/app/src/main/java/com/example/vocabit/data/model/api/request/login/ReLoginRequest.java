package com.example.vocabit.data.model.api.request.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReLoginRequest {
    private String phone ;
    private String password;
    private String serial;
    private String grant_type ;

    public ReLoginRequest(String phone, String password, String serial) {
        this.phone = phone;
        this.password = password;
        this.serial = serial;
        this.grant_type= "employee";
    }
}
