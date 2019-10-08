package com.leo.app.entity;

import lombok.Data;

import java.util.Date;


@Data
public class CloudUser {

    private Integer id;
    private String  username;
    private String  password;
    private String  phone;
    private String  email;
    private Integer status;
    private String  openid;
    private Date created;
    private Date updated;

}