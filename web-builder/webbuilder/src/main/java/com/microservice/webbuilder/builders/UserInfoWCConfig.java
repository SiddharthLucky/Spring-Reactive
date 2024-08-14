package com.microservice.webbuilder.builders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class UserInfoWCConfig {

    @Value("${com.userinfo.builder.base-url}")
    private String userInfoBaseUrl;

    @Bean
    public WebClient webClient(WebClient.Builder userInfoBuilder) {
        return userInfoBuilder.baseUrl(userInfoBaseUrl).build();
    }
}
