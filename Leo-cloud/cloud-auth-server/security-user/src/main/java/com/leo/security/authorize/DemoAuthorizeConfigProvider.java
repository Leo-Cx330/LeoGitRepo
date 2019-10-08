package com.leo.security.authorize;

import com.leo.core.authorize.AuthorizeConfigProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;



@Component
public class DemoAuthorizeConfigProvider implements AuthorizeConfigProvider {


    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
        registry.antMatchers(
                "/user/binding"
                , "/user/appBinding"
                , "/login.html",
                "social/user",
                "/user/regist",
                "sign_up.html"
        )
                .permitAll();
        return false;
    }
}
