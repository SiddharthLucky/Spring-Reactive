package com.apirepo.apibank.controller;

import com.apirepo.apibank.model.UserInfo;
import com.apirepo.apibank.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/{id}")
    public UserInfo getUserInfo(@PathVariable Long id) {
        return userInfoService.getUserInfo(id);
    }

    @GetMapping("/all-users")
    public List<UserInfo> getAllUsers() {
        return userInfoService.getUserInfoList();
    }

    @PutMapping("/add-user")
    public String addUsers(@RequestBody UserInfo userInfo) {
        return userInfoService.addUserInfo(userInfo);
    }
}
