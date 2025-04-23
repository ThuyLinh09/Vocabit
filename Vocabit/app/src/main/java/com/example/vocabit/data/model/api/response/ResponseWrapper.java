package com.example.vocabit.data.model.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper<T> {
    private boolean result;
    private T data;
    private String message;
    private String code;
    private String firebaseUrl;
    private String urlBase;
}
