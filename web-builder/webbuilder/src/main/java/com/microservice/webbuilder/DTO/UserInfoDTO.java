package com.microservice.webbuilder.DTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO implements Serializable {
    private Long user_id;
    private String name;
    private String email;
    private String phone;
    private AddressDTO address;
    private Set<TagsDTO> tags;
}
