package com.leo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author leo
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosDiscoverServer {

    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoverServer.class, args);
    }
}
