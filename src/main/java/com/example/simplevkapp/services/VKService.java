package com.example.simplevkapp.services;

import com.example.simplevkapp.models.MyExceptions.ApiException;
import com.example.simplevkapp.models.MyRest.RestRequest;
import com.example.simplevkapp.models.MyRest.RestResponse;

import java.io.IOException;

public interface VKService {
    RestResponse buildUser(String accessToken, RestRequest restRequest) throws IOException, ApiException;
}
