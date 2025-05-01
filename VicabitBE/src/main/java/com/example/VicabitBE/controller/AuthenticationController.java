package com.example.VicabitBE.controller;

import com.example.VicabitBE.dto.response.ApiResponse;
import com.example.VicabitBE.dto.request.AuthenticationRequest;
import com.example.VicabitBE.dto.request.IntrospectRequest;
import com.example.VicabitBE.dto.response.AuthenticationResponse;
import com.example.VicabitBE.dto.response.IntrospectResponse;
import com.example.VicabitBE.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService service;
    private final AuthenticationService authenticationService;
    private final RestClient.Builder builder;

    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        var result = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }
    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest authenticationRequest)
        throws ParseException , JOSEException {
        var result = authenticationService.introspect(authenticationRequest);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }


}
