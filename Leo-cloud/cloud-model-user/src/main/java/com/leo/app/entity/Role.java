package com.leo.app.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Role")
@Data
public class Role {

    @Id
    @GeneratedValue
    private Integer rid;

    private String rname;

    //private Set<User>users=new HashSet<>();

    //private Set<Module>modules=new HashSet<>();

}