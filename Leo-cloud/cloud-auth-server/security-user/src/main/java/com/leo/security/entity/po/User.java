package com.leo.security.entity.po;

import lombok.Data;

import java.util.Date;


@Data
public class User {

    private String id;
    private String username;
    private String password;
    private String age;
    private Date birthDay;
    private String mobile;
}
