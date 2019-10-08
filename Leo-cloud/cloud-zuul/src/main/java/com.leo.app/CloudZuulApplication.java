package com.leo.app;


import com.leo.app.filter.DecryptFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableEurekaClient //如果需要注册倒eureka服务中心的话，启用这个
@EnableZuulProxy
public class CloudZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudZuulApplication.class,args);
    }

    @Bean
    public DecryptFilter decryptFilter(){
        return new DecryptFilter();
    }
}
