package com.leo.app.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Module")
@Data
public class Module implements Serializable {
    @Id
    @GeneratedValue
    private Integer mid;

    private String mname;

    //private Set<Role>roles;

}