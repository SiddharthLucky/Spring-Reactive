package com.microservice.webbuilder.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO implements Serializable {
    private Long address_id;
    private String street;
    private String city;
    private String state;
}
