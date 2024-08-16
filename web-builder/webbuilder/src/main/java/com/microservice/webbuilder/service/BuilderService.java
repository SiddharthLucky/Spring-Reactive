package com.microservice.webbuilder.service;

import com.microservice.webbuilder.DTO.UserInfoDTO;
import com.microservice.webbuilder.builders.UserInfoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BuilderService {

    @Autowired
    private UserInfoBuilder userInfoBuilder;

    public Mono<UserInfoDTO> getUserInfoById(Long id) {
        return userInfoBuilder.getUserInfoById(id);
    }

    public Mono<String> insertUserInfo(UserInfoDTO userInfoDTO) {
        return userInfoBuilder.addUserInfo(userInfoDTO)
                .flatMap(userInfo -> {
                    if (userInfo != null) {
                        return Mono.just("User is added to DB");
                    } else {
                        return Mono.just("User isn't inserted");
                    }
                })
                .onErrorReturn("User isn't inserted due to an error");
    }
}