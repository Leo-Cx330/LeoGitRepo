package com.leo.app.endpoint;

import com.leo.app.logger.annotation.ApiLog;
import com.leo.app.configruation.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/8/24 3:57 PM
 * @Description:
 * @author: lihao
 */
@RestController
@RequestMapping("/dynamic/refresh")
@Slf4j
@ApiLog
public class DynamicRefreshConfigEndpoint {

    @Resource
    private TestConfig testConfig;


    @GetMapping("/getConfig")
    public String getConfig(){
        log.info("api接口 ......日志收集中.................!");
        return  "SUCCESS";
    }
}
