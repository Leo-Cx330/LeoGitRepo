package com.leo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.leo.app.*"})
@RefreshScope
public class CloudModelServer {

    public static  void  main(String []args){
        SpringApplication.run(CloudModelServer.class,args);

    }

}
