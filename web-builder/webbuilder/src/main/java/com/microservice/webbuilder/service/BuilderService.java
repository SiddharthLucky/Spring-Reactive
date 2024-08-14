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
}