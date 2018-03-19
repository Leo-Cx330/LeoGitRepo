package com.leo.app.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Module_role")
@Data
public class Module_role {

    @Id
    @GeneratedValue
    private Integer rid;

    private Integer mid;


}