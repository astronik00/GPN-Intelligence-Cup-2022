package com.example.simplevkapp.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class VKConfiguration {
    @Value("${vk.api.config.access_token}")
    private String accessToken;
}
