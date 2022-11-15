package com.example.simplevkapp.models.VKModels.ErrorResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RequestParams {
    @JsonProperty("key")
    private String key;
    @JsonProperty("value")
    private String value;
}
