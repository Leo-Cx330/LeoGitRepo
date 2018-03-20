package com.leo.app.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User_role")
@Data
public class User_role {

    @Id
    @GeneratedValue
    private Integer uid;

    private Integer rid;


}