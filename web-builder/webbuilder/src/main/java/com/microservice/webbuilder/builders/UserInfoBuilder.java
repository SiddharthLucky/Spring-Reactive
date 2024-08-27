package com.microservice.webbuilder.builders;

import com.microservice.webbuilder.DTO.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class UserInfoBuilder {

    @Autowired
    private WebClient userInfoWebClient;

    public Mono<UserInfoDTO> getUserInfoById(Long id) {
        return userInfoWebClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(UserInfoDTO.class);
    }

    public Flux<UserInfoDTO> getAllUsers() {
        return userInfoWebClient.get()
                .uri("/all-users")
                .retrieve()
                .bodyToFlux(UserInfoDTO.class);
    }

    public Mono<String> addUserInfo(UserInfoDTO userInfoDTO) {
        return userInfoWebClient.post()
                .uri("/add-user")
                .bodyValue(userInfoDTO)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        return clientResponse.bodyToMono(String.class);
                    } else {
                        return clientResponse.bodyToMono(String.class)
              .flatMap(errorBody -> Mono.error(new RuntimeException(
                                "Failed to add user. Status: " + clientResponse.statusCode().value() + ", Error: " + errorBody)));
                    }
                });
    }

    public Mono<String> deleteUserById(Long id) {
        return userInfoWebClient.delete()
                .uri("/delete-user/{id}",id)
                .retrieve().bodyToMono(String.class);
    }

    public Mono<String> updateUserById(UserInfoDTO userInfoDTO) {
        return userInfoWebClient.put()
                .uri("/update-user")
                .bodyValue(userInfoDTO)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        return clientResponse.bodyToMono(String.class);
                    } else {
                        return clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException(
                                        "Failed to add user. Status: " + clientResponse.statusCode().value() + ", Error: " + errorBody)));
                    }
                });
    }
}
