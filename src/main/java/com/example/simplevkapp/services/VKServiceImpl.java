package com.example.simplevkapp.services;

import com.example.simplevkapp.models.MyExceptions.ApiException;
import com.example.simplevkapp.models.MyRest.RestRequest;
import com.example.simplevkapp.models.MyRest.RestResponse;
import com.example.simplevkapp.models.VKModels.ErrorResponse.ErrorResponse;
import com.example.simplevkapp.models.VKModels.GroupsResponse.GroupsResponse;
import com.example.simplevkapp.models.VKModels.UsersResponse.UsersResponse;
import com.example.simplevkapp.models.VKModels.UsersResponse.UsersResponseList;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class VKServiceImpl implements VKService {

    CloseableHttpResponse callVkAPI(String url) throws ApiException {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            return httpClient.execute(httpGet);
        } catch (IOException e) {
            throw new ApiException(301, "Error happened during performing request to VK API");
        }
    }

    private boolean callIsMember(String accessToken, RestRequest restRequest) throws ApiException {
        ObjectMapper mapper = new ObjectMapper();
               // .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String url = "https://api.vk.com/method/groups.isMember?" +
                "user_id=" + restRequest.getUserID() +
                "&group_id=" + restRequest.getGroupID() +
                "&access_token=" + accessToken +
                "&v=5.131";

        try (CloseableHttpResponse httpResponse = callVkAPI(url)) {
            return mapper
                    .readValue(httpResponse.getEntity().getContent(), GroupsResponse.class).isMember();

        } catch (Exception var1) {
            try(CloseableHttpResponse httpResponse = callVkAPI(url)) {
                var error = mapper
                        .readValue(httpResponse.getEntity().getContent(), ErrorResponse.class)
                        .getError();
                throw new ApiException(error.getCode(), error.getMessage());
            } catch (IOException var2) {
                throw new ApiException(302, "Cannot parse VK API response groups.IsMember()");
            }
        }
    }

    private UsersResponse callGetUserName(String accessToken, RestRequest restRequest) throws ApiException {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String url = "https://api.vk.com/method/users.get?" +
                "user_ids=" + restRequest.getUserID() +
                "&access_token=" + accessToken +
                "&v=5.131";

        try (CloseableHttpResponse httpResponse = callVkAPI(url)) {
            return mapper
                    .readValue(httpResponse.getEntity().getContent(), UsersResponseList.class)
                    .getUsersResponses()
                    .get(0);

        } catch (Exception var1) {
            try(CloseableHttpResponse httpResponse = callVkAPI(url)) {
                var error = mapper
                        .readValue(httpResponse.getEntity().getContent(), ErrorResponse.class);
                throw new ApiException(error.getError().getCode(), error.getError().getMessage());
            } catch (IOException var2) {
                throw new ApiException(301, "Cannot parse VK API response users.get()");
            }
        }
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void isFormatCorrect(String var, String name) throws ApiException {
        if (var.isEmpty())
            throw new ApiException(
                    100,
                    "One of the parameters specified was missing or invalid: " + name + " is undefined"
            );
        if (!isInteger(var))
            throw new ApiException(
                    100,
                    "One of the parameters specified was missing or invalid: " + name + " not integer");
        else if (Integer.parseInt(var) <= 0)
            throw new ApiException(
                    100,
                    "One of the parameters specified was missing or invalid: " + name + " should be greater than 0");

    }

    @Override
    @Cacheable(value = "users", key = "#restRequest.userID")
    public RestResponse buildUser(String accessToken, RestRequest restRequest) throws ApiException {
        System.out.println("Starting activity to find user by user_id: " + restRequest.getUserID());

        isFormatCorrect(restRequest.getUserID(), "user_id");

        var user = callGetUserName(accessToken, restRequest);
        return new RestResponse(
                user.getFirstName(),
                user.getLastName(),
                "",
                String.valueOf(callIsMember(accessToken, restRequest))
        );
    }
}
