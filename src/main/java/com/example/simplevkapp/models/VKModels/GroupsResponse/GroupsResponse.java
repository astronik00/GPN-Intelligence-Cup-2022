package com.example.simplevkapp.models.VKModels.GroupsResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GroupsResponse {
    @JsonProperty("response")
    private boolean isMember;
}
