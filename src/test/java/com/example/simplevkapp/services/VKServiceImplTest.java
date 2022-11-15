package com.example.simplevkapp.services;

import com.example.simplevkapp.SimpleVkAppApplicationTests;
import com.example.simplevkapp.configurations.VKConfiguration;
import com.example.simplevkapp.models.MyExceptions.ApiException;
import com.example.simplevkapp.models.MyRest.RestRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

class VKServiceImplTest extends SimpleVkAppApplicationTests {

    @Autowired
    private VKService vkService;

    @Autowired
    private VKConfiguration vkConfiguration;

    @Test
    void buildUser() throws IOException, ApiException {
        var request = new RestRequest("1", "catx2");

        System.out.println("User " + vkService
                .buildUser(vkConfiguration.getAccessToken(), request)
                .getFirstName() + " was found");
        System.out.println("User " + vkService
                .buildUser(vkConfiguration.getAccessToken(), request)
                .getFirstName() + " was found");
        System.out.println("User " + vkService
                .buildUser(vkConfiguration.getAccessToken(), request)
                .getFirstName() + " was found");
    }
}