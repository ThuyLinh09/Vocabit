package com.example.vocabit.data.model.api.response.login;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    @SerializedName("token")
    private String token;

    @SerializedName("authenticated")
    private boolean authenticated;
}
