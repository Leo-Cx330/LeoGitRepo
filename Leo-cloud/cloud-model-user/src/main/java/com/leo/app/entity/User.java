package com.leo.app.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "User")
@Data
public class User {
    @Id
    @GeneratedValue
    private Integer uid;
    private String username;
    private String password;
   // private Set<Role>roles=new HashSet<>();


}