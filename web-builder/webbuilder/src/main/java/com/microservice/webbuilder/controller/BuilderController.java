package com.microservice.webbuilder.controller;

import com.microservice.webbuilder.DTO.UserInfoDTO;
import com.microservice.webbuilder.service.BuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
