package com.example.simplevkapp.controllers;

import com.example.simplevkapp.SimpleVkAppApplicationTests;
import com.example.simplevkapp.configurations.VKConfiguration;
import com.example.simplevkapp.models.MyRest.RestErrorResponse;
import com.example.simplevkapp.models.MyRest.RestRequest;
import com.example.simplevkapp.models.MyRest.RestResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VKControllerTest extends SimpleVkAppApplicationTests {
    @Autowired
    private VKController vkController;
    @Autowired
    private VKConfiguration vkConfiguration;

    @Test
    void testCorrectRequestAndCorrectToken() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        var expected = new ResponseEntity<>(new RestResponse(
                "Pavel",
                "Durov",
                "",
                "false"
        ), HttpStatus.OK);

        RestRequest restRequest = new RestRequest("1", "catx2");
        var result = vkController.getUser(vkConfiguration.getAccessToken(), restRequest);

        assertEquals(expected, result);
    }

    @Test
    void testCorrectRequestAndIncorrectToken() throws IOException {
        var expected = new ResponseEntity<>(new RestErrorResponse(
                5,
                "User authorization failed: no access_token passed."
        ), HttpStatus.NOT_FOUND);

        RestRequest restRequest = new RestRequest("1", "catx2");
        var result = vkController.getUser("", restRequest);

        assertEquals(expected, result);
    }

    @Test
    void testEmptyUserIDAndCorrectToken() throws IOException {
        var expected = new ResponseEntity<>(new RestErrorResponse(
                100,
                "One of the parameters specified was missing or invalid: user_id is undefined"
        ), HttpStatus.NOT_FOUND);

        RestRequest restRequest = new RestRequest("", "catx2");
        var result = vkController.getUser(vkConfiguration.getAccessToken(), restRequest);

        assertEquals(expected, result);
    }

    @Test
    void testEmptyGroupIDAndCorrectToken() throws IOException {
        var expected = new ResponseEntity<>(new RestErrorResponse(
                100,
                "One of the parameters specified was missing or invalid: group_id is undefined"
        ), HttpStatus.NOT_FOUND);

        RestRequest restRequest = new RestRequest("1", "");
        var result = vkController.getUser(vkConfiguration.getAccessToken(), restRequest);

        assertEquals(expected, result);
    }

    @Test
    void testEmptyUserAndGroupIDsAndCorrectToken() throws IOException {
        var expected = new ResponseEntity<>(new RestErrorResponse(
                100,
                "One of the parameters specified was missing or invalid: user_id is undefined"
        ), HttpStatus.NOT_FOUND);

        RestRequest restRequest = new RestRequest("", "");
        var result = vkController.getUser(vkConfiguration.getAccessToken(), restRequest);

        assertEquals(expected, result);
    }

    @Test
    void testIncorrectUserIDAndCorrectToken() throws IOException {
        var expected = new ResponseEntity<>(new RestErrorResponse(
                100,
                "One of the parameters specified was missing or invalid: user_id should be greater than 0"
        ), HttpStatus.NOT_FOUND);

        RestRequest restRequest = new RestRequest("-1", "catx2");
        var result = vkController.getUser(vkConfiguration.getAccessToken(), restRequest);

        assertEquals(expected, result);
    }

    @Test
    void testIncorrectGroupIDAndCorrectToken() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        var expected = new ResponseEntity<>(new RestErrorResponse(
                100,
                "One of the parameters specified was missing or invalid: group_id is invalid"
        ), HttpStatus.NOT_FOUND);

        RestRequest restRequest = new RestRequest("1", "catx23");
        var result = vkController.getUser(vkConfiguration.getAccessToken(), restRequest);

        assertEquals(expected, result);
    }

}