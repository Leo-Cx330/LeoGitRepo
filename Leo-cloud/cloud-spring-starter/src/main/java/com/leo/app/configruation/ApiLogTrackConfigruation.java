package com.leo.app.configruation;

import com.leo.app.logger.componet.ApiLogTrack;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/8/29 1:42 下午
 * @Description:
 * @author: lihao
 */
@Configuration
@EnableAspectJAutoProxy
@ConditionalOnWebApplication
public class ApiLogTrackConfigruation {

    @Bean
    public ApiLogTrack apiLogTrack(){
        return  new ApiLogTrack();
    }
}
