package com.example.vocabit.data.model.api.response.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReLoginResponse {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private Long expires_in;
    private String scope;
    private Long user_kind;
    private String tenant_info;
    private String device_serial;
    private Long user_id;
    private String grant_type;
    private String additional_info;
    private String jti;
}
