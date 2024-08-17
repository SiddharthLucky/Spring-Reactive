package com.apirepo.apibank.service;

import com.apirepo.apibank.model.Address;
import com.apirepo.apibank.model.Tags;
import com.apirepo.apibank.model.UserInfo;
import com.apirepo.apibank.repository.TagsRepository;
import com.apirepo.apibank.repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserInfoService
{
    @Autowired
    private UserInfoRepository userInfoRepository;

    @PostConstruct
    public void init() {
        log.info("Post Construct running:");
        log.info("Inserting 1 user into the DB:");
        Tags tags = Tags.builder()
                .tag_name("tag1")
                .build();
        Address address = Address.builder()
                .city("Hyd")
                .street("Street1")
                .state("State1")
                .build();
        UserInfo userInfo = UserInfo.builder()
                .address(address)
                .tags(Set.of(tags))
                .email("Email")
                .name("Sid")
                .phone("1")
                .build();
        userInfoRepository.save(userInfo);
        log.info("Inserted users into the DB:");
    }

    public UserInfo getUserInfo(Long userId) {
        log.info("Getting User Info basing on the ID: "+userId);
        return userInfoRepository.findById(userId).stream()
                .filter(userInfo -> userInfo.getUser_id().equals(userId))
                .findFirst().orElse(null);
    }

    public String addUserInfo(UserInfo userInfo) {
        log.info("Adding User Info basing on the ID: "+userInfo.getUser_id());
        userInfoRepository.save(userInfo);
        return "User List Updated";
    }


    public List<UserInfo> getAllUsers() {
        log.info("Getting All User List");
        return userInfoRepository.findAll();
    }

    public void deleteUser(Long id) {
        log.info("Deleting User with ID: "+id);
        userInfoRepository.deleteById(id);
    }

    public void deleteMultipleUsers(List<Long> ids) {
        for(Long id : ids) {
            log.info("Deleting User with ID: "+id);
            userInfoRepository.deleteById(id);
        }
    }

    public UserInfo updateUserInfo(UserInfo userInfo) {
        log.info("Updating User Info basing on the ID: "+userInfo.getUser_id());
        Optional<UserInfo> updatedUserInfo = userInfoRepository.findById(userInfo.getUser_id());
        if(updatedUserInfo.isPresent()) {
            return userInfoRepository.save(userInfo);
        }
        else {
            return null;
        }
    }
}
