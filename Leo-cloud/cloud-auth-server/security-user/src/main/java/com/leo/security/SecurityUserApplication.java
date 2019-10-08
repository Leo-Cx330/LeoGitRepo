package com.leo.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.leo")
@MapperScan("com.leo.security.mapper")
@EnableEurekaClient
public class SecurityUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityUserApplication.class,args);
    }
}
