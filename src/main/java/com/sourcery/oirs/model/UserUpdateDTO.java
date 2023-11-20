package com.sourcery.oirs.model;

import lombok.Data;

import java.util.UUID;

@Data
public class UserUpdateDTO {

    private UUID id;
    private Address address;
    private String avatar;

}
