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

    @GetMapping(value = "/{id}", produces = "application/json")
    public UserInfo getUserInfo(@PathVariable Long id) {
        return userInfoService.getUserInfo(id);
    }

    @GetMapping(value = "/all-users", produces = "application/json")
    public List<UserInfo> getAllUsers() {
        return userInfoService.getAllUsers();
    }

    @PostMapping(value = "/add-user", produces = "application/json", consumes = "application/json")
    public String addUsers(@RequestBody UserInfo userInfo) {
        return userInfoService.addUserInfo(userInfo);
    }

    @DeleteMapping(value = "/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userInfoService.deleteUser(id);
        return "User deleted";
    }

    @DeleteMapping(value = "/delete-multiple-users", consumes = "application/json")
    public String deleteMultipleUsers(@RequestBody List<Long> ids) {
        userInfoService.deleteMultipleUsers(ids);
        return "Multiple users deleted";
    }

    @PutMapping(value = "/update-user", consumes = "application/json", produces = "application/json")
    public String updateUser(@RequestBody UserInfo userInfo) {
        userInfoService.updateUserInfo(userInfo);
        return "User updated and saved to DB.";
    }
}
