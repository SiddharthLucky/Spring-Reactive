package com.microservice.webbuilder.service;

import com.microservice.webbuilder.DTO.UserInfoDTO;
import com.microservice.webbuilder.builders.UserInfoBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class BuilderService {

    @Autowired
    private UserInfoBuilder userInfoBuilder;

    @Cacheable(value = "app_user", key = "#userid")
    public Mono<UserInfoDTO> getUserInfoById(Long id) {
        return userInfoBuilder.getUserInfoById(id);
    }

    @CachePut(value = "app_user", key = "#userid")
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

    @Cacheable(value = "app_user")
    public Flux<UserInfoDTO> getAllUserInfo() {
        log.info("Getting all users");
        return userInfoBuilder.getAllUsers();
    }

    @CacheEvict(value = "app_user", key = "#userid")
    public Mono<String> deleteUserInfoById(Long id) {
        log.info("Deleting user id {}", id);
        return userInfoBuilder.deleteUserById(id);
    }

    @CacheEvict(value = "app_user", key = "#userid")
    public Mono<String> updateUserInfo(UserInfoDTO userInfoDTO) {
        log.info("updating the user with id: "+userInfoDTO.getUser_id());
        return userInfoBuilder.updateUserById(userInfoDTO);
    }
}