package com.microservice.webbuilder.controller;

import com.microservice.webbuilder.DTO.UserInfoDTO;
import com.microservice.webbuilder.service.BuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user-info")
public class BuilderController {

    @Autowired
    private BuilderService builderService;

    @GetMapping("/{id}")
    public ResponseEntity<Mono<UserInfoDTO>> getUserInfoById(@PathVariable Long id) {
        return ResponseEntity.ok(builderService.getUserInfoById(id));
    }

    @PostMapping("/insert-user")
    public ResponseEntity<Mono<String>> insertUserInfo(@RequestBody UserInfoDTO userInfoDTO) {
        return ResponseEntity.ok(builderService.insertUserInfo(userInfoDTO));
    }
}
