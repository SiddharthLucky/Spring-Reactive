package com.apirepo.apibank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "ADDRESSINFO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long address_id;
    private String street;
    private String city;
    private String state;
}
