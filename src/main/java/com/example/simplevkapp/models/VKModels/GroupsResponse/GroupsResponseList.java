package com.example.simplevkapp.models.VKModels.GroupsResponse;

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
public class GroupsResponseList {
    @JsonProperty("response")
    private List<GroupsResponse> groupsResponses;
}
