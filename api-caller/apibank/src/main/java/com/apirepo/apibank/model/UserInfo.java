package com.apirepo.apibank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo
{
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
}
