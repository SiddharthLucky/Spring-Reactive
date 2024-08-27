package com.microservice.webbuilder.controller;

import com.microservice.webbuilder.DTO.UserInfoDTO;
import com.microservice.webbuilder.service.BuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/user-info")
public class BuilderController {

    @Autowired
    private BuilderService builderService;

    @GetMapping("/{id}")
    public ResponseEntity<Mono<UserInfoDTO>> getUserInfoById(@PathVariable Long id) {
        return ResponseEntity.ok(builderService.getUserInfoById(id));
    }

    @PostMapping(value = "/insert-user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Mono<String>> insertUserInfo(@RequestBody UserInfoDTO userInfoDTO) {
        return ResponseEntity.ok(builderService.insertUserInfo(userInfoDTO));
    }

    @GetMapping("all-users")
    public ResponseEntity<Flux<UserInfoDTO>> getAllUserInfo() {
        return ResponseEntity.ok(builderService.getAllUserInfo());
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Mono<String>> deleteUserInfo(@PathVariable Long id) {
        return ResponseEntity.ok(builderService.deleteUserInfoById(id));
    }

    @PutMapping("/update-user")
    public ResponseEntity<Mono<String>> updateUserInfo(@RequestBody UserInfoDTO userInfoDTO) {
        return ResponseEntity.ok(builderService.updateUserInfo(userInfoDTO));
    }
}
