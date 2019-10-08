package com.leo.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import lombok.Data;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix ="spring.datasource" )
@RefreshScope
@Component
@Data
public class ModelConfig {


    private String username;


}
