package com.example.simplevkapp.models.VKModels.UsersResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UsersResponseList {
    @JsonProperty("response")
    private List<UsersResponse> usersResponses;
}
