package com.leo.core.authorize;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
public class AuthorizeConfigManagerImpl implements AuthorizeConfigManager {

    @Autowired
    private List<AuthorizeConfigProvider> providerList;

    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
        boolean existAnyRequestConfig = false;
        String existAnyRequestConfigName = null;

        if(providerList!=null&&providerList.size()>0) {

            for (AuthorizeConfigProvider authorizeConfigProvider : providerList) {
                boolean currentIsAnyRequestConfig = authorizeConfigProvider.config(registry);
                if (existAnyRequestConfig && currentIsAnyRequestConfig) {
                    log.error("重复配置anyRequest(),{}", existAnyRequestConfigName);
                    throw new SecurityException("重复的anyRequest配置:" + existAnyRequestConfigName + ","
                            + authorizeConfigProvider.getClass().getSimpleName());
                } else if (currentIsAnyRequestConfig) {
                    existAnyRequestConfig = true;
                    existAnyRequestConfigName = authorizeConfigProvider.getClass().getSimpleName();
                }
            }
        }

        if(!existAnyRequestConfig){
            registry.anyRequest().authenticated();
        }
    }
}
