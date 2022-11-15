package com.example.simplevkapp.controllers;

import com.example.simplevkapp.models.MyExceptions.ApiException;
import com.example.simplevkapp.models.MyRest.RestErrorResponse;
import com.example.simplevkapp.models.MyRest.RestRequest;
import com.example.simplevkapp.services.VKService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class VKController {
    @Autowired
    private VKService vkService;

    @CrossOrigin
    @PostMapping("/user")
    @ResponseBody
    public ResponseEntity<?> getUser(@RequestHeader("access_token") String accessToken,
                                     @RequestBody RestRequest restRequest) throws IOException {
        try {
            var userInfo = vkService.buildUser(accessToken, restRequest);
            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        } catch (ApiException e) {
            return new ResponseEntity<>(new RestErrorResponse(e.getCode(), e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
