package com.example.simplevkapp.models.MyRest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class RestErrorResponse {
    @JsonProperty("error_code")
    @Value("1")
    private Integer code;
    @JsonProperty("error_msg")
    @Value("Unknown error happened, try later")
    private String message;
}
