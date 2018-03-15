package com.leo.app;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.concurrent.LinkedBlockingQueue;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class CloudConfigServer {
    public  static  void  main (String[] args){
        SpringApplication.run(CloudConfigServer.class,args);

    }
}
