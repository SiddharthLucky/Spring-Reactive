package com.apirepo.apibank.service;

import com.apirepo.apibank.model.UserInfo;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserInfoService
{
    UserInfo userInfo1 = new UserInfo(1L, "Sid1", "sid1@sid1.com", "1", "address1");
    UserInfo userInfo2 = new UserInfo(2L, "Sid2", "sid2@sid2.com", "2", "address2");
    UserInfo userInfo3 = new UserInfo(3L, "Sid3", "sid3@sid3.com", "3", "address3");
    UserInfo userInfo4 = new UserInfo(4L, "Sid4", "sid4@sid4.com", "4", "address4");
    @Getter
    List<UserInfo> userInfoList = new ArrayList<UserInfo>(Arrays.asList(userInfo1, userInfo2, userInfo3, userInfo4));

    public UserInfo getUserInfo(Long userId) {
        log.info("Getting User Info basing on the ID: "+userId);
        return userInfoList.stream()
                .filter(userInfo -> userInfo.getId().equals(userId))
                .findFirst().orElse(null);
    }

    public String addUserInfo(UserInfo userInfo) {
        log.info("Adding User Info basing on the ID: "+userInfo.getId());
        userInfoList.add(userInfo);
        return "User List Updated";
    }

}
