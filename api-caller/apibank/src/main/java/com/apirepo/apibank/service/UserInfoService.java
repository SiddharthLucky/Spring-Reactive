package com.apirepo.apibank.service;

import com.apirepo.apibank.model.Address;
import com.apirepo.apibank.model.Tags;
import com.apirepo.apibank.model.UserInfo;
import com.apirepo.apibank.repository.TagsRepository;
import com.apirepo.apibank.repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserInfoService
{
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private TagsRepository tagsRepository;

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

    public void updateUserInfo(UserInfo userInfo) {
        log.info("Updating User Info basing on the ID: " + userInfo.getUser_id());
        Optional<UserInfo> updatedUserInfo = userInfoRepository.findById(userInfo.getUser_id());
        if (updatedUserInfo.isPresent()) {
            UserInfo existingUserInfo = updatedUserInfo.get();

            // Update basic user information (name, email, phone)
            existingUserInfo.setName(userInfo.getName());
            existingUserInfo.setEmail(userInfo.getEmail());
            existingUserInfo.setPhone(userInfo.getPhone());

            // Update address
            if (userInfo.getAddress() != null) {
                existingUserInfo.getAddress().setStreet(userInfo.getAddress().getStreet());
                existingUserInfo.getAddress().setCity(userInfo.getAddress().getCity());
                existingUserInfo.getAddress().setState(userInfo.getAddress().getState());
            }

            // Update tags using a combination of both approaches
            Set<Tags> updatedTags = new HashSet<>();
            for (Tags tag : userInfo.getTags()) {
                if (tag.getTag_name() != null) {
                    // Create new Tags with updated name
                    updatedTags.add(new Tags(tag.getTag_id(), tag.getTag_name()));
                } else {
                    // Keep existing tag if no update provided
                    updatedTags.add(tag);
                }
            }

            // Associate the updated tags with the existing UserInfo
            existingUserInfo.getTags().clear();
            existingUserInfo.getTags().addAll(updatedTags);

            userInfoRepository.save(existingUserInfo);
            log.info("Updated User Info basing on the ID: " + userInfo.getUser_id());
        } else {
            log.info("User not updated.");
        }
    }
}
