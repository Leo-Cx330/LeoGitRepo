package com.leo.app.configruation;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/8/24 3:47 PM
 * @Description:
 * @author: lihao
 */
@RefreshScope
@Slf4j
@ConfigurationProperties(prefix = "nacos")
@Data
@Configuration
public class TestConfig {

    private String config;

    private static Map<String, Object> map = Maps.newHashMap();

    public  String getMap(String key){
        return (String) map.get(key);
    }

    @PostConstruct
    public void init(){
          map.put("user",config);
        log.info("TestConfig .....is init");

    }






}
