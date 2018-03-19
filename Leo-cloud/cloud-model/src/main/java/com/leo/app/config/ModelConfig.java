package com.leo.app.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix ="spring.datasource" )
@Data
@RefreshScope
@Component
@EqualsAndHashCode(callSuper = false)
public class ModelConfig {

    private String username;


}
