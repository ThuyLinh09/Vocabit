package com.example.VicabitBE.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1001, "User already existed", HttpStatus.BAD_REQUEST),
    USER_DOES_NOT_EXIST(1008, "User does not exist", HttpStatus.NOT_FOUND),
    USERNAME_INVALID(1002, "Username must be at least 8 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),

    NAME_INVALID(1004, "Name is invalid", HttpStatus.BAD_REQUEST),

    EMAIL_INVALID(1005, "Email is invalid", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1006, "Invalid message key", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1007, "Unauthenticated", HttpStatus.UNAUTHORIZED),

    UNAUTHORIZED(1008, "You do not have permission", HttpStatus.FORBIDDEN),

    ;
    private int code;
    private String message;

    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

}
