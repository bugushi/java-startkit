package com.example.demo.user.entity;

import lombok.Data;

@Data
public class UserEntity {
    private Integer id;

    private String userName;

    private String pasword;

    private String salt;
}