package com.example.VicabitBE.Enum;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized"),
    USER_EXISTED(1001, "User already existed"),
    USERNAME_INVALID(1002, "Username must be at least 8 characters"),
    PASSWORD_INVALID(1003, "Password must be at least 8 characters"),

    NAME_INVALID(1004, "Name is invalid"),

    EMAIL_INVALID(1005, "Email is invalid"),
    INVALID_KEY(1006, "Invalid message key"),

    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
