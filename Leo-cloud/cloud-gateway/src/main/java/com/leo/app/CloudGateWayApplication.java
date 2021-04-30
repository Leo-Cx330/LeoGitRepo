package com.leo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Copyright xxxx
 * FileName: Leo-cloud
 * Author:   lihao
 * Date:     2019/10/9 10:33 AM
 * Description:
 * author: leo
 */

@SpringBootApplication
@EnableDiscoveryClient
public class CloudGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGateWayApplication.class,args);
    }
}
