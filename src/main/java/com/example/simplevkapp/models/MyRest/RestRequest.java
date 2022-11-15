package com.example.simplevkapp.models.MyRest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestRequest {
    @JsonProperty("user_id")
    private String userID;
    @JsonProperty("group_id")
    private String groupID;
}
