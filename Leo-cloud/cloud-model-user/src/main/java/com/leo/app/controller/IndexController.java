package com.leo.app.controller;

import com.leo.app.api.UserService;
import com.leo.app.base.common.BaseApiService;
import com.leo.app.base.common.ResponseBase;
import com.leo.app.entity.CloudUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/index")
public class IndexController extends BaseApiService {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public ResponseBase getUser(){
        CloudUser user=new CloudUser();
        user.setUsername("abc");
        user.setEmail("123@qq.com");
        user.setPassword("123");
        user.setPhone("123456");
        user.setCreated(new Date());
        user.setUpdated(new Date());
        return   userService.qqLogin(user);

    }

    @RequestMapping("/info")
    public ResponseBase info(){
       return  setResultSuccess();

    }


}
