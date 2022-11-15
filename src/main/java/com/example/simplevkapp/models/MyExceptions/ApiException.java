package com.example.simplevkapp.models.MyExceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiException extends Exception {
    private Integer code;
    private String message;
}
