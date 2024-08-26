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
public class TagsDTO implements Serializable {
    private Long tag_id;
    private String tag_name;
}
